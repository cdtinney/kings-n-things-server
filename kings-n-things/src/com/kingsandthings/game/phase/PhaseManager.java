package com.kingsandthings.game.phase;

import java.util.ArrayList;
import java.util.List;

public class PhaseManager {
	
	private static PhaseManager INSTANCE = null;
	
	private List<Phase> phases;
	
	private PhaseManager() {
		phases = new ArrayList<Phase>();
		phases.add(new StartingKingdomsPhase());
	}
	
	public static PhaseManager getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new PhaseManager();
		}
		
		return INSTANCE;
		
	}
	
	public Phase getCurrentPhase() {
		return phases.get(0);
	}

}
