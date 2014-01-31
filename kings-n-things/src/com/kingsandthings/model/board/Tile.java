package com.kingsandthings.model.board;

import javafx.scene.image.Image;

import com.kingsandthings.model.enums.Terrain;

public class Tile {
	
	private Terrain type = null;
	
	public Tile(Terrain type) {
		this.type = type;
	}
	
	public Terrain getType() {
		return type;
	}
	
	public Image getImage() {
		
		String path = "/images/hex/tile_" + type.toString().toLowerCase() + ".png";
		return new Image(path);
		
	}

}
