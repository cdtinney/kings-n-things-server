package com.kingsandthings.game.board;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.model.phase.PhaseNotification;
import com.kingsandthings.model.phase.StartingKingdomsPhase;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	private Map<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		menuItems = new LinkedHashMap<String, MenuItem>();

		menuItems.put("placeControlMarker", new MenuItem("Place control marker"));
		
		getItems().addAll(menuItems.values());
		
		NotificationDispatcher.getDispatcher().addListener(StartingKingdomsPhase.class, PhaseNotification.END, this, "onStartingKingdomsPhaseEnd");
		
	}
	
	public TileView getOwner() {
		return owner;
	}
	
	public MenuItem get(String id) {
		return menuItems.get(id);
	}
	
	public boolean visibleItems() {
		
		int numVisible = 0;
		for (MenuItem item : getItems()) {
			if (item.isVisible()) {
				numVisible++;
			}
		}
		
		return numVisible > 0;
		
	}
	
	@SuppressWarnings("unused")
	private void onStartingKingdomsPhaseEnd() {
		get("placeControlMarker").setVisible(false);
	}

}
