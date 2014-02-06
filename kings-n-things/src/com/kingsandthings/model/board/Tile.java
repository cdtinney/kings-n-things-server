package com.kingsandthings.model.board;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.enums.Terrain;
import com.kingsandthings.model.things.Thing;

public class Tile {
	
	private static Logger LOGGER = Logger.getLogger(Tile.class.getName());

	private Player owner;
	private List<Tile> neighbours;
	
	private Terrain type = null;
	private List<Thing> things;
	
	/* 
	 * Constructor
	 */
	public Tile(Terrain type) {
		
		this.type = type;
		
		things = new ArrayList<Thing>();
		neighbours = new ArrayList<Tile>();
		
	}
	
	/*
	 * Returns the player who controls the tile.
	 */
	public Player getOwner() {
		return owner;
	}
	
	/*
	 * Sets the player who controls the tile.
	 */
	public void setOwner(Player player) {
		PropertyChangeDispatcher.getInstance().notify(this, "owner", owner, owner = player);
	}
	
	public List<Tile> getNeighbours() {
		return neighbours;
	}
	
	public void setNeighbours(List<Tile> neighbours) {
		this.neighbours = neighbours;
	}
	
	/*
	 * Returns a list of Things currently in place on the Tile.
	 */
	public List<Thing> getThings() {
		return things;
	}
	
	/*
	 * Adds a Thing to the Tile.
	 */
	public void addThing(Thing thing) {
		
		if (things.contains(thing)) {
			// TODO - descriptive log statement using Thing attributes
			LOGGER.info("Tile already contains Thing - ");
			return;
		} 
		
		things.add(thing);
		
	}
	
	/*
	 * Removes a Thing from the Tile.
	 */
	public void removeThing(Thing thing) {
		
		boolean contains = things.remove(thing);
		if (!contains) {
			// TODO - descriptive log statement using Thing attributes
			LOGGER.info("Tile does not contain Thing - " );
		}
		
	}
	
	/* 
	 * Returns the terrain type of the tile.
	 */
	public Terrain getType() {
		return type;
	}
	
	/*
	 * Returns the image associated with this particular tile, based on terrain type.
	 */ 
	public Image getImage() {
		
		Image image = null;
		String path = "/images/tiles/" + type.toString().toLowerCase() + ".png";
		
		try {
			image = new Image(path);
			  
		} catch (IllegalArgumentException e) {
			LOGGER.warning("Hex tile image not found - " + path);
			
		}
		
		return image;		
		
	}

}
