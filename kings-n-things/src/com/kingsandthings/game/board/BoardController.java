package com.kingsandthings.game.board;

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
	
	// View
	private BoardView view;	
	
	/**
	 * Initialization of the controller should initialize the necessary
	 * model(s) and view(s), and set up event handling.
	 * 
	 * @param numPlayers
	 */
	public void initialize(List<Player> players) {
		
		int numPlayers = players.size();

		board = new Board(numPlayers);
		view = new BoardView();
		
		// Initialize view and set the tiles
		view.initialize();
		view.setTileImages(board.getTiles());
		
		// Set up starting tiles (TODO - move this to the initial phase)
		for (Player player : PlayerManager.getInstance().getPlayers()) {
			int pos = PlayerManager.getInstance().getPosition(player);
			board.setStartingTile(player, pos);
		}
		
		// Set up event handlers for clicking tiles
		setupTileClickHandlers();
		
	}
	
	/**
	 * The view of a controller should be the highest-level parent node.
	 * 
	 * @return 
	 */
	public Node getView() {
		return view;
	}
	
	/**
	 * Subscribe to click events for the TileView objects.
	 */
	private void setupTileClickHandlers() {
		
		TileView[][] tileViews = view.getTiles();
	
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
				
				// Action menu
				addEventHandler(tileView.getActionMenu().get("placeControlMarker"), "setOnAction", "handlePlaceControlMarkerMenuItem");
				
			}
		}
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
		tileView.toggleActionMenu();	
	}
	
	@SuppressWarnings("unused")
	private void handleTileMouseExit(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.setOpacity(1.0);
	}
	
	@SuppressWarnings("unused")
	private void handleTileMouseEnter(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.setOpacity(0.8);
	}

}
