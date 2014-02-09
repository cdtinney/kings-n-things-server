package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;

public class TowerPlacementPhase extends Phase {

	public TowerPlacementPhase() {
		super("Tower Placement", true, true, 1);
	}
	
	@Override
	public void begin() {
		
		BoardView.setInstructionText("please place a tower");
		
		notifyBegin();
		
	}

}
