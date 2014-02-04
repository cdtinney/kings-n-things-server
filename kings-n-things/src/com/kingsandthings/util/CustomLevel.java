package com.kingsandthings.util;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class CustomLevel extends Level {
	
	public static final Level STATUS = new CustomLevel("STATUS", Level.INFO.intValue() + 1);

	public CustomLevel(String name, int value) {
		super(name, value);
	}

}
