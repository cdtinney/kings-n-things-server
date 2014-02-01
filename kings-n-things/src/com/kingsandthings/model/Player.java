package com.kingsandthings.model;

import javafx.scene.image.Image;

public class Player {
	
	private String name;
	private int initialPosition;
	
	public Player(String name, int initialPosition) {
		this.name = name;
		this.initialPosition = initialPosition;
	}
	
	public String getName() {
		return name;
	}
	
	public Image getControlMarker() {
		
		String image = "/images/other/control_marker_" + initialPosition + ".png";
		return new Image(image);
	
	}

}
