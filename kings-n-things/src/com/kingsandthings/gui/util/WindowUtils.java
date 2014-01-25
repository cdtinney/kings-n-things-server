package com.kingsandthings.gui.util;

import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class WindowUtils {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(WindowUtils.class.getName());
	
	@SuppressWarnings("unchecked")
	public static <T> T findChildById(Parent parent, String id) {
		
		String nodeId;
		
		if (parent instanceof MenuBar) {
			
			for (Menu menu : ((MenuBar) parent).getMenus()) {
				for (MenuItem item : menu.getItems()) {
					nodeId = item.getId();
					
					if (nodeId != null && nodeId.equals(id)) {
						return (T) item;
					}
				}
			}
		}
		
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodeId = node.getId();
			
			if (nodeId != null && nodeId.equals(id)) {
				return (T) node;
			} else if (node instanceof Parent) {
				
				T child = findChildById((Parent) node, id);
				
				if (child != null) {
					return child;
				}
			}
		}
		
		return null;
	}
	

}
