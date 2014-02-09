package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kingsandthings.model.things.Thing;

public class Cup {
	
	List<Thing> things;
	
	public Cup() {
		things = new ArrayList<Thing>();
	}
	
	public Thing drawThing() {
		Random r = new Random();
		return things.remove(r.nextInt(things.size()));
	}
	
	public List<Thing> getThings() {
		return things;
	}
	
	/*
	 * TODO - remove. For demo purposes only.
	 */
	public List<String> getThingNames() {
		
		List<String> names = new ArrayList<String>();
		
		for (Thing thing : things) {
			names.add(thing.toString());
		}
		
		return names;
		
	}
	
	public void addThings(List<? extends Thing> things) {
		this.things.addAll(things);
	}
	
	public void removeThings(List<Thing> things) {
		this.things.removeAll(things);
	}	

}
