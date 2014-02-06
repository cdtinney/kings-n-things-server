package com.kingsandthings.game;

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import com.kingsandthings.Controller;
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
		
		// Roll dice button handler
		addEventHandler(view, "rollDice", "setOnAction", "handleRollDiceButton");
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void handleRollDiceButton(Event event) {
		
		String rollType = ((ComboBox<String>) view.lookup("#diceRollType")).getValue().toString();
		
		LOGGER.log(LogLevel.STATUS, "Dice rolled - " + rollType); 
		
	}
	
}
