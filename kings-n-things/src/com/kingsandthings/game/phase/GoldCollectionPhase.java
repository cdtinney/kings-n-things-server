package com.kingsandthings.game.phase;

import java.util.logging.Logger;

import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;
import com.kingsandthings.model.things.SpecialIncome;

public class GoldCollectionPhase extends Phase {
	
	private static Logger LOGGER = Logger.getLogger(GoldCollectionPhase.class.getName());

	public GoldCollectionPhase() {
		super("Gold Collection", true, false, 1);
	}

	@Override
	public void begin() {
		next();		
	}
	
	@Override
	public void next() {
		
		Player player = playerManager.getActivePlayer();
		
		int total = computeIncome(player);
		player.addGold(total);

		LOGGER.log(LogLevel.STATUS, "Player '" + player.getName() + "' collected " + total + " gold.");
		
	}
	
	private int computeIncome(Player player) {
		
		// One gold piece for each land hex controlled
		int controlledHexValue = player.getControlledTiles().size();
		
		// Add gold pieces for the combat value of controlled forts
		int fortValue = 0;
		for (Fort fort : player.getForts()) {
			
			// Forts placed on the board only
			if (fort.placedOnBoard()) {
				fortValue += fort.getCombatValue();
			}
			
		}
		
		// Add gold pieces for value of special income counters 
		int specialIncomeValue = 0;
		for (SpecialIncome counter : player.getSpecialIncomeCounters()) {
			
			// Special income counters placed on the board only
			if (counter.placedOnBoard()) {
				specialIncomeValue += counter.getGoldValue();
			}
			
		}
		
		// One gold piece for each special character controlled
		int specialCharacterValue = player.getSpecialCharacters().size();
		
		// Add it all up
		return controlledHexValue + fortValue + specialIncomeValue + specialCharacterValue;
		
	}

}
