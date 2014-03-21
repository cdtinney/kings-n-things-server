package com.kingsandthings;

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

public class ServerMenuView extends Scene {
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 400;
	
	private BorderPane root;
	private VBox gameSettings;
	
	public ServerMenuView() {
		super(new BorderPane(), WIDTH, HEIGHT);
		
		root = (BorderPane) getRoot();
		
		getStylesheets().add(getClass().getResource("/css/MainMenu.css").toExternalForm());
	}
	
	public void initialize() {
		
		initializeGameSettings();	
		initializeStatusText();
		
		displayGameSettings();
		
	}
	
	public Integer getPort() {
		TextField textField = (TextField) gameSettings.lookup("#port");
		return Integer.parseInt(textField.getText());
	}
	
	public Integer getNumPlayers() {
		return (int) ((Slider) root.lookup("#numPlayers")).getValue();
	}
	
	public void setStatusText(String text) {
		((Text) root.lookup("#statusText")).setText(text);
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
	
	private void initializeGameSettings() {
		
		ImageView logoImg = new ImageView(new Image("/images/logo.png"));
		
		gameSettings = new VBox(20);
		gameSettings.setStyle("-fx-background-color: #FFFFFF");
		gameSettings.setAlignment(Pos.CENTER);
	
		// Initialize grid for settings
		GridPane grid = new GridPane();
		grid.setId("settingsGrid");
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		
		GridPane.setConstraints(logoImg, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
		
		// Player number label and slider
		Label playerNumLabel = new Label("Number of Players:");
		Slider playerNumSlider = new Slider(2, 4, 0);
		playerNumSlider.setId("numPlayers");
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
		Button startButton = new Button("Start Server");
		startButton.setId("startButton");
		startButton.setPrefWidth(150);
		GridPane.setConstraints(startButton, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Exit button
		Button exitButton = new Button("Exit");
		exitButton.setId("exitButton");
		exitButton.setPrefWidth(150);
		GridPane.setConstraints(exitButton, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER);
		
		// Add labels and controls to grid
		grid.getChildren().addAll(playerNumLabel, playerNumSlider, startButton, exitButton);	
		
		// Add the settings grid to the VBox
		gameSettings.getChildren().addAll(logoImg, grid);
		
		// Add port field
		addPortField();
	}
	
	private void addPortField() {
		
		GridPane grid = (GridPane) gameSettings.lookup("#settingsGrid");
		
		Label portLabel = new Label("Port: ");
		TextField portField = new TextField("9000");
		portField.setId("port");
		
		GridPane.setConstraints(portLabel, 0, 1);
		GridPane.setConstraints(portField, 1, 1);
		
		grid.getChildren().addAll(portLabel, portField);

		GridPane.setConstraints(gameSettings.lookup("#startButton"), 0, 2+1, 2, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(gameSettings.lookup("#exitButton"), 0, 3+1, 2, 1, HPos.CENTER, VPos.CENTER);
		
	}
	
}
