package com.kingsandthings;

import java.util.Arrays;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.kingsandthings.game.GameController;
import com.kingsandthings.game.GameSettings;

public class MainMenuController extends Controller {
	
	// Primary stage of the application
	private Stage stage;
	
	// View
	private MainMenuView view;
	
	// Sub-controller
	private GameController gameController = new GameController();
	
	public void initialize(Stage stage) {
		
		this.stage = stage;
		
		view = new MainMenuView();
		
		stage.setScene(view.initialize());
		stage.centerOnScreen();
		
		setupHandlers();
		
	}
	
	private void setupHandlers() {
		
		Parent root = view.getRoot();
		
		addEventHandler(root, "newGameButton", "setOnAction", "handleNewGameButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
	public void handleStartButtonAction(Event event) {
		
		int numPlayers = (int) ((Slider) view.lookup("#playerNum")).getValue();
		String playerName = ((TextField) view.lookup("#playerName")).getText();
		
		GameSettings settings = new GameSettings(numPlayers, Arrays.asList(playerName));
		
		gameController.initialize(stage, settings, this);
	}
	
	public void handleNewGameButtonAction(Event event) {
		
		view.displayGameSettings();
		
		addEventHandler(view.getRoot(), "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(view.getRoot(), "backButton", "setOnAction", "handleBackButtonAction");
		
	}
	
	public void handleBackButtonAction(Event event) {
		view.displayMainMenu();
	}
	
	public void handleExitButtonAction(Event event) {
		stage.close();
	}
	
}
