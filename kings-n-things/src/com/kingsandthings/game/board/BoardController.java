package com.kingsandthings.game.board;

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Board;

public class BoardController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(BoardController.class.getName());

	private Board board;		// Model
	private BoardView view;		// View
	
	/* 
	 * Default constructor.
	 */
	public BoardController() {
		
	}
	
	/*
	 * Initialize controller. This method should initialize the view associated
	 * with the controller.
	 */
	public void initialize(int numPlayers) {

		board = new Board(numPlayers);
		view = new BoardView(numPlayers);
		
		// Initialize view and set the tiles
		view.initialize();
		view.setTileImages(board.getTiles());
		
		// Set up event handlers for clicking tiles
		setupTileClickHandlers();
		
	}
	
	/*
	 * Returns the view associated with the controller, as a Node.
	 */
	public Node getView() {
		return view;
	}
	
	/*
	 * Subscribe to click events for the TileView elements.
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
				
				// TODO - refactor event handling for action menu items
				addEventHandler(tileView.getActionMenu().get("toggleControlMarker"), "setOnAction", "handleToggleMarkerMenuItem");
				
			}
		}
	}
	
	/*
	 * Event handling.
	 */
	public void handleToggleMarkerMenuItem(Event event) {
		
		MenuItem item = (MenuItem) event.getSource();
		
		TileActionMenu tileActionMenu = (TileActionMenu) item.getParentPopup();
		TileView tileView = tileActionMenu.getOwner();
		
		Player owner = tileView.getTile().getOwner();
		
		if (owner == null) {
			// TODO - get current player
			Player p = new Player("Colin");
			tileView.getTile().setOwner(p);
		} else {
			tileView.getTile().setOwner(null);
		}
		
		view.toggleControlMarker(tileView);
		
	}
	
	/*
	 * Event handling.
	 */
	public void handleTileClick(Event event) {
		
		TileView tileView = (TileView) event.getSource();
		
		tileView.toggleActionMenu();
	}
	
}
