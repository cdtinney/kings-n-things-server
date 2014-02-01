package com.kingsandthings;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

import com.kingsandthings.util.WindowUtils;

public abstract class Controller {
	
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	protected void addEventHandler(Parent parent, String childId, String elementProperty, String handlerMethodName) {
		
		Object target = WindowUtils.lookup(parent, childId);
		
		if (target == null) {
			LOGGER.warning("No child found with id - #" + childId);
			return;
		}
		
		addEventHandler(target, elementProperty, handlerMethodName);
		
	}
	
	// TODO - comment this method and add more specific catch statements
	protected void addEventHandler(Object target, String eventName, final String handlerMethodName) {
		
		if (target == null) {
			LOGGER.warning("Cannot add event handler to null target.");
			return;
		}
		
		try {
			
			final Controller instance = this;
			
			Method actionMethod = target.getClass().getMethod(eventName, EventHandler.class);
			
			actionMethod.invoke(target, new EventHandler<Event> () {
				
				public void handle(Event event) {
					
					try {
						Method method = instance.getClass().getMethod(handlerMethodName, Event.class);
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
