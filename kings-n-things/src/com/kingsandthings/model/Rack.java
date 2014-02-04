package com.kingsandthings.model;

import java.util.ArrayList;

import com.kingsandthings.model.things.Thing;

public class Rack {
	
	private final int MAX_THINGS = 10;
	
	private ArrayList<Thing> things;
	
	private Player owner;
	
	public Rack() {
		things = new ArrayList<Thing>();
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player player) {
		this.owner = player;
	}
	
	public ArrayList<Thing> getThings() {
		return things;
	}
	
	public boolean addThing(Thing thing) {
		
		if (things.size() == MAX_THINGS) {
			return false;
		}
		
		return things.add(thing);
	}

	public void removeThing(Thing thing) {
		things.remove(thing);
	}

}
