package com.kingsandthings.game.phase;

import java.util.logging.Logger;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.game.player.PlayerManager;

public abstract class Phase {
	
	private static Logger LOGGER = Logger.getLogger(Phase.class.getName());
	
	protected PlayerManager playerManager = PlayerManager.getInstance();
	
	private String name;
	
	private boolean mandatory;
	private boolean playerInteraction;
	
	private int numPlayerTurns;
	private int currentNumberTurns = 0;
	
	public Phase(String name, boolean mandatory, boolean playerInteraction, int numPlayerTurns) {
		this.name = name;
		this.mandatory = mandatory;
		this.numPlayerTurns = numPlayerTurns;
		this.playerInteraction = playerInteraction;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean playerInteractionRequired() {
		return playerInteraction;
	}
	
	public boolean isMandatory() {
		return mandatory;
	}
	
	public void nextTurn() {
		
		currentNumberTurns++;
		
		if (isLastTurn()) {
			
			// End the phase
			end();
			
			// Move to next phase
			PhaseManager.getInstance().nextPhase();
			
		} else {
			next();
			
		}
		
	}
	
	public boolean isLastTurn() {
		return currentNumberTurns == (numPlayerTurns * PlayerManager.getInstance().getNumPlayers());
	}
	
	public void begin() {
		notifyBegin();
	}
	
	public void next() {
		// TODO - notify next player turn
	}
	
	public void end() {
		notifyEnd();
	}
	
	protected void notifyBegin() {
		NotificationDispatcher.getDispatcher().notify(getClass(), PhaseNotification.BEGIN);
	}
	
	protected void notifyEnd() {
		LOGGER.info("All players completed phase '" + name + "'");
		NotificationDispatcher.getDispatcher().notify(getClass(), PhaseNotification.END);		
	}

}
