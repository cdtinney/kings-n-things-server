package com.kingsandthings.logging;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javafx.application.Platform;

import com.kingsandthings.game.GameView;

public class LogHandler extends Handler {

	@Override
	public void close() throws SecurityException {
		// Do nothing
	}

	@Override
	public void flush() {
		// Do nothing
	}

	@Override
	public void publish(LogRecord r) {
		
		if (r.getLevel() == LogLevel.STATUS) {
			
			// Set the status message in the view
			GameView.setStatusText(r.getMessage());
			
			// Clear the status message after 10 seconds
			clearStatusMessage(10000, r.getMessage());
			
			return;
		}
		
		System.out.println(getFormatter().format(r));
		
	}
	
	private void clearStatusMessage(int timeElapsed, final String message) {

		new Timer().schedule(new TimerTask() {
			
		    public void run() {
		    	
		         Platform.runLater(new Runnable() {
		        	 
		            public void run() {
		            	
		            	String status = GameView.getStatusText().replace("STATUS: ", "");
		            	
		            	// Ignore if the message has changed
		            	if (!status.equals(message)) {
		            		return;
		            	}
		            	
		                GameView.setStatusText(null);
		            }
		            
		        });
		         
		    }
		    
		}, timeElapsed);
		
	}

}
