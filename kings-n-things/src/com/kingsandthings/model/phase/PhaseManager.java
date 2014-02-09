package com.kingsandthings.model.phase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Fort;

public class PhaseManager {
	
	private static Logger LOGGER = Logger.getLogger(PhaseManager.class.getName());
	
	private static PhaseManager INSTANCE = null;
	
	private PlayerManager playerManager = PlayerManager.getInstance();
	
	private List<Phase> phases;
	
	private int currentPhaseNumber = 0;
	
	private PhaseManager() {
		phases = new ArrayList<Phase>();
		
		// Add the phases in order
		phases.add(new StartingKingdomsPhase());
		phases.add(new TowerPlacementPhase());
		phases.add(new GoldCollectionPhase());
		
		// Set the first player to active
		for (Player player: playerManager.getPlayers()) {
			
			if (playerManager.getPosition(player) == 1) {
				playerManager.setActivePlayer(player);
			}
			
		}
	}
	
	public static PhaseManager getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new PhaseManager();
		}
		
		return INSTANCE;
		
	}
	
	public void endPlayerTurn() {
		PlayerManager.getInstance().nextPlayer();
		getCurrentPhase().nextTurn();
	}
	
	public void skipPlayerTurn() {
		
		if (getCurrentPhase().isMandatory()) {
			LOGGER.log(LogLevel.STATUS, "Cannot skip turn - phase play is mandatory.");
			return;
		}
		
		getCurrentPhase().nextTurn();
		
	}
	
	public Phase getCurrentPhase() {
		return phases.get(currentPhaseNumber);
	}
	
	public void nextPhase() {
		
		Phase oldPhase = getCurrentPhase();
		
		// No more phases (currently)
		if (currentPhaseNumber + 1 == phases.size()) {
			
			// Set the active player to none
			PlayerManager.getInstance().setActivePlayer(null);
			
			// Notify listeners
			PropertyChangeDispatcher.getInstance().notify(this, "currentPhase", oldPhase, null);
			
			return;
			
		}
		
		Phase newPhase = phases.get(++currentPhaseNumber);
		
		// Notify listeners of phase change
		PropertyChangeDispatcher.getInstance().notify(this, "currentPhase", oldPhase, newPhase);
		
		// Begin new phase
		newPhase.begin();
		
	}

}
