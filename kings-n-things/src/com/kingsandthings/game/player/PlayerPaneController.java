package com.kingsandthings.game.player;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.DataImageView;
import com.kingsandthings.util.CustomDataFormat;

public class PlayerPaneController extends Controller {
	
	//public static DataFormat format = new DataFormat("object/thing");
	
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
			
			DataImageView imageView = (DataImageView) event.getSource();
			PlayerView playerView = (PlayerView) imageView.getParent();
			
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				playerView.showHoverImage(imageView);
				imageView.addBorder();
			}
			
			if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				playerView.hideHoverImage();
				imageView.removeBorder();
			}
			
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				
				// TODO - do stuff
				Thing t = (Thing) imageView.getData();
				System.out.println(t.toString());
				
			}
			
			if (event.getEventType() == MouseEvent.DRAG_DETECTED) {
				
				Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				
				content.put(CustomDataFormat.THING, (Thing) imageView.getData());
				content.put(DataFormat.IMAGE, imageView.getImage());
				
				db.setContent(content);
				
			}
			
		}
		
	}

}
