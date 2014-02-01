package com.kingsandthings.game.board;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		MenuItem toggleControlMarker = new MenuItem("Toggle Control Marker");
		toggleControlMarker.setId("toggleControlMarker");
		
		getItems().add(toggleControlMarker);
		
	}
	
	public TileView getOwner() {
		return owner;
	}
	
	public MenuItem get(String id) {
		
		for (MenuItem item:  getItems()) {
			
			String nodeId = item.getId();
			
			if (nodeId != null && nodeId.equals(id)) {
				return  item;
			}
			
		}
		
		return null;
	}

}
