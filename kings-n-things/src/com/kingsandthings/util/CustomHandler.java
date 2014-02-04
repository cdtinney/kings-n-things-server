package com.kingsandthings.util;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.kingsandthings.game.GameView;

public class CustomHandler extends Handler {

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
		
		if (r.getLevel() == CustomLevel.STATUS) {
			GameView.setStatusText(r.getMessage());
			return;
		}
		
		System.out.println(getFormatter().format(r));
		
	}

}
