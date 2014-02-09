package com.kingsandthings.model.things;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.model.enums.Terrain;

@SuppressWarnings("serial")
public class Creature extends Thing {
	
	private static Logger LOGGER = Logger.getLogger(Creature.class.getName());
	
	public enum Ability {
		FLY,
		MAGIC,
		CHARGE,
		RANGE,
		SPECIAL,
		NONE	
	}
	
	private List<Ability> abilities;
	private Terrain terrainType;
	private int combatValue;
	
	public Creature(String name, String terrainType, int combatValue, Image image) {
		this(name, terrainType, null, combatValue, image);
	}
	
	public Creature(String name, String terrainType, List<String> abilities, int combatValue, Image image) {
		super(name, image);
		
		this.combatValue = combatValue;
		
		try {
			this.terrainType = Terrain.valueOf(terrainType.toUpperCase());
		} catch (IllegalArgumentException e) {
			LOGGER.warning(e.getMessage());
		}
		
		this.abilities = new ArrayList<Ability>();
		
		if (abilities != null) {

			try {
				
				for (String ability : abilities) {
					this.abilities.add(Ability.valueOf(ability.toUpperCase()));
				}
				
			} catch (IllegalArgumentException e) {
				LOGGER.warning(e.getMessage());
			}
			
		}
	
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
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder(super.toString());
		
		sb.append(" " + terrainType.toString());
		
		Map<Ability, String> map = new HashMap<Ability, String>() {{
			put(Ability.FLY, "^");
			put(Ability.MAGIC, "*");
			put(Ability.CHARGE, "C");
			put(Ability.RANGE, "R");
			put(Ability.MAGIC, "S");
			put(Ability.NONE, "");
		}};
		
		String abilityStr = " ";
		for (Ability a : abilities) {
			abilityStr += map.get(a);
		}
		
		sb.append(abilityStr + combatValue);
		return sb.toString();
		
	}
	
}
