package com.kingsandthings.game.player;

import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.CustomDataFormat;
import com.kingsandthings.util.DataImageView;

public class PlayerPaneController extends Controller {
	
	private PlayerPane view;
	
	public void initialize(List<Player> players) {
		
		view = new PlayerPane(players);
		view.initialize();
		
		setupRackImageHandlers();
		
	}
	
	public Node getView() {
		return view;
	}
	
	private void setupRackImageHandlers() {
		
		for (final PlayerView playerView : view.getPlayerViews()) {
			
			for (DataImageView rackImage : playerView.getRackImageViews()) {
				addEventHandler(rackImage, "setOnMouseClicked", "handleRackImageClicked");
				addEventHandler(rackImage, "setOnDragDetected", "handleThingDragDetected");
			}
			
			for (DataImageView fortImage : playerView.getFortImageViews()) {
				addEventHandler(fortImage, "setOnDragDetected", "handleThingDragDetected");
				addEventHandler(fortImage, "setOnDragDone", "handleThingDragDone");
				
				// TODO - abstract this
				fortImage.addEventFilter(Event.ANY, new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						
						Player player = playerView.getPlayer();
						
						if (PlayerManager.getInstance().getActivePlayer() != player) {
							event.consume();
						}
						
						if (!PhaseManager.getInstance().getCurrentPhase().getName().equals("Tower Placement")) {
							event.consume();
						}
					}
				});
				
			}
		}
		
	}
	
	@SuppressWarnings("unused")
	private void handleRackImageClicked(Event event) {
			
		DataImageView imageView = (DataImageView) event.getSource();

		// TODO - do stuff
		Thing t = (Thing) imageView.getData();
		System.out.println(t.toString());
		
	}
	
	@SuppressWarnings("unused")
	private void handleThingDragDetected(Event event) {
		
		DataImageView imageView = (DataImageView) event.getSource();

		Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		
		content.put(CustomDataFormat.THING, (Thing) imageView.getData());
		content.put(DataFormat.IMAGE, imageView.getImage());
		
		db.setContent(content);
		
	}
	
	@SuppressWarnings("unused")
	private void handleThingDragDone(Event event) {
		
		DragEvent dragEvent = (DragEvent) event;
		
		if (dragEvent.getTransferMode() == null) {
			System.out.println("unsuccessful drop");
			return;
		} 
		
		System.out.println("successful drop");
		
	}

}
