package com.kingsandthings;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.kingsandthings.game.View;

public class MainMenuView extends Scene implements View<Scene> {
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 400;
	
	private BorderPane root;
	
	private VBox mainMenu; 
	private VBox gameSettings;
	
	private Slider playerNumSlider;
	
	public MainMenuView() {
		super(new BorderPane(), WIDTH, HEIGHT);
		
		root = (BorderPane) getRoot();
		
		getStylesheets().add(getClass().getResource("/css/MainMenu.css").toExternalForm());
	}
	
	@Override
	public Scene initialize() {
		
		initializeMainMenu();	
		initializeGameSettings();	
		
//		mainMenu.getStyleClass().add("root");
//		gameSettings.getStyleClass().add("root");
		
		displayMainMenu();
		
		return this;	
		
	}
	
	public int getPlayerNum() { 
		return playerNumSlider != null? (int) playerNumSlider.getValue() : 0; 
	}
	
	public void displayMainMenu() { 
		root.setCenter(mainMenu); 
	}
	
	public void displayGameSettings() { 
		root.setCenter(gameSettings); 
	}
	
	private void initializeMainMenu() {
		
		mainMenu = new VBox(20);
		mainMenu.setStyle("-fx-background-color: #FFFFFF");
		mainMenu.setAlignment(Pos.CENTER);
		
		Button newGameButton = new Button("New Game");
		newGameButton.setId("newGameButton");
		newGameButton.setPrefWidth(125);
		
		Button exitButton = new Button("Exit");
		exitButton.setId("exitButton");
		exitButton.setMinWidth(125);
		
		mainMenu.getChildren().addAll(newGameButton, exitButton);
		
	}
	
	private void initializeGameSettings() {
		
		gameSettings = new VBox(20);
		gameSettings.setStyle("-fx-background-color: #FFFFFF");
		gameSettings.setAlignment(Pos.CENTER);
	
		// Initialize grid for settings
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		
		// Player number label and slider
		Label playerNumLabel = new Label("Number of Players:");
		playerNumSlider = new Slider(2, 4, 0);
		playerNumSlider.setId("playerNum");
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
		playerNameField.setId("playerName");
		
		GridPane.setConstraints(playerNameLabel, 0, 1);
		GridPane.setConstraints(playerNameField, 1, 1);
			
		// Start button
		Button startButton = new Button("Start");
		startButton.setId("startButton");
		startButton.setPrefWidth(150);
		GridPane.setConstraints(startButton, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Back button
		Button backButton = new Button("Back");
		backButton.setId("backButton");
		backButton.setPrefWidth(150);
		GridPane.setConstraints(backButton, 0, 4, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Add labels and controls to grid
		grid.getChildren().addAll(playerNumLabel, playerNumSlider, playerNameLabel, playerNameField, startButton, backButton);	
		
		gameSettings.getChildren().addAll(grid);
	}
	
}
