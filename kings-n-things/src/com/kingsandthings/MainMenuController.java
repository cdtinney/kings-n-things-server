package com.kingsandthings;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.kingsandthings.gui.Controller;
import com.kingsandthings.gui.util.WindowUtils;

public class MainMenuController extends Controller {
	
	private final String GAME_VIEW = "/com/kingsandthings/gui/GameView.fxml";
	private final int GAME_VIEW_WIDTH = 1024;
	private final int GAME_VIEW_HEIGHT = 768;
	
	public BorderPane rootPane;
	
	private VBox mainMenuVBox; 
	private VBox gameSettingsVBox;

	@Override
	public void initialize(Stage stage) {
		
		initializeMainMenuVBox();	
		initializeGameSettingsVBox();	
		
		rootPane.setCenter(mainMenuVBox);
		
	}
	
	private void initializeMainMenuVBox() {
		
		mainMenuVBox = new VBox(20);
		mainMenuVBox.setStyle("-fx-background-color: #FFFFFF");
		mainMenuVBox.setAlignment(Pos.CENTER);
		
		Button newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(125);
		addEventHandler(newGameButton, "setOnAction", "handleNewGameButtonAction");
		
		Button exitButton = new Button("Exit");
		exitButton.setMinWidth(125);
		addEventHandler(exitButton, "setOnAction", "handleExitButtonAction");
		
		mainMenuVBox.getChildren().addAll(newGameButton, exitButton);
		
	}
	
	private void initializeGameSettingsVBox() {
		
		gameSettingsVBox = new VBox(20);
		gameSettingsVBox.setStyle("-fx-background-color: #FFFFFF");
		gameSettingsVBox.setAlignment(Pos.CENTER);
	
		// Initialize grid for settings
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		
		// Player number label and slider
		Label playerNumLabel = new Label("Number of Players:");
		Slider playerNumSlider = new Slider(2, 4, 0);
		playerNumSlider.setValue(1);
		playerNumSlider.setMinorTickCount(0);
		playerNumSlider.setMajorTickUnit(1);
		playerNumSlider.setSnapToTicks(true);
		playerNumSlider.setShowTickMarks(true);
		playerNumSlider.setShowTickLabels(true);
		
		GridPane.setConstraints(playerNumLabel, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
		GridPane.setConstraints(playerNumSlider, 1, 0);
		
		// Player name label and field
		Label playerNameLabel = new Label("Name: ");
		TextField playerNameField = new TextField();
		
		GridPane.setConstraints(playerNameLabel, 0, 1);
		GridPane.setConstraints(playerNameField, 1, 1);
			
		// Start button
		Button startButton = new Button("Start!");
		startButton.setPrefWidth(150);
		GridPane.setConstraints(startButton, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Back button
		Button backButton = new Button("Back!");
		backButton.setPrefWidth(150);
		GridPane.setConstraints(backButton, 0, 4, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Add button event handlers
		addEventHandler(startButton, "setOnAction", "handleStartButtonAction");
		addEventHandler(backButton, "setOnAction", "handleBackButtonAction");
		
		// Add labels and controls to grid
		grid.getChildren().addAll(playerNumLabel, playerNumSlider, playerNameLabel, playerNameField, startButton, backButton);	
		
		gameSettingsVBox.getChildren().addAll(grid);
		
	}
	
	public void handleStartButtonAction(ActionEvent event) {
		WindowUtils.changeScene(event, true, GAME_VIEW, GAME_VIEW_WIDTH, GAME_VIEW_HEIGHT);
	}
	
	public void handleNewGameButtonAction(ActionEvent event) {
		rootPane.setCenter(gameSettingsVBox);
	}
	
	public void handleBackButtonAction(ActionEvent event) {
		rootPane.setCenter(mainMenuVBox);
	}
	
	public void handleExitButtonAction(ActionEvent event) {
		WindowUtils.closeStage(event);
	}
	
}
