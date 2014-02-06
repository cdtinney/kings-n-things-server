package com.kingsandthings.game;

import javafx.scene.Node;

import com.kingsandthings.Controller;

public class GameActionController extends Controller {

	// View
	private GameActionView view;
	
	public void initialize() {
		
		view = new GameActionView();
		view.initialize();
		
	}
	
	public Node getView() {
		return view;
	}
	
}
