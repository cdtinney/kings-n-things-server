package com.kingsandthings.model.phase;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import com.kingsandthings.game.board.BoardView;

public class MovementPhase extends Phase {
	
	public static BooleanProperty active = new SimpleBooleanProperty(false);

	public MovementPhase() {
		super("Movement", false, true, 1, false);
	}
	
	@Override
	public void begin() {
		super.begin();
		
		BoardView.setInstructionText("do some movement");
		active.set(true);
		
	}
	
	@Override
	public void end() {
		super.end();
		active.set(false);
	}

}
