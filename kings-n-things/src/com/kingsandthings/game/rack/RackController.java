package com.kingsandthings.game.rack;

import java.util.List;

import javafx.scene.Node;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;

public class RackController extends Controller {
	
	private RackView view;
	
	public RackController() {
		
	}
	
	public void initialize(List<Player> players) {
		
		view = new RackView(players);
		view.initialize();
		
	}
	
	public Node getView() {
		return view;
	}

}
