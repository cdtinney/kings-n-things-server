package com.kingsandthings.model.things;

import javafx.scene.image.Image;

public abstract class Thing {
	
	private String name;
	private Image image;

    public Thing(String name, Image image) {
    	this.name = name;
    	this.image = image;
    }
    
    public String getName() {
    	return name;
    }
    
    public Image getImage() {
    	return image;
    }
    
    @Override
    public String toString() {
    	return name;    	
    }

}
