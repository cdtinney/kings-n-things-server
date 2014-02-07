package com.kingsandthings.game.player;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.DataImageView;

public class PlayerPaneController extends Controller {
	
	private PlayerPane view;
	
	public void initialize(List<Player> players) {
		
		view = new PlayerPane(players);
		view.initialize();
		
		setupHandlers();
		
	}
	
	public Node getView() {
		return view;
	}
	
	private void setupHandlers() {
		
		setupRackHandlers();
		
	}
	
	private void setupRackHandlers() {
		
		RackImageViewMouseHandler handler = new RackImageViewMouseHandler();
		
		for (PlayerView playerView : view.getPlayerViews()) {
			
			for (DataImageView rackImage : playerView.getRackImageViews()) {
				rackImage.addEventHandler(MouseEvent.ANY, handler);
			}
			
		}
		
	}
	
	private class RackImageViewMouseHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			
			DataImageView itemImageView = (DataImageView) event.getSource();
			PlayerView playerView = (PlayerView) itemImageView.getParent();
			
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				playerView.showHoverImage(itemImageView);
				itemImageView.addBorder();
			}
			
			if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				playerView.hideHoverImage();
				itemImageView.removeBorder();
			}
			
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				
				// TODO - do stuff?
				Thing t = (Thing) itemImageView.getData();
				System.out.println(t.toString());
				
			}
			
		}
		
	}

}
