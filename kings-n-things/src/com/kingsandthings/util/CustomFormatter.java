package com.kingsandthings.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Colin Tinney
 * 
 * Provides custom formatting support for log messages.
 *
 */
public class CustomFormatter extends Formatter {
	
    @Override
    public String format(final LogRecord r) {
    	
        StringBuilder sb = new StringBuilder();
        
       	sb.append(r.getLevel() + ": ");
        sb.append(r.getSourceClassName() + ":" + r.getSourceMethodName() + " - ");
        sb.append(formatMessage(r)).append(System.getProperty("line.separator"));
        
        return sb.toString();
    }
}