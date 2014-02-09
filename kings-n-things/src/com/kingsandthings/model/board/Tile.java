package com.kingsandthings.model.board;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.enums.Terrain;
import com.kingsandthings.model.things.Fort;
import com.kingsandthings.model.things.Thing;

public class Tile {
	
	private static Logger LOGGER = Logger.getLogger(Tile.class.getName());
	
	private static final Image defaultImg = new Image("/images/tiles/back.png");
	
	private Image image;

	private Player owner;
	private List<Tile> neighbours;
	
	private Terrain type = null;
	private List<Thing> things;
	
	private Fort fort;
	
	private boolean discovered = false;
	
	public Tile(Terrain type) {
		
		this.type = type;
		
		things = new ArrayList<Thing>();
		neighbours = new ArrayList<Tile>();

		image = new Image("/images/tiles/" + type.toString().toLowerCase() + ".png");
		
	}
	
	public boolean isDiscovered() {
		return discovered;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player player) {
		
		player.getControlledTiles().add(this);
		discovered = true;
		
		PropertyChangeDispatcher.getInstance().notify(this, "owner", owner, owner = player);
	}
	
	public List<Tile> getNeighbours() {
		return neighbours;
	}
	
	public void setNeighbours(List<Tile> neighbours) {
		this.neighbours = neighbours;
	}
	
	public Fort getFort() {
		return fort;
	}
	
	public boolean setFort(Fort fort) {
		
		if (this.fort != null) {
			LOGGER.log(LogLevel.STATUS, "This tile already contains a fort.");
			return false;
		}

		PropertyChangeDispatcher.getInstance().notify(this, "fort", this.fort, this.fort = fort);
		return true;
	}
	
	public List<Thing> getThings() {
		return things;
	}
	
	public boolean addThing(Thing thing) {
		
		if (things.contains(thing)) {
			LOGGER.info("Tile already contains Thing: " + thing);
			return false;
		} 
		
		return things.add(thing);
		
	}
	
	public void removeThing(Thing thing) {
		
		boolean contains = things.remove(thing);
		if (!contains) {
			LOGGER.info("Tile does not contain Thing: " + thing);
		}
		
	}
	
	public Terrain getType() {
		return type;
	}
	
	public Image getImage() {
		
		if (!discovered) {
			return defaultImg;
		}
		
		return image;		
	}

}
