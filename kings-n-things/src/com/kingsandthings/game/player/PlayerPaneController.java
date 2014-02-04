package com.kingsandthings.game.player;

import java.util.List;

import javafx.scene.Node;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;

public class PlayerPaneController extends Controller {
	
	private PlayerPane view;
	
	public PlayerPaneController() {
		
	}
	
	public void initialize(List<Player> players) {
		
		view = new PlayerPane(players);
		view.initialize();
		
	}
	
	public Node getView() {
		return view;
	}

}
