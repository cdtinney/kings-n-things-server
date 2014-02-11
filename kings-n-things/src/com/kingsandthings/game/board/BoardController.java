package com.kingsandthings.game.board;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.PlayerManager;
import com.kingsandthings.model.board.Board;
import com.kingsandthings.model.board.Tile;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.CustomDataFormat;

public class BoardController extends Controller {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(BoardController.class.getName());

	// Model
	private Board board;
	private List<Thing> selectedThings;
	
	private Tile initialMovementTile;
	private boolean selectedForMovement = false;
	
	// Views
	private BoardView boardView;	
	
	// Sub-controller
	private ExpandedTileController expandedTileController;
	
	public void initialize(List<Player> players) {
		
		int numPlayers = players.size();

		board = new Board(numPlayers);
		
		// Initialize views and set the tiles
		boardView = new BoardView();
		boardView.initialize();
		boardView.setTileImages(board.getTiles());
		
		// Initialize the expand tile controller
		expandedTileController = new ExpandedTileController();
		expandedTileController.initialize(players);
		
		// Add the expanded tile view to the board view (initially not visible)
		boardView.getChildren().add(expandedTileController.getView());
		
		// Set up starting tiles (TODO - move this to the initial phase)
		for (Player player : players) {
			int pos = PlayerManager.getInstance().getPosition(player);
			board.setStartingTile(player, pos);
		}
		
		// Set up event handlers for clicking tiles
		setupTileClickHandlers();
		
		selectedThings = new ArrayList<Thing>();
		addEventHandler(expandedTileController.getView(), "finishSelection", "setOnAction", "handleThingSelection");
		
	}
	
	/**
	 * The view of a controller should be the highest-level parent node.
	 * 
	 * @return 
	 */
	public Node getView() {
		return boardView;
	}
	
	/**
	 * Subscribe to click events for the TileView objects.
	 */
	private void setupTileClickHandlers() {
		
		TileView[][] tileViews = boardView.getTiles();
	
		for (int i=0; i<tileViews.length; ++i) {
			for (int j=0; j<tileViews[i].length; ++j) {
				
				final TileView tileView = tileViews[i][j];
				if (tileView == null) {
					continue;
				}
				
				addEventHandler(tileView, "setOnMouseClicked", "handleTileClick");
				addEventHandler(tileView, "setOnMouseEntered", "handleTileMouseEnter");
				addEventHandler(tileView, "setOnMouseExited", "handleTileMouseExit");
				
				// Drag and drop
				addEventHandler(tileView, "setOnDragOver", "handleTileDragOver");
				addEventHandler(tileView, "setOnDragDropped", "handleTileDragDropped");
				addEventHandler(tileView, "setOnDragExited", "handleTileDragExit");
				
				// Action menu (TODO - Refactor. Too many event handlers..
				addEventHandler(tileView.getActionMenu().get("placeControlMarker"), "setOnAction", "handlePlaceControlMarkerMenuItem");
				addEventHandler(tileView.getActionMenu().get("selectThings"), "setOnAction", "handleSelectThings");
				
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void handleThingSelection(Event event) {
		expandedTileController.hideView();
		selectedThings = expandedTileController.getSelectedThings();
		selectedForMovement = !selectedThings.isEmpty();
	}
	
	@SuppressWarnings("unused")
	private void handleSelectThings(Event event) {

		MenuItem item = (MenuItem) event.getSource();
		TileActionMenu tileActionMenu = (TileActionMenu) item.getParentPopup();
		TileView tileView = tileActionMenu.getOwner();
		
		initialMovementTile = tileView.getTile();
		expandedTileController.show(tileView.getTile());
		
	}
	
	@SuppressWarnings("unused")
	private void handlePlaceControlMarkerMenuItem(Event event) {
		
		MenuItem item = (MenuItem) event.getSource();
		
		TileActionMenu tileActionMenu = (TileActionMenu) item.getParentPopup();
		TileView tileView = tileActionMenu.getOwner();
		
		Player player = PlayerManager.getInstance().getActivePlayer();
		
		if (board.setTileControl(tileView.getTile(), player, true)) {
			PhaseManager.getInstance().endPlayerTurn();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void handleTileDragOver(Event event) {

		DragEvent dragEvent = (DragEvent) event;
		
		if (dragEvent.getDragboard().hasContent(CustomDataFormat.THINGS)) {
			
			TileView tileView = (TileView) event.getSource();
			
			boolean activePlayerOwned = tileView.getTile().getOwner() == PlayerManager.getInstance().getActivePlayer();
			
			if (activePlayerOwned) {
				dragEvent.acceptTransferModes(TransferMode.ANY);
			}
			
			tileView.addHighlight(activePlayerOwned);
			
		}
		
		dragEvent.consume();

	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void handleTileDragDropped(Event event) {

		DragEvent dragEvent = (DragEvent) event;
		
		boolean hasThings = dragEvent.getDragboard().hasContent(CustomDataFormat.THINGS);
		if (!hasThings) {
			return;
		}
		
		TileView tileView = (TileView) event.getSource();
		Tile tile = tileView.getTile();
		
		List<Thing> things = (List<Thing>) dragEvent.getDragboard().getContent(CustomDataFormat.THINGS);
		List<String> imageUrls = (List<String>) dragEvent.getDragboard().getContent(CustomDataFormat.IMAGES);
		
		for (int i=0; i<things.size(); ++i) {
			things.get(i).setImage(new Image(imageUrls.get(i)));
		}
		
		boolean success = board.addThingsToTile(tile, things);
		
		dragEvent.setDropCompleted(success);
		dragEvent.consume();
		
	}

	@SuppressWarnings("unused")
	private void handleTileDragExit(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.removeHighlight();
	}
	
	@SuppressWarnings("unused")
	private void handleTileClick(Event event) {
		TileView tileView = (TileView) event.getSource();
		
		if (selectedForMovement) {
			boolean success = board.moveThings(initialMovementTile, tileView.getTile(), selectedThings);
			selectedForMovement = false;
			tileView.removeHighlight();
			return;
		}
		
		tileView.toggleActionMenu();	
	}
	
	@SuppressWarnings("unused")
	private void handleTileMouseExit(Event event) {
		TileView tileView = (TileView) event.getSource();
		
		if (selectedForMovement) {
			tileView.removeHighlight();
			return;
		}
		
		tileView.setOpacity(1.0);
	}
	
	@SuppressWarnings("unused")
	private void handleTileMouseEnter(Event event) {
		TileView tileView = (TileView) event.getSource();
		
		if (selectedForMovement) {
			tileView.addHighlight(true);
			return;
		}
		
		tileView.setOpacity(0.8);
	}

}
