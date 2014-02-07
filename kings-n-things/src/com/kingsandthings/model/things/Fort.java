package com.kingsandthings.model.things;

import java.util.logging.Logger;

import javafx.scene.image.Image;

public class Fort extends Thing {
	
	private static Logger LOGGER = Logger.getLogger(Fort.class.getName());
	
	public enum Type {
		TOWER,
		KEEP,
		CASTLE,
		CITADEL
	}
	
	private Type type;
	private boolean neutralized = false;

	public Fort(String type, int combatValue, Image image) {
        super(type, image);
        
        try {
        	this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
        	LOGGER.warning("Invalid fort type - " + type);
        }
        
    }
	
	public Type getType() {
		return type;
	}
	
	public boolean isNeutralized() {
		return neutralized;
	}
	
}
