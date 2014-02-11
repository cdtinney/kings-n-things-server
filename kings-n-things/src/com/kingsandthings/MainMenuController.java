package com.kingsandthings;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.game.GameController;
import com.kingsandthings.model.PlayerManager;
import com.kingsandthings.util.Dialog;

public class MainMenuController extends Controller {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(PlayerManager.class.getName());
	
	// Primary stage
	private Stage stage;
	
	// View
	private MainMenuView view;
	
	// Sub-controllers
	private GameController gameController = new GameController();
	
	public void initialize(Stage stage) {
		
		this.stage = stage;
		
		view = new MainMenuView();
		view.initialize();
		
		stage.setScene(view);
		stage.centerOnScreen();
		
		Dialog.setStage(stage);
		
		setupHandlers();
		
	}
	
	private void setupHandlers() {
		
		Parent root = view.getRoot();
		
		addEventHandler(root, "newGameButton", "setOnAction", "handleNewGameButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
	protected void handleStartButtonAction(Event event) {
		
		List<String> playerNames = view.getPlayerNames();
		for (String name : playerNames) {
			
			if (name.trim().length() == 0) {
				view.setStatusText("one or more player names are empty");
				return;
			}
			
		}
		
		gameController.initialize(stage, playerNames, this);
	}
	
	protected void handleNewGameButtonAction(Event event) {
		
		view.displayGameSettings();
		view.setDefaultPlayerNames();
		
		addEventHandler(view.getRoot(), "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(view.getRoot(), "backButton", "setOnAction", "handleBackButtonAction");
		
	}
	
	protected void handleBackButtonAction(Event event) {
		view.displayMainMenu();
	}
	
	protected void handleExitButtonAction(Event event) {
		stage.close();
	}
	
}
