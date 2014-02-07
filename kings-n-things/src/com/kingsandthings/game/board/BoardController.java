package com.kingsandthings.game.board;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import com.kingsandthings.Controller;
import com.kingsandthings.game.phase.PhaseManager;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Board;

public class BoardController extends Controller {
	
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
		
		// Set up starting tiles
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
				
				// TODO - refactor event handling for action menus (currently, every single menu has an individual handler)
				addEventHandler(tileView.getActionMenu().get("addControlMarker"), "setOnAction", "handleAddControlMarkerMenuItem");
				addEventHandler(tileView.getActionMenu().get("placeTower"), "setOnAction", "handlePlaceTowerMenuItem");
				
			}
		}
	}
	
	protected void handlePlaceTowerMenuItem(Event event) {
		
		LOGGER.info("Handle add tower menu");
		
	}
	
	protected void handleAddControlMarkerMenuItem(Event event) {
		
		MenuItem item = (MenuItem) event.getSource();
		
		TileActionMenu tileActionMenu = (TileActionMenu) item.getParentPopup();
		TileView tileView = tileActionMenu.getOwner();
		
		Player player = PlayerManager.getInstance().getActivePlayer();
		
		if (board.setTileControl(tileView.getTile(), player, true)) {
			PhaseManager.getInstance().endPlayerTurn();
		}
		
	}
	
	/**
	 * Tile click event handling
	 * 
	 * @param event
	 */
	protected void handleTileClick(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.toggleActionMenu();
	}
	
	/**
	 * Tile mouse exit event handling
	 * 
	 * @param event
	 */
	protected void handleTileMouseExit(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.setOpacity(1.0);
	}
	
	/**
	 * Tile mouse enter event handling
	 * 
	 * @param event
	 */
	protected void handleTileMouseEnter(Event event) {
		TileView tileView = (TileView) event.getSource();
		tileView.setOpacity(0.8);
	}

}
