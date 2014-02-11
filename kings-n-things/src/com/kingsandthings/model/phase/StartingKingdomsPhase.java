package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;
import com.kingsandthings.model.Player;

public class StartingKingdomsPhase extends Phase {
	
	private int initialNumGold = 10;
	
	public StartingKingdomsPhase() {
		super("Starting Kingdoms", true, true, 2);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("please place a control marker");
		
		for (Player player: playerManager.getPlayers()) {
			
			// Give each player 10 gold 
			player.addGold(initialNumGold);
			
		}
		
	}

}
