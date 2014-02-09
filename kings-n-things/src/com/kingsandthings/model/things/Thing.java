package com.kingsandthings.model.things;

import java.io.Serializable;

import javafx.scene.image.Image;

@SuppressWarnings("serial")
public abstract class Thing implements Serializable {
	
	public int id;
	
	protected String name;
	private transient Image image;

    public Thing(String name, Image image) {
    	this.name = name;
    	this.image = image;
    	
    	this.id = System.identityHashCode(this);
    }
    
    public String getName() {
    	return name;
    }
    
    public Image getImage() {
    	return image;
    }
    
    public void setImage(Image image) {
    	this.image = image;
    }
    
    @Override
    public boolean equals(Object other) {
    	
    	Thing thing = (Thing) other;
    	return thing.id == this.id;   
    	
    }
    
    @Override
    public String toString() {
    	return name;   	
    }

}
