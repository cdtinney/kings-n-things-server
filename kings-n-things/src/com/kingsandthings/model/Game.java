package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.model.board.Board;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;

public class Game {

	private static Logger LOGGER = Logger.getLogger(Game.class.getName());

	private PlayerManager playerManager = new PlayerManager();
	private PhaseManager phaseManager;
	
	private final int NUM_INITIAL_THINGS = 10;
	
	private Cup cup;
	private Board board;
	
	public Game() {
		cup = new Cup();
		board = new Board(this);

		phaseManager = new PhaseManager(this);
	}
	
	public Player getActivePlayer() {
		return playerManager.getActivePlayer();
	}
	
	public PhaseManager getPhaseManager() {
		return phaseManager;
	}
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Cup getCup() {
		return cup;
	}
	
	public void begin() {
		playerManager.setFirstPlayerActive();
		phaseManager.beginPhases();
	}
	
	public void addPlayers(List<String> playerNames) {
		playerManager.setNumPlayers(playerNames.size());
		playerManager.addAllPlayers(playerNames);
		
		board.generateBoard(playerNames.size());
	}	
	
	public void addInitialThingsToPlayer(List<Thing> things, Player player) {

		boolean success = player.getRack().addThings(things);
		
		if (success) {
			cup.removeThings(things);
			
			if (player.getRack().getThings().size() == NUM_INITIAL_THINGS) {
				phaseManager.endPlayerTurn();
			}
			
		}
		
	}
	
	public void addThingIndicesToPlayer(List<Integer> indices, Player player) {
		
		if (player == null) {
			LOGGER.warning("Cannot add Things to null player (is there an active player?)");
			return;
		}
		
		List<Thing> things = new ArrayList<Thing>();

		for (Integer i : indices) {
			things.add(cup.getThings().get(i));
		}
		
		addInitialThingsToPlayer(things, player);
	}

}
