package com.kingsandthings.model.things;

import javafx.scene.image.Image;

public class Fort extends Thing {
	
	public enum Type {
		TOWER,
		KEEP,
		CASTLE,
		CITADEL
	}
	
	private Type type;
	private boolean neutralized = false;

	public Fort(String name, int combatValue, Image image) {
        super(name, image);
    }
	
	public Type getType() {
		return type;
	}
	
	public boolean isNeutralized() {
		return neutralized;
	}
	
}
