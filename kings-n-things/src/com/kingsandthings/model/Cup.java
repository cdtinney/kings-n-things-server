package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.kingsandthings.model.things.Thing;
import com.kingsandthings.model.things.ThingImport;

public class Cup {
	
	List<Thing> things;
	
	public Cup() {
		things = new ArrayList<Thing>();
		
		// TODO - import all Things
		addThings(ThingImport.importCreatures());
	}
	
	public List<Thing> drawThings(int num) {
		
		List<Thing> copy = new ArrayList<Thing>(things);
		Collections.shuffle(copy);
		return copy.subList(0, num);
		
	}
	
	public Thing drawThing() {
		Random r = new Random();
		return things.remove(r.nextInt(things.size()));
	}
	
	public List<Thing> getThings() {
		return things;
	}
	
	/*
	 * TASK - Demo only. Remove.
	 */
	public List<String> getThingNames() {
		
		List<String> names = new ArrayList<String>();
		
		for (Thing thing : things) {
			names.add(thing.toString());
		}
		
		return names;
		
	}
	
	public void removeThings(List<Thing> list) {
		things.removeAll(list);
	}	
	
	private void addThings(List<? extends Thing> list) {
		things.addAll(list);
	}

}
