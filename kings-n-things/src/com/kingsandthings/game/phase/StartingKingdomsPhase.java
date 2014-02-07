package com.kingsandthings.game.phase;

import javafx.scene.image.Image;

import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;

public class StartingKingdomsPhase extends Phase {
	
	private int initialNumGold = 10;
	
	public StartingKingdomsPhase() {
		super(true, 2);
	}
	
	@Override
	public void begin() {
		
		for (Player player: playerManager.getPlayers()) {
			
			// Give each player 10 gold
			player.setNumGold(initialNumGold);
			
			// Give each player a tower
			player.addFort(new Fort("Tower", 1, new Image("/images/things/fort/-n Tower -a 1.jpg")));
			
			// Set the player at 1st position to active
			if (playerManager.getPosition(player) == 1) {
				playerManager.setActivePlayer(player);
			}
			
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
