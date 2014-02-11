package com.kingsandthings.game.board;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;

import com.kingsandthings.Controller;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Tile;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.DataImageView;

public class ExpandedTileController extends Controller {

	// Model
	private List<Thing> selectedThings;
	
	// View
	private ExpandedTileView view;
	
	public void initialize(List<Player> players) {
		
		view = new ExpandedTileView();
		
		view.setPlayers(players);
		view.initialize();
		view.setVisible(false);
		
		selectedThings = new ArrayList<Thing>();
		
		addEventHandler(view, "close", "setOnMouseClicked", "onClose");
		
		for (DataImageView imgView : view.getImageViews()) {
			addEventHandler(imgView, "setOnMouseClicked", "handleThingImageClicked");
		}
		
	}
	
	public ExpandedTileView getView() {
		return view;
	}
	
	public List<Thing> getSelectedThings() {
		return selectedThings;
	}
	
	public void show(Tile tile) {
		
		selectedThings.clear();

		view.clear();
		view.setTile(tile);
		view.updatePlayerThings();
		view.setVisible(true);
		
	}
	
	public void hideView() {
		view.clear();
	}
	
	@SuppressWarnings("unused")
	private void handleThingImageClicked(Event event) {
			
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
	
	@SuppressWarnings("unused")
	private void onClose(Event event) {
		view.clear();
		selectedThings.clear();
	}

}
