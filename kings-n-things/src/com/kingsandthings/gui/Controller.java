package com.kingsandthings.gui;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import com.kingsandthings.gui.util.WindowUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;

public abstract class Controller {
	
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	protected void addEventHandler(Parent parent, String childId, String elementProperty, String handlerMethodName) {
		
		Object target = WindowUtils.findChildById(parent, childId);
		
		if (target == null) {
			LOGGER.warning("No child found with id - " + childId + " of parent id- " + parent.getId());
			return;
		}
		
		addEventHandler(target, elementProperty, handlerMethodName);
		
	}
	
	// TODO - comment this method
	protected void addEventHandler(Object target, String elementProperty, final String handlerMethodName) {
		
		if (target == null) {
			LOGGER.warning("Cannot add event handler to null target.");
			return;
		}
		
		try {
			
			final Controller instance = this;
			
			Method actionMethod = target.getClass().getMethod(elementProperty, EventHandler.class);
			
			actionMethod.invoke(target, new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent event) {
					
					try {
						Method method = instance.getClass().getMethod(handlerMethodName, ActionEvent.class);
						method.invoke(instance, event);
						
					} catch (Exception e) {
						e.printStackTrace();
						
					}
					
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
