package com.kingsandthings.model;

import javafx.scene.image.Image;

public class Player {
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Image getControlMarker() {
		
		// TODO - get image based on position from manager class
		String image = "/images/other/control_marker_0.png";
		return new Image(image);
	
	}

}
