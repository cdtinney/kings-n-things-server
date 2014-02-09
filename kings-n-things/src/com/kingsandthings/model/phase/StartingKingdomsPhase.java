package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;

public class StartingKingdomsPhase extends Phase {
	
	private int initialNumGold = 10;
	
	public StartingKingdomsPhase() {
		super("Starting Kingdoms", true, true, 2);
	}
	
	@Override
	public void begin() {
		
		BoardView.setInstructionText("please place a control marker");
		
		for (Player player: playerManager.getPlayers()) {
			
			// Give each player 10 gold 
			player.addGold(initialNumGold);
			
			// Give each player a tower
			player.addFort(Fort.getTower());
			
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
