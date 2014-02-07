package com.kingsandthings.game;

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import com.kingsandthings.Controller;
import com.kingsandthings.game.phase.PhaseManager;
import com.kingsandthings.logging.LogLevel;

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
		
		addEventHandler(view, "rollDice", "setOnAction", "handleRollDiceButton");
		addEventHandler(view, "skipTurn", "setOnAction", "handleSkipTurnButton");
		addEventHandler(view, "endTurn", "setOnAction", "handleEndTurnButton");
		
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
