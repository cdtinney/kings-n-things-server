package com.kingsandthings.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.model.things.ThingImport;

public class Cup {

	private static Logger LOGGER = Logger.getLogger(Cup.class.getName());
	
	private List<Thing> things;
	
	public Cup() {
		things = new ArrayList<Thing>();
		
		// TODO - Import all Things
		addThings(ThingImport.importCreatures());
	}
	
	public boolean recruitThings(Player player, int numPaid) {
		
		int goldRequired = numPaid * 5;
		
		if (player.getNumGold() < goldRequired) {
			LOGGER.log(LogLevel.STATUS, goldRequired + " gold is required to buy " + numPaid + " recruits.");
			return false;
		}
		
		int numFree = numFreeRecruits(player);
		
		List<Thing> drawn = drawThings(numFree + numPaid);
		
		boolean success = player.getRack().addThings(drawn);
		
		if (success) {
			LOGGER.log(LogLevel.STATUS, player.getName() + " received: " + numFree  + " free, " + numPaid + " paid.");
			removeThings(drawn);
		}
		
		return success;
		
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
	
	private int numFreeRecruits(Player player) {
		return player.getControlledTiles().size() * 2;
	}
	
	private void addThings(List<? extends Thing> list) {
		things.addAll(list);
	}

}
