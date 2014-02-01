package com.kingsandthings.game.board;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Board;
import com.kingsandthings.model.board.Tile;

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
				
				final int row = i;
				final int col = j;
				
				tileView.setOnMouseClicked(new EventHandler<MouseEvent>() {
					
					@Override
					public void handle(MouseEvent event) {
						
						final Tile modelTile = tileView.getTile();
						if (modelTile == null) {
							return;
						}
						
						LOGGER.info("Tile clicked: [" + row + "] [" + col + "] " + modelTile.getType().toString());
						tileView.toggleActionMenu();
						
					}
					
				});
				
				// TODO - refactor this to use an ActionmMenuEventHandler
				tileView.getActionMenu().getItem("toggleControlMarker").setOnAction(new EventHandler<ActionEvent> () {

					@Override
					public void handle(ActionEvent event) {
						
						Player owner = tileView.getTile().getOwner();
						
						if (owner == null) {
							Player p = new Player("Colin");
							tileView.getTile().setOwner(p);
						} else {
							tileView.getTile().setOwner(null);
						}
						
						view.toggleControlMarker(tileView);
						
					};
				
				});
				
			}
		}
	}
	
}
