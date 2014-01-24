package com.kingsandthings.gui;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class Controller implements Initializable {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	protected void addEventHandler(Object element, String elementProperty, final String handlerMethod) {
		
		try {
			
			final Controller instance = this;
			
			Method actionMethod = element.getClass().getMethod(elementProperty, EventHandler.class);
			
			actionMethod.invoke(element, new EventHandler<ActionEvent> () {
				
				public void handle(ActionEvent event) {
					
					try {
						Method method = instance.getClass().getMethod(handlerMethod, ActionEvent.class);
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
