package com.kingsandthings.game.board;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.model.phase.Phase.Notification;
import com.kingsandthings.model.phase.StartingKingdomsPhase;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	private Map<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		menuItems = new LinkedHashMap<String, MenuItem>();

		menuItems.put("placeControlMarker", MenuItemBuilder.create().visible(false).text("Place control marker").build());
		
		getItems().addAll(menuItems.values());

		NotificationDispatcher.getInstance().addListener(StartingKingdomsPhase.class, Notification.BEGIN, this, "onStartingKingdomsPhaseBegin");
		NotificationDispatcher.getInstance().addListener(StartingKingdomsPhase.class, Notification.END, this, "onStartingKingdomsPhaseEnd");
		
	}
	
	public TileView getOwner() {
		return owner;
	}
	
	public MenuItem get(String id) {
		return menuItems.get(id);
	}
	
	public boolean visibleItems() {
		
		for (MenuItem item : getItems()) {
			if (item.isVisible()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	@SuppressWarnings("unused")
	private void onStartingKingdomsPhaseBegin() {
		get("placeControlMarker").setVisible(true);
	}
	
	@SuppressWarnings("unused")
	private void onStartingKingdomsPhaseEnd() {
		get("placeControlMarker").setVisible(false);
	}

}
