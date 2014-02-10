package com.kingsandthings.model.phase;

import java.util.logging.Logger;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.model.PlayerManager;

public abstract class Phase {
	
	public enum Notification {
		BEGIN,
		END
	}
	
	private static Logger LOGGER = Logger.getLogger(Phase.class.getName());
	
	protected PlayerManager playerManager = PlayerManager.getInstance();
	protected String currentStep = "none";
	
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
	
	public void begin() {
		notifyBegin();
	}
	
	public void next() {
		// TODO
	}
	
	public void end() {
		notifyEnd();
	}
	
	public void nextTurn() {
		
		playerManager.nextPlayer();
		currentNumberTurns++;
		
		if (isLastTurn()) {
			
			// End the phase
			end();
			
			// Move to next phase
			PhaseManager.getInstance().nextPhase();
			
		} else {
			
			if (allPlayersCompletedTurn()) {
				nextStep();
			} else {
				next();
			}
			
		}
		
	}
	
	public String getStep() {
		return currentStep;
	}
	
	protected void notifyBegin() {
		NotificationDispatcher.getInstance().notify(getClass(), Notification.BEGIN);
	}
	
	protected void notifyEnd() {
		LOGGER.info("All players completed phase '" + name + "'");
		NotificationDispatcher.getInstance().notify(getClass(), Notification.END);		
	}
	
	protected void nextStep() {
		next();
	}
	
	private boolean allPlayersCompletedTurn() {
		return currentNumberTurns % PlayerManager.getInstance().getNumPlayers() == 0;
	}
	
	private boolean isLastTurn() {
		return currentNumberTurns == (numPlayerTurns * PlayerManager.getInstance().getNumPlayers());
	}

}
