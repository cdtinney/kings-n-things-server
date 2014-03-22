package com.kingsandthings.server.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ServerLogFormatter extends Formatter {
	
	@Override
    public String format(final LogRecord r) {
    	
        StringBuilder sb = new StringBuilder();
        
       	sb.append(r.getLevel() + ": ");
        sb.append(formatMessage(r));
        
        return sb.toString();
        
    }
}
