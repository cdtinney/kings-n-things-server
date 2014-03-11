package com.kingsandthings.model.phase;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import com.kingsandthings.game.board.BoardView;
import com.kingsandthings.model.Game;
import com.kingsandthings.model.Player;

public class StartingKingdomsPhase extends Phase {
	
	private final static BooleanProperty active = new SimpleBooleanProperty(false);
	
	private int initialNumGold = 10;
	
	public StartingKingdomsPhase(Game game) {
		super(game, "Starting Kingdoms", true, true, 2, true);
	}
	
	public static BooleanProperty getActive() {
		return active;
	}
	
	@Override
	public void begin() {
		super.begin();
		active.set(true);
		
		BoardView.setInstructionText("please place a control marker");
		
		for (Player player: game.getPlayerManager().getPlayers()) {
			
			// Give each player 10 gold 
			player.addGold(initialNumGold);
			
		}
		
	}
	
	@Override 
	public void end() {
		super.end();
		active.set(false);
	}

}
