package com.kingsandthings.game.player;

import java.util.List;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
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
		
		for (PlayerView playerView : view.getPlayerViews()) {
			
			for (DataImageView rackImage : playerView.getRackImageViews()) {
				addEventHandler(rackImage, "setOnMouseClicked", "handleRackImageClicked");
				addEventHandler(rackImage, "setOnDragDetected", "handleRackImageDragDetected");
			}
			
			for (DataImageView fortImage : playerView.getFortImageViews()) {
				addEventHandler(fortImage, "setOnMouseClicked", "handleRackImageClicked");
				addEventHandler(fortImage, "setOnDragDetected", "handleRackImageDragDetected");
				addEventHandler(fortImage, "setOnDragDone", "handleRackImageDragDone");
			}
		}
		
	}
	
	@SuppressWarnings("unused")
	private void handleFortImageClicked(Event event) {

		DataImageView imageView = (DataImageView) event.getSource();
		System.out.println("fort image clicked");
		
	}
	
	@SuppressWarnings("unused")
	private void handleFortImageDragDetected(Event event) {
		
	}
	
	@SuppressWarnings("unused")
	private void handleRackImageClicked(Event event) {
			
		DataImageView imageView = (DataImageView) event.getSource();

		// TODO - do stuff
		Thing t = (Thing) imageView.getData();
		System.out.println(t.toString());
		
	}
	
	@SuppressWarnings("unused")
	private void handleRackImageDragDetected(Event event) {
		
		DataImageView imageView = (DataImageView) event.getSource();

		Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		
		content.put(CustomDataFormat.THING, (Thing) imageView.getData());
		content.put(DataFormat.IMAGE, imageView.getImage());
		
		db.setContent(content);
		
	}
	
	@SuppressWarnings("unused")
	private void handleRackImageDragDone(Event event) {
		
		DragEvent dragEvent = (DragEvent) event;
		
		if (dragEvent.getTransferMode() == null) {
			System.out.println("unsuccessful drop");
		} else {
			System.out.println("successful drop");
		}
		
	}

}
