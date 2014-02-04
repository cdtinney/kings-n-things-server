package com.kingsandthings.model;

import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.model.board.Tile;

public class Player {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(Player.class.getName());
	
	private String name;
	private Image controlMarker;
	
	private Tile startingTile;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
