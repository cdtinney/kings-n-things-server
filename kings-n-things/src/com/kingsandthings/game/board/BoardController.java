package com.kingsandthings.game.board;

import javafx.scene.Node;

import com.kingsandthings.Controller;
import com.kingsandthings.model.board.Board;

public class BoardController extends Controller {

	// Model
	private Board board;
	
	// View
	private BoardView view;
	
	public BoardController() {
		
	}
	
	public void initialize(int numPlayers) {

		board = new Board(numPlayers);
		view = new BoardView(numPlayers);
		
		// Initialize view and set the tiles
		view.initialize();
		view.setTiles(board.getTiles());
		
	}
	
	public Node getView() {
		return view;
	}
	
}
