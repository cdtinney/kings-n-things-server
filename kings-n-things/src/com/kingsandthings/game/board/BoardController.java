package com.kingsandthings.game.board;

import javafx.scene.Node;

import com.kingsandthings.Controller;
import com.kingsandthings.game.View;

public class BoardController extends Controller {
	
	private BoardView view;
	
	public BoardController() {
		
	}
	
	public void initialize(int numPlayers) {
		
		view = new BoardView(numPlayers);
		view.initialize();
		
	}
	
	public View<Node> getView() {
		return view;
	}
	
}
