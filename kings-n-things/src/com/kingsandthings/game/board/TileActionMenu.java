package com.kingsandthings.game.board;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.model.phase.PhaseNotification;
import com.kingsandthings.model.phase.StartingKingdomsPhase;
import com.kingsandthings.model.phase.TowerPlacementPhase;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	private Map<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		menuItems = new LinkedHashMap<String, MenuItem>();

		menuItems.put("placeControlMarker", new MenuItem("Place control marker"));
		menuItems.put("placeTower", new MenuItem("Place tower"));
		
		menuItems.get("placeTower").setVisible(false);
		
		getItems().addAll(menuItems.values());
		
		NotificationDispatcher.getDispatcher().addListener(StartingKingdomsPhase.class, PhaseNotification.END, this, "onStartingKingdomsPhaseEnd");
		NotificationDispatcher.getDispatcher().addListener(TowerPlacementPhase.class, PhaseNotification.BEGIN, this, "onTowerPlacementPhaseBegin");
		
	}
	
	public TileView getOwner() {
		return owner;
	}
	
	public MenuItem get(String id) {
		return menuItems.get(id);
	}
	
	@SuppressWarnings("unused")
	private void onStartingKingdomsPhaseEnd() {
		get("placeControlMarker").setVisible(false);
	}
	
	@SuppressWarnings("unused")
	private void onTowerPlacementPhaseBegin() {
		get("placeTower").setVisible(true);
	}

}
