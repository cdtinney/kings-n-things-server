package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.model.things.Thing;
import com.kingsandthings.model.things.ThingImport;

public class Game {

	private static Logger LOGGER = Logger.getLogger(Game.class.getName());
	
	private static Game INSTANCE = null;
	
	private Cup cup;
	
	private Game() {
		
		// Add all the Things to the cup
		// TODO - treasures, income counters, etc
		cup = new Cup();
		cup.addThings(ThingImport.importCreatures());
	}
	
	public static Game getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new Game();
		}
		
		return INSTANCE;
		
	}
	
	public Cup getCup() {
		return cup;
	}
	
	/*
	 * TODO - remove. For demo only.
	 */
	public void addThingsToPlayer(List<Integer> indices, Player player) {
		
		if (player == null) {
			LOGGER.warning("Cannot add Things to null player (is there an active player?)");
			return;
		}
		
		List<Thing> things = new ArrayList<Thing>();

		for (Integer i : indices) {
			things.add(cup.getThings().get(i));
		}

		// Add them to the player's list of Things
		boolean success = player.getRack().addThings(things);
		
		// If they were successfully added, remove the Things from the cup
		if (success) {
			cup.removeThings(things);
		}
		
	}

}
