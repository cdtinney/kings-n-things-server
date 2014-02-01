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
		return new Image("/images/other/control_marker_1.png");
	}

}
