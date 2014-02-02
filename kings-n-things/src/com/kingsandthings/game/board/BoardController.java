package com.kingsandthings.game.board;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Board;
import com.kingsandthings.model.board.Tile;

public class BoardController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(BoardController.class.getName());

	// Model
	private Board board;		
	
	// View
	private BoardView view;		
	
	/**
	 * Default constructor,
	 */
	public BoardController() {
		
	}
	
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
				
				// TODO - refactor event handling for action menu items
				addEventHandler(tileView.getActionMenu().get("toggleControlMarker"), "setOnAction", "handleToggleMarkerMenuItem");
				
			}
		}
	}
	
	/**
	 * Tile context menu event handling
	 * 
	 * @param event
	 */
	protected void handleToggleMarkerMenuItem(Event event) {
		
		MenuItem item = (MenuItem) event.getSource();
		
		TileActionMenu tileActionMenu = (TileActionMenu) item.getParentPopup();
		TileView tileView = tileActionMenu.getOwner();
		
		Player owner = tileView.getTile().getOwner();
		
		// TODO - get current player (perhaps using some manager class)
		Player p = new Player("Colin");
		
		boolean success = false;
		
		if (owner == null) {
			success = board.setTileControl(tileView.getTile(), p, true);

			// TODO - dialogs
			if (success) {
				LOGGER.info("Control marker placed successfully.");
				view.toggleControlMarker(tileView);
			} else {
				LOGGER.info("Control marker not placed");
			}
			
		} else {
			
			success = board.setTileControl(tileView.getTile(), p, false);
			
			if (success) {
				LOGGER.info("Control marker removed successfully.");
			} else {
				LOGGER.info("Control marker not removed successfully - player does not control the tile.");
			}
			
		}
		
		if (success) {
			view.toggleControlMarker(tileView);
		}
		
	}
	
	/**
	 * Tile click event handling
	 * 
	 * @param event
	 */
	protected void handleTileClick(Event event) {
		
		TileView tileView = (TileView) event.getSource();
		
		List<Tile> neighbours = tileView.getTile().getNeighbours();
		
		for (TileView[] t : view.getTiles()) {
			for (TileView tt : t) {
				if (tt == null) continue;
				
				if (neighbours.contains(tt.getTile())) {
					tt.setOpacity(0.6);
				} else {
					tt.setOpacity(1.0);
				}
			}
		}
		
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
