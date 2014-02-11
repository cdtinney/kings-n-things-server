package com.kingsandthings.model.phase;

import com.kingsandthings.game.board.BoardView;

public class ThingRecruitmentPhase extends Phase {

	public ThingRecruitmentPhase() {
		super("Thing Recruitment", true, true, 2, false);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("please recruit things");
		currentStep = "Draw_Things";
		
	}
	
	@Override
	public void nextStep() {
		notify(Notification.STEP);
		
		BoardView.setInstructionText("please place your Things");
		currentStep = "Thing_Placement";
		
	}
	
}
