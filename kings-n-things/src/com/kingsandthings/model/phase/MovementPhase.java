package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;

public class MovementPhase extends Phase {

	public MovementPhase() {
		super("Movement", false, true, 1);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("do some movement");
		
	}

}
