package com.kingsandthings;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.gui.Controller;
import com.kingsandthings.gui.GameController;

public class MainMenuController extends Controller {
	
	// Primary stage of the application
	private Stage stage;
	
	// View
	private MainMenuView view = new MainMenuView();
	
	// Sub-controller
	private GameController gameController = new GameController();
	
	public void initialize(Stage stage) {
		
		this.stage = stage;
		stage.setScene(view.initialize().getScene());
		
		setupHandlers();
		
	}
	
	private void setupHandlers() {
		
		Parent root = view.getScene().getRoot();
		
		addEventHandler(root, "newGameButton", "setOnAction", "handleNewGameButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
	public void handleStartButtonAction(ActionEvent event) {
		gameController.initialize(stage);
	}
	
	public void handleNewGameButtonAction(ActionEvent event) {
		
		view.displayGameSettings();
		
		addEventHandler(view.getScene().getRoot(), "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(view.getScene().getRoot(), "backButton", "setOnAction", "handleBackButtonAction");
		
	}
	
	public void handleBackButtonAction(ActionEvent event) {
		view.displayMainMenu();
	}
	
	public void handleExitButtonAction(ActionEvent event) {
		stage.close();
	}
	
}
