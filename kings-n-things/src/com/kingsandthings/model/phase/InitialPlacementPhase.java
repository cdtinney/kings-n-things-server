package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;

public class InitialPlacementPhase extends Phase {

	public InitialPlacementPhase() {
		super("Thing Placement", true, true, 2);
	}
	
	@Override
	public void begin() {
		BoardView.setInstructionText("please draw 10 Things from the cup");
		
		this.currentStep = "Draw_Things";
		
		notifyBegin();
	}
	
	@Override
	protected void nextStep() {
		BoardView.setInstructionText("please place your Things");
		
		this.currentStep = "Thing_Placement";
	}

}
