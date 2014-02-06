package com.kingsandthings.game.phase;

import java.util.logging.Logger;

import com.kingsandthings.game.events.NotificationDispatcher;
import com.kingsandthings.game.player.PlayerManager;

public abstract class Phase {
	
	private static Logger LOGGER = Logger.getLogger(Phase.class.getName());
	
	protected PlayerManager playerManager = PlayerManager.getInstance();
	
	private boolean mandatory;
	
	private int numPlayerTurns;
	private int currentNumberTurns = 0;
	
	public Phase(boolean mandatory, int numPlayerTurns) {
		this.mandatory = mandatory;
		this.numPlayerTurns = numPlayerTurns;
	}
	
	public abstract void begin();
	public abstract void end();
	
	public boolean isMandatory() {
		return mandatory;
	}
	
	public void incrementTurns() {
		currentNumberTurns++;
		
		if (isLastTurn()) {
			LOGGER.info("All players completed phase.");
			PlayerManager.getInstance().setActivePlayer(null);
			end();
		}
		
	}
	
	public boolean isLastTurn() {
		return currentNumberTurns == (numPlayerTurns * PlayerManager.getInstance().getNumPlayers());
	}
	
	protected void notifyBegin() {
		NotificationDispatcher.getDispatcher().notify(getClass(), PhaseNotification.BEGIN);
	}
	
	protected void notifyEnd() {
		NotificationDispatcher.getDispatcher().notify(getClass(), PhaseNotification.END);		
	}

}
