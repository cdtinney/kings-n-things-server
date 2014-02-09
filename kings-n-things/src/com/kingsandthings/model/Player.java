package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.board.Tile;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Creature;
import com.kingsandthings.model.things.Fort;
import com.kingsandthings.model.things.RandomEvent;
import com.kingsandthings.model.things.SpecialCharacter;
import com.kingsandthings.model.things.SpecialIncome;
import com.kingsandthings.model.things.Treasure;

public class Player {
	
	private static Logger LOGGER = Logger.getLogger(Player.class.getName());
	
	private String name;
	private Image controlMarker;
	
	private Tile startingTile;
	private List<Tile> controlledTiles;
	
	private int numGold = 0;
	
	private Rack rack;
	
	private List<Fort> forts;
	private List<SpecialIncome> specialIncomeCounters;
	private List<SpecialCharacter> specialCharacters;
	private List<Treasure> treasures;
	private List<RandomEvent> randomEvents;
	
	private List<Creature> creatures;
	
	public Player(String name) {
		this.name = name;
		
		rack = new Rack(this);
		
		controlledTiles = new ArrayList<Tile>();
		
		forts = new ArrayList<Fort>();
		specialIncomeCounters = new ArrayList<SpecialIncome>();
		specialCharacters = new ArrayList<SpecialCharacter>();
		treasures = new ArrayList<Treasure>();
		randomEvents = new ArrayList<RandomEvent>();
		
		creatures = new ArrayList<Creature>();
	}
	
	public Rack getRack() {
		return rack;
	}
	
	public List<Tile> getControlledTiles() {
		return controlledTiles;
	}
	
	public List<Fort> getForts() {
		return forts;
	}
	
	public boolean placeFort(Fort fort, Tile tile) {
		
		boolean success = tile.setFort(fort);
		if (success) {
			// Drag and drop creates a duplicate object, so we have to find it in the list
			forts.get(forts.indexOf(fort)).setPlaced(true);
		}

		PropertyChangeDispatcher.getInstance().notify(this, "forts", null, forts);
		return success;
		
	}
	
	public void addFort(Fort fort) {
		
		if (forts.contains(fort)) {
			LOGGER.warning("Fort already contained in player list");
			return;
		}

		List<Fort> oldForts = forts;
		forts.add(fort);
		PropertyChangeDispatcher.getInstance().notify(this, "forts", oldForts, forts);
		
	}
	
	public void removeFort(Fort fort) {
		
		if (!forts.contains(fort)) {
			LOGGER.warning("Fort not contained in player list.");
			return;
		}
		
		List<Fort> oldForts = forts;
		forts.remove(fort);
		PropertyChangeDispatcher.getInstance().notify(this, "forts", oldForts, forts);
		
	}
	
	public List<SpecialIncome> getSpecialIncomeCounters() {
		return specialIncomeCounters;
	}
	
	public List<SpecialCharacter> getSpecialCharacters() {
		return specialCharacters;
	}
	
	public List<Treasure> getTreasures() {
		return treasures;
	}
	
	public List<RandomEvent> getRandomEvents() {
		return randomEvents;
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumGold() {
		return numGold;
	}
	
	public void addGold(int num) {
		PropertyChangeDispatcher.getInstance().notify(this, "numGold", numGold, numGold = (numGold + num));
	}
	
	public void removeGold(int num) {
		PropertyChangeDispatcher.getInstance().notify(this, "numGold", numGold, numGold = (numGold - num));
	}
	
	public void setStartingTile(Tile tile) {
		startingTile = tile;
		startingTile.setOwner(this);
	}
	
	public Tile getStartingTile() {
		return startingTile;
	}
	
	public Image getControlMarker() {
		return controlMarker;
	}
	
	public void setControlMarker(Image controlMarker) {
		this.controlMarker = controlMarker;
	}

}
