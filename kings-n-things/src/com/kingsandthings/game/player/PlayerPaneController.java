package com.kingsandthings.game.player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.PlayerManager;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.CustomDataFormat;
import com.kingsandthings.util.DataImageView;

public class PlayerPaneController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(PlayerPaneController.class.getName());
	
	// Model
	private List<Thing> selectedThings;
	
	// View
	private PlayerPane view;
	
	public void initialize(List<Player> players) {
		
		view = new PlayerPane(players);
		view.initialize();
		
		selectedThings = new ArrayList<Thing>();
		
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
				addEventHandler(rackImage, "setOnDragDone", "handleThingDragDone");
				
				// TASK - Demo only. Abstract this
				rackImage.addEventFilter(Event.ANY, new EventHandler<Event> (){

					@Override
					public void handle(Event event) {
						
						Player player = playerView.getPlayer();
						if (PlayerManager.getInstance().getActivePlayer() != player) {
							event.consume();
						}
						
						if (!PhaseManager.getInstance().getCurrentPhase().getStep().equals("Thing_Placement")) {
							event.consume();
						}
						
					}
					
				});
			}
			
			for (DataImageView fortImage : playerView.getFortImageViews()) {
				addEventHandler(fortImage, "setOnDragDetected", "handleThingDragDetected");
				addEventHandler(fortImage, "setOnDragDone", "handleThingDragDone");
				
				// TODO - abstract this
				fortImage.addEventFilter(Event.ANY, new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						
						Player player = playerView.getPlayer();
						
						// Allow drag done events to propagate. The active player may have changed
						// before the event was fired.
						if (event.getEventType() == DragEvent.DRAG_DONE) {
							return;
						}
						
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
		Thing thing = (Thing) imageView.getData();
		
		if (selectedThings.contains(thing)) {
			selectedThings.remove(thing);
			imageView.setSelected(false);
			
		} else {
			selectedThings.add(thing);
			imageView.setSelected(true);
			
		}
		
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	private void handleThingDragDetected(Event event) {
		
		DataImageView imageView = (DataImageView) event.getSource();
		
		// Handle the case where the user simply wants to drag one Thing
		if (selectedThings.isEmpty()) {
			selectedThings.add((Thing) imageView.getData());
		}

		Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		
		content.put(CustomDataFormat.THINGS, selectedThings);
		
		List<String> imageUrls = new ArrayList<String>();
		for (Thing thing : selectedThings) {
			imageUrls.add(thing.getImage().impl_getUrl());
		}
		
		content.put(CustomDataFormat.IMAGES, imageUrls);
		db.setContent(content);
		
	}
	
	@SuppressWarnings("unused")
	private void handleThingDragDone(Event event) {
		
		DragEvent dragEvent = (DragEvent) event;
		
		if (dragEvent.getTransferMode() == null) {
			LOGGER.info("Unsuccessful drag and drop.");
			return;
		} 
		
		LOGGER.info("Successful drag and drop. Clearing selected Things.");
		selectedThings.clear();
		
	}

}
