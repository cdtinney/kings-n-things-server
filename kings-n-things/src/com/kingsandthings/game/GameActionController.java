package com.kingsandthings.game;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import com.kingsandthings.Controller;
import com.kingsandthings.logging.LogLevel;
import com.kingsandthings.model.Game;
import com.kingsandthings.model.PlayerManager;
import com.kingsandthings.model.phase.PhaseManager;
import com.kingsandthings.model.things.Thing;

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
		
		addEventHandler(view, "endTurn", "setOnAction", "handleEndTurnButton");
		
		addEventHandler(view, "drawThing", "setOnAction", "handleDrawThingButton");
		addEventHandler(view, "selectThings", "setOnAction", "handleSelectThingsButton");
		
		addEventHandler(view, "rollDice", "setOnAction", "handleRollDiceButton");
		
	}
	
	@SuppressWarnings({ "unused" })
	private void handleSelectThingsButton(Event event) {
		
		List<Integer> selected = view.getSelectedThings();
		
		// Add the selected things to the player's list of Things
		Game.getInstance().addThingIndicesToPlayer(selected, PlayerManager.getInstance().getActivePlayer());
		
		// Hide the list of Things
		view.toggleThingList();
		
	}
	
	@SuppressWarnings("unused")
	private void handleDrawThingButton(Event event) {
		
		if (!PhaseManager.getInstance().getCurrentPhase().getStep().equals("Draw_Things")) {
			return;
		}
		
		// TASK - Demo only (drawing 10 things). Modify this.
		List<Thing> things = Game.getInstance().getCup().drawThings(10);
		Game.getInstance().addInitialThingsToPlayer(things, PlayerManager.getInstance().getActivePlayer());
		
		//view.toggleThingList();
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
