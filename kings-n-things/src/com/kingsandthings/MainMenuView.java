package com.kingsandthings;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.kingsandthings.game.InitializableView;

public class MainMenuView extends Scene implements InitializableView {
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 400;
	
	private BorderPane root;
	
	private VBox mainMenu; 
	private VBox gameSettings;
	
	private List<TextField> playerNameFields;
	
	public MainMenuView() {
		super(new BorderPane(), WIDTH, HEIGHT);
		
		root = (BorderPane) getRoot();
		
		getStylesheets().add(getClass().getResource("/css/MainMenu.css").toExternalForm());
	}
	
	@Override
	public void initialize() {
		
		playerNameFields = new ArrayList<TextField>();
		
		initializeMainMenu();	
		initializeGameSettings();	
		
		initializeStatusText();
		
		displayMainMenu();
		
	}
	
	public void setDefaultPlayerNames() {
		
		int i = 1;
		for (TextField textField : playerNameFields) {
			textField.setText("Player " + i++);
		}
		
	}

	public List<String> getPlayerNames() {
		
		if (playerNameFields == null) {
			return null;
		}
		
		List<String> names = new ArrayList<String>();
		for (TextField textField : playerNameFields) {
			names.add(textField.getText());
		}
		
		return names;
		
	}
	
	public void setStatusText(String text) {
		((Text) root.lookup("#statusText")).setText(text);
	}
	
	public void displayMainMenu() { 
		root.setCenter(mainMenu); 
	}
	
	public void displayGameSettings() { 
		root.setCenter(gameSettings); 
	}

	private void initializeStatusText() {
		
		Text statusText = new Text();
		statusText.setFont(Font.font("Lucida Sans", 12));
		statusText.setFill(Color.RED);
		statusText.setId("statusText");
		
		root.setTop(statusText);
		
		BorderPane.setAlignment(statusText, Pos.CENTER);
		BorderPane.setMargin(statusText, new Insets(10));
		
	}
	
	private void initializeMainMenu() {
		
		mainMenu = new VBox(20);
		mainMenu.setStyle("-fx-background-color: #FFFFFF");
		mainMenu.setAlignment(Pos.CENTER);
		
		ImageView logoImg = new ImageView(new Image("/images/logo.png"));
		
		Button newGameButton = new Button("New Game");
		newGameButton.setId("newGameButton");
		newGameButton.setPrefWidth(125);
		
		Button exitButton = new Button("Exit");
		exitButton.setId("exitButton");
		exitButton.setMinWidth(125);
		
		mainMenu.getChildren().addAll(logoImg, newGameButton, exitButton);
		
	}
	
	private void initializeGameSettings() {
		
		gameSettings = new VBox(20);
		gameSettings.setStyle("-fx-background-color: #FFFFFF");
		gameSettings.setAlignment(Pos.CENTER);
	
		// Initialize grid for settings
		GridPane grid = new GridPane();
		grid.setId("settingsGrid");
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		
		// Player number label and slider
		Label playerNumLabel = new Label("Number of Players:");
		Slider playerNumSlider = new Slider(2, 4, 0);
		playerNumSlider.setId("playerNum");
		playerNumSlider.setMinorTickCount(0);
		playerNumSlider.setMajorTickUnit(1);
		playerNumSlider.setSnapToTicks(true);
		playerNumSlider.setShowTickMarks(true);
		playerNumSlider.setShowTickLabels(true);
		
		// Set to 4 and disable for now
		playerNumSlider.setValue(4);
		playerNumSlider.setDisable(true);
		
		GridPane.setConstraints(playerNumLabel, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
		GridPane.setConstraints(playerNumSlider, 1, 0);
			
		// Start button
		Button startButton = new Button("Start");
		startButton.setId("startButton");
		startButton.setPrefWidth(150);
		GridPane.setConstraints(startButton, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Back button
		Button backButton = new Button("Back");
		backButton.setId("backButton");
		backButton.setPrefWidth(150);
		GridPane.setConstraints(backButton, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Add labels and controls to grid
		grid.getChildren().addAll(playerNumLabel, playerNumSlider, startButton, backButton);	
		
		// Add the settings grid to the VBox
		gameSettings.getChildren().addAll(grid);
		
		// Add player fields
		setPlayerFields(4);
	}
	
	private void setPlayerFields(int num) {
		
		GridPane grid = (GridPane) gameSettings.lookup("#settingsGrid");
		
		for (int i=0; i<num; ++i) {
			
			Label playerNameLabel = new Label("Name: ");
			TextField playerNameField = new TextField();
			
			GridPane.setConstraints(playerNameLabel, 0, 1+i);
			GridPane.setConstraints(playerNameField, 1, 1+i);
			
			playerNameFields.add(playerNameField);
			
			grid.getChildren().addAll(playerNameLabel, playerNameField);
			
		}

		GridPane.setConstraints(gameSettings.lookup("#startButton"), 0, 2+num, 2, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(gameSettings.lookup("#backButton"), 0, 3+num, 2, 1, HPos.CENTER, VPos.CENTER);
		
	}
	
}
