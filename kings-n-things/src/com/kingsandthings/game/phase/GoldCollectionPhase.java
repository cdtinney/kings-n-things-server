package com.kingsandthings.game.phase;

import java.util.logging.Logger;

import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;
import com.kingsandthings.model.things.SpecialIncome;

public class GoldCollectionPhase extends Phase {
	
	private static Logger LOGGER = Logger.getLogger(GoldCollectionPhase.class.getName());

	public GoldCollectionPhase(boolean mandatory, int numPlayerTurns) {
		super(true, 0);
	}

	@Override
	public void begin() {
		
		for (Player player: playerManager.getPlayers()) {
			
			int total = computeIncome(player);
			LOGGER.log(LogLevel.STATUS, total + " gold collected.");
			
		}
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}
	
	private int computeIncome(Player player) {
		
		// TODO - one gold piece for each land hex controlled
		int controlledHexValue = 0;
		
		// Add gold pieces for the combat value of controlled forts
		// TODO - on board only
		int fortValue = 0;
		for (Fort fort : player.getForts()) {
			fortValue += fort.getCombatValue();
		}
		
		// Add gold pieces for value of special income counters 
		// TODO - on board only
		int specialIncomeValue = 0;
		for (SpecialIncome counter : player.getSpecialIncomeCounters()) {
			specialIncomeValue += counter.getGoldValue();
		}
		
		// One gold piece for each special character controlled
		int specialCharacterValue = player.getSpecialCharacters().size();
		
		// Add it all up
		return controlledHexValue + fortValue + specialIncomeValue + specialCharacterValue;
		
	}

}
