package com.kingsandthings.model.phase;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import com.kingsandthings.game.board.BoardView;

public class InitialPlacementPhase extends Phase {
	
	private final static BooleanProperty active = new SimpleBooleanProperty(false);

	public InitialPlacementPhase() {
		super("Thing Placement", false, true, 2, true);
	}
	
	public static BooleanProperty getActive() {
		return active;
	}
	
	@Override
	public void begin() {
		currentStep = "Draw_Things";
		BoardView.setInstructionText("please draw 10 Things from the cup");
		
		super.begin();
		active.set(true);
		
	}
	
	@Override
	protected void nextStep() {
		notify(Notification.STEP);
		
		currentStep = "Thing_Placement";
		BoardView.setInstructionText("please place your Things");
		
	}
	
	@Override
	public void end() {
		super.end();
		active.set(false);
	}

}
