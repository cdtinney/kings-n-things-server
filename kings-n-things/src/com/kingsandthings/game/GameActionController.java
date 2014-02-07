package com.kingsandthings.game;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import com.kingsandthings.Controller;
import com.kingsandthings.game.phase.PhaseManager;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Game;

public class GameActionController extends Controller {

	private static Logger LOGGER = Logger.getLogger(GameActionController.class.getName());

	// View
	private GameActionView view;
	
	public void initialize() {
		
		view = new GameActionView();
		view.initialize();
		
		setupHandlers();
		
	}
	
	public Node getView() {
		return view;
	}
	
	private void setupHandlers() {
		
		// Skip and end turns within a phase
		addEventHandler(view, "skipTurn", "setOnAction", "handleSkipTurnButton");
		addEventHandler(view, "endTurn", "setOnAction", "handleEndTurnButton");
		
		// Draw and select Things
		addEventHandler(view, "drawThing", "setOnAction", "handleDrawThingButton");
		addEventHandler(view, "selectThings", "setOnAction", "handleSelectThingsButton");
		
		// Roll dice
		addEventHandler(view, "rollDice", "setOnAction", "handleRollDiceButton");
		
	}
	
	@SuppressWarnings({ "unused" })
	private void handleSelectThingsButton(Event event) {
		
		List<Integer> selected = view.getSelectedThings();
		
		// Add the selected things to the player's list of Things
		Game.getInstance().addThingsToPlayer(selected, PlayerManager.getInstance().getActivePlayer());
		
		// Hide the list of Things
		view.toggleThingList();
		
	}
	
	@SuppressWarnings("unused")
	private void handleDrawThingButton(Event event) {
		view.toggleThingList();
	}
	
	@SuppressWarnings("unused")
	private void handleSkipTurnButton(Event event) {
		PhaseManager.getInstance().skipPlayerTurn();
	}
	
	@SuppressWarnings("unused")
	private void handleEndTurnButton(Event event) {
		PhaseManager.getInstance().endPlayerTurn();		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void handleRollDiceButton(Event event) {
		
		String rollType = ((ComboBox<String>) view.lookup("#diceRollType")).getValue().toString();
		
		LOGGER.log(LogLevel.STATUS, "Dice rolled - " + rollType); 
		
	}
	
}
