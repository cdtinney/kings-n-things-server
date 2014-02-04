package com.kingsandthings.model;

import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.board.Tile;

public class Player {
	
	private static Logger LOGGER = Logger.getLogger(Player.class.getName());
	
	private String name;
	private Image controlMarker;
	
	private Tile startingTile;
	
	private int numGold = 0;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumGold() {
		return numGold;
	}
	
	public void setNumGold(int num) {
		
		if (num < 0) {
			LOGGER.warning("Cannot set gold value to a negative number.");
			return;
		}

		PropertyChangeDispatcher.getInstance().notify(this, "numGold", numGold, numGold = num);
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
