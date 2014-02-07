package com.kingsandthings.model.things;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.model.enums.Terrain;

public class Creature extends Thing {
	
	private static Logger LOGGER = Logger.getLogger(Creature.class.getName());
	
	public enum Ability {
		FLYING,
		MAGIC,
		CHARGING,
		RANGE,
		SPECIAL,
		NONE	
	}
	
	private String name;
	private List<Ability> abilities;
	private Terrain terrainType;
	private int combatValue;
	
	public Creature(String name, String terrainType, String ability, int combatValue, Image image) {
		super(name, image);
		
		this.combatValue = combatValue;
		
		try {
			this.terrainType = Terrain.valueOf(terrainType.toUpperCase());
		} catch (IllegalArgumentException e) {
			LOGGER.warning(e.getMessage());
		}
		
		abilities = new ArrayList<Ability>();
	
	}
	
	public String getName() {
		return name;
	}
	
	public Terrain getTerrainType() {
		return terrainType;
	}
	
	public List<Ability> getAbilities() {
		return abilities;
	}
	
	public int getCombatValue() {
		return combatValue;
	}
	
}
