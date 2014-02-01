package com.kingsandthings.game.board;

import java.util.HashMap;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TileActionMenu extends ContextMenu {
	
	private HashMap<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		menuItems = new HashMap<String, MenuItem>();
		
		MenuItem toggleControlMarker = new MenuItem("Toggle Control Marker");
		menuItems.put("toggleControlMarker", toggleControlMarker);
		
		getItems().addAll(toggleControlMarker);
		
	}
	
	public MenuItem getItem(String id) {
		
		if (menuItems != null && menuItems.containsKey(id)) {
			return menuItems.get(id);
		}
		
		return null;
	}

}
