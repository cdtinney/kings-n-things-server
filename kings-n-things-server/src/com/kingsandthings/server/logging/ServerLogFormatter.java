package com.kingsandthings.server.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.kingsandthings.logging.LogLevel;

public class ServerLogFormatter extends Formatter {
	
	@Override
    public String format(final LogRecord r) {
    	
        StringBuilder sb = new StringBuilder();
        
        if (r.getLevel() == LogLevel.TRACE) {
        	sb.append(System.currentTimeMillis() + " ");
        }
        
       	sb.append(r.getLevel() + ": ");
        sb.append(formatMessage(r));
        
        return sb.toString();
        
    }
}
