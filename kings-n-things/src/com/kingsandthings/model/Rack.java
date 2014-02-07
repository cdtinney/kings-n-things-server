package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.things.Thing;

public class Rack {
	
	private static Logger LOGGER = Logger.getLogger(Rack.class.getName());
	
	private final int MAX_THINGS = 10;
	
	private ArrayList<Thing> things;
	
	private Player owner;
	
	public Rack(Player owner) {
		this.owner = owner;
		
		things = new ArrayList<Thing>();
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public ArrayList<Thing> getThings() {
		return things;
	}
	
	public boolean addThings(List<Thing> list) {
		
		if (things.size() + list.size() > MAX_THINGS) {
			LOGGER.log(LogLevel.STATUS, "Cannot add " + list.size() + " Things due to limit (current: " + things.size() + " maximum: " + MAX_THINGS + ")");
			return false;
		}
		
		things.addAll(list);
		PropertyChangeDispatcher.getInstance().notify(this, "things", null, things);
		return true;
		
	}
	
	public boolean addThing(Thing thing) {
		
		if (things.size() == MAX_THINGS) {
			LOGGER.log(LogLevel.STATUS, "Cannot add Thing due to limit (current: " + things.size() + " maximum: " + MAX_THINGS + ")");
			return false;
		}
		
		things.add(thing);
		PropertyChangeDispatcher.getInstance().notify(this, "things", null, things);
		return true;
	}

	public void removeThing(Thing thing) {
		things.remove(thing);
	}

}
