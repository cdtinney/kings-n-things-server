package com.kingsandthings.game.board;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.game.phase.PhaseNotification;
import com.kingsandthings.game.phase.StartingKingdomsPhase;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	private Map<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		menuItems = new HashMap<String, MenuItem>();
		
		menuItems.put("toggleControlMarker", new MenuItem("Toggle Control Marker"));
		menuItems.put("placeThing", new MenuItem("Place Thing"));
		
		getItems().addAll(menuItems.values());
		
		NotificationDispatcher.getDispatcher().addListener(StartingKingdomsPhase.class, PhaseNotification.END, this, "onStartingKingdomsPhaseEnd");
		
	}
	
	public TileView getOwner() {
		return owner;
	}
	
	public MenuItem get(String id) {
		return menuItems.get(id);
	}
	
	@SuppressWarnings("unused")
	private void onStartingKingdomsPhaseEnd() {
		get("toggleControlMarker").setVisible(false);
		
	}

}
