package com.kingsandthings.game.board;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.model.phase.MovementPhase;
import com.kingsandthings.model.phase.Phase.Notification;
import com.kingsandthings.model.phase.StartingKingdomsPhase;
import com.sun.xml.internal.bind.v2.schemagen.episode.Bindings;
import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding;

public class TileActionMenu extends ContextMenu {
	
	public TileView owner;
	
	private Map<String, MenuItem> menuItems;
	
	public TileActionMenu(TileView tileView) {
		
		owner = tileView;
		
		menuItems = new LinkedHashMap<String, MenuItem>();

		menuItems.put("placeControlMarker", MenuItemBuilder.create().visible(false).text("Place control marker").build());
		menuItems.put("selectThings", MenuItemBuilder.create().visible(false).text("Select things").build());
		
		getItems().addAll(menuItems.values());
		
		get("selectThings").visibleProperty().bind(MovementPhase.active);
		get("placeControlMarker").visibleProperty().bind(StartingKingdomsPhase.active);
		
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

}
