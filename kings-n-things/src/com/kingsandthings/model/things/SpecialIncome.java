package com.kingsandthings.model.things;

import java.util.logging.Logger;

import com.kingsandthings.model.enums.Terrain;

import javafx.scene.image.Image;

public class SpecialIncome extends Thing {
	
	private static Logger LOGGER = Logger.getLogger(SpecialIncome.class.getName());
	
	private Terrain terrainType;

	public SpecialIncome(String name, String terrainType, Image image) {
		super(name, image);
		
		try {
			this.terrainType = Terrain.valueOf(terrainType.toUpperCase());
		} catch (IllegalArgumentException e) {
			LOGGER.warning(e.getMessage());
		}
	}
	
	public Terrain getTerrainType() {
		return terrainType;
	}
	
}
