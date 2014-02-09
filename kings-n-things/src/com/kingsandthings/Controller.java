package com.kingsandthings;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

import com.kingsandthings.util.NodeUtils;

public abstract class Controller {
	
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	protected void addEventHandler(Parent parent, String childId, String eventProperty, String handlerMethodName) {
		
		Object target = NodeUtils.lookup(parent, childId);
		
		if (target == null) {
			LOGGER.warning("No child found with selector '#" + childId + "' from parent id#" + parent.getId());
			return;
		}
		
		addEventHandler(target, eventProperty, handlerMethodName, null);
		
	}
	
	protected void addEventHandler(Object target, String eventProperty, final String handlerMethodName) {
		addEventHandler(target, eventProperty, handlerMethodName, null);
	}
	
	protected void addEventHandler(Object target, String eventProperty, final String handlerMethodName, final Map<String, Object> parameters) {
		
		if (target == null) {
			LOGGER.warning("Cannot add '" + eventProperty + "' event handler to null target (" + this.getClass().getSimpleName() + "." + handlerMethodName + ")");
			return;
		}
		
		try {
			
			final Controller instance = this;
			
			Method actionMethod = target.getClass().getMethod(eventProperty, EventHandler.class);
			
			actionMethod.invoke(target, new EventHandler<Event> () {
				
				public void handle(Event event) {
					
					try {
						
						if (parameters != null) {
							Method method = instance.getClass().getDeclaredMethod(handlerMethodName, Event.class, Map.class);
							method.setAccessible(true);
							method.invoke(instance, event, parameters);
							
						} else {
							Method method = instance.getClass().getDeclaredMethod(handlerMethodName, Event.class);
							method.setAccessible(true);
							method.invoke(instance, event);
							
						}
						
						
					} catch (NoSuchMethodException e) {
						LOGGER.warning("Event handler cannot find method " + instance.getClass().getSimpleName() 
								+ "." + handlerMethodName
								+ "(" + Event.class.getSimpleName() + ")");
						
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
