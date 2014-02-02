package com.kingsandthings.model;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.model.board.Tile;

public class Player {
	
	private static Logger LOGGER = Logger.getLogger(Player.class.getName());
	
	private String name;
	private Image controlMarker;
	
	private Set<Tile> controlledTiles;
	private Tile startingTile;
	
	private int INITIAL_LIMIT = 3;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addControlledTile(Tile tile) {
		
		if (controlledTiles == null) {
			controlledTiles = new HashSet<Tile>();
		}
		
		if (controlledTiles.size() >=  INITIAL_LIMIT) { 
			LOGGER.info("Only " + INITIAL_LIMIT + " control markers can be placed in the Starting Kingdoms phase.");
			return;
		}
		
		tile.setOwner(this);
		controlledTiles.add(tile);
		
		if (controlledTiles.size() == INITIAL_LIMIT) {
			LOGGER.info("Player '" + name + "' placed last control marker.");
			PlayerManager.INSTANCE.nextPlayer();
		}
		
	}
	
	public void removeControlledTile(Tile tile) {
		tile.setOwner(null);
		controlledTiles.remove(tile);
	}
	
	public void setStartingTile(Tile tile) {
		startingTile = tile;
		addControlledTile(tile);
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
