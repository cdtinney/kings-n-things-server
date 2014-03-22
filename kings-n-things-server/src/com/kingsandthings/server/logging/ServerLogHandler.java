package com.kingsandthings.server.logging;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.server.ServerView;

public class ServerLogHandler extends Handler {
	
	private ServerView view;
	
	public static void setHandler(Logger logger, ServerView view) {
	
		Logger parent = logger.getParent();
		
		for (Handler handler: parent.getHandlers()) {
			parent.removeHandler(handler);
		}
		
		ServerLogHandler customHandler = new ServerLogHandler();
		customHandler.setFormatter(new ServerLogFormatter());
		customHandler.setLevel(LogLevel.DEBUG);	
		customHandler.setView(view);
		
		parent.setLevel(LogLevel.DEBUG);
		parent.addHandler(customHandler);
		
	}
	
	public void setView(ServerView view) {
		this.view = view;
	}

	@Override
	public void publish(LogRecord r) {
		
		String log = getFormatter().format(r);
		
		if (view != null) {
			view.appendToLog(log);
		} else {
			System.out.println(log);
		}
		
	}

	@Override
	public void close() throws SecurityException {
		// Do nothing
	}

	@Override
	public void flush() {
		// Do nothing
	}
}
