package com.kingsandthings.model.phase;

import javafx.scene.image.Image;

import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;

public class StartingKingdomsPhase extends Phase {
	
	private int initialNumGold = 10;
	
	public StartingKingdomsPhase() {
		super("Starting Kingdoms", true, true, 2);
	}
	
	@Override
	public void begin() {
		
		for (Player player: playerManager.getPlayers()) {
			
			// Give each player 10 gold
			player.addGold(initialNumGold);
			
			// Give each player a tower
			// TODO - don't hard code this 
			player.addFort(new Fort("Tower", 1, new Image("/images/things/fort/-n Tower -a 1.jpg")));
			
		}
		
		// Notify listeners that the phase has begun
		notifyBegin();
		
	}

	@Override
	public void end() {
		
		// Notify listeners that the phase has ended
		notifyEnd();
	}

}
