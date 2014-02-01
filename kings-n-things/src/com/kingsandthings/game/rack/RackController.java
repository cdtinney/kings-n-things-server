package com.kingsandthings.game.rack;

import javafx.scene.Node;

import com.kingsandthings.Controller;

public class RackController extends Controller {
	
	private RackView view;
	
	public RackController() {
		
	}
	
	public void initialize(int numPlayers) {
		
		view = new RackView(numPlayers);
		view.initialize();
		
	}
	
	public Node getView() {
		return view;
	}

}
