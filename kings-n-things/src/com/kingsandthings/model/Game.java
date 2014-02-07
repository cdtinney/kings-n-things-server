package com.kingsandthings.model;

import java.util.List;

import com.kingsandthings.model.things.Creature;
import com.kingsandthings.model.things.ThingImport;

public class Game {
	
	private List<Creature> creatures;
	
	public Game() {
		creatures = ThingImport.importCreatures();
		
		printCreatures();
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}
	
	public void printCreatures() {
		
		System.out.println("# Creatures - " + creatures.size());
		
		for (Creature c : creatures) {
			System.out.print(c.getName() + " - " + c.getTerrainType().toString() + " - " + c.getCombatValue());
			
			for (Creature.Ability ability : c.getAbilities()) {
				System.out.print(" -" + ability.toString());
			}
			
			System.out.println(" (" + c.getImage().toString() + ")");
			
		}
	}

}
