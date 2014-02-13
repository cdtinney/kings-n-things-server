package com.kingsandthings.model.phase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.Game;

public class PhaseManager {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(PhaseManager.class.getName());
	
	private static PhaseManager INSTANCE = null;
	
	protected Game game;
	
	private List<Phase> phases;
	private int currentPhaseNumber = 0;
	
	public PhaseManager(Game game) {
		
		this.game = game;

		phases = new ArrayList<Phase>();
		
		// Add the phases (in order)
		phases.add(new StartingKingdomsPhase());
		phases.add(new TowerPlacementPhase());
		phases.add(new InitialPlacementPhase());
		
		phases.add(new GoldCollectionPhase());
		phases.add(new ThingRecruitmentPhase());
		phases.add(new MovementPhase(game));
		
		// TODO - fix
		INSTANCE = this;
		
	}
	
	public static PhaseManager getInstance() {
		return INSTANCE;
	}
	
	public void beginPhases() {
		phases.get(0).begin();
		PropertyChangeDispatcher.getInstance().notify(this, "currentPhase", null, getCurrentPhase());
	}
	
	public void endPlayerTurn() {
		getCurrentPhase().nextTurn();
	}
	
	public Phase getCurrentPhase() {
		return phases.get(currentPhaseNumber);
	}
	
	public void nextPhase() {
		
		Phase oldPhase = getCurrentPhase();
		
		currentPhaseNumber = (currentPhaseNumber + 1) % phases.size();
		
		Phase newPhase = phases.get(currentPhaseNumber);
		
		if (oldPhase.isInitial()) {
			phases.remove(oldPhase);
			currentPhaseNumber--;
		}
		
		newPhase.begin();
		
		notifyPhaseChange(oldPhase, newPhase);
		
	}
	
	private void notifyPhaseChange(Phase oldPhase, Phase newPhase) {
		PropertyChangeDispatcher.getInstance().notify(this, "currentPhase", oldPhase, newPhase);
	}

}
