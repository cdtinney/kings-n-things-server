package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;

public class TowerPlacementPhase extends Phase {

	public TowerPlacementPhase() {
		super("Tower Placement", true, true, 1, true);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("please place a tower");
		
		for (Player player: playerManager.getPlayers()) {
			
			// Give each player a tower
			player.addFort(Fort.getTower());
		}
		
	}

}
