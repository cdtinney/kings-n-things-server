package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;

public class Game {

	private static Logger LOGGER = Logger.getLogger(Game.class.getName());
	private static Game INSTANCE = null;
	
	private PhaseManager phaseManager = PhaseManager.getInstance();
	private PlayerManager playerManager = PlayerManager.getInstance();
	
	private final int NUM_INITIAL_THINGS = 10;
	
	private Cup cup;
	
	public static Game getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new Game();
		}
		
		return INSTANCE;
		
	}
	
	public void addPlayers(List<String> playerNames) {
		playerManager.setNumPlayers(playerNames.size());
		playerManager.addAllPlayers(playerNames);
	}
	
	public List<Player> getPlayers() {
		return playerManager.getPlayers();
	}
	
	public void begin() {
		playerManager.setFirstPlayerActive();
		phaseManager.beginPhases();
	}
	
	public Cup getCup() {
		
		if (cup == null) {
			cup = new Cup();
		}
		
		return cup;
	}
	
	// TASK - Demo only. Remove/move.
	public void addThingsToPlayer(List<Thing> things, Player player) {

		boolean success = player.getRack().addThings(things);
		
		if (success) {
			cup.removeThings(things);
			
			if (player.getRack().getThings().size() == NUM_INITIAL_THINGS) {
				PhaseManager.getInstance().endPlayerTurn();
			}
			
		}
		
	}
	
	// TASK - Demo only. Remove/move.
	public void addThingIndicesToPlayer(List<Integer> indices, Player player) {
		
		if (player == null) {
			LOGGER.warning("Cannot add Things to null player (is there an active player?)");
			return;
		}
		
		List<Thing> things = new ArrayList<Thing>();

		for (Integer i : indices) {
			things.add(cup.getThings().get(i));
		}
	}

}
