package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;

public class InitialPlacementPhase extends Phase {

	public InitialPlacementPhase() {
		super("Thing Placement", false, true, 2);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("please draw 10 Things from the cup");
		currentStep = "Draw_Things";
		
	}
	
	@Override
	protected void nextStep() {
		BoardView.setInstructionText("please place your Things");
		currentStep = "Thing_Placement";
		
	}

}
