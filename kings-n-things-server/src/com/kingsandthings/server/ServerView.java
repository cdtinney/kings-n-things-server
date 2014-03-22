package com.kingsandthings.server;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ServerView extends Scene {
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 420;
	
	private BorderPane root;
	
	private VBox settingsVBox;
	private VBox logVBox;
	
	private TextArea textLog;
	private Text connectedPlayersText;
	
	public ServerView() {
		super(new BorderPane(), WIDTH, HEIGHT);
		
		root = (BorderPane) getRoot();
		
		getStylesheets().add(getClass().getResource("/css/MainMenu.css").toExternalForm());
	}
	
	public void initialize() {
		
		initializeGameSettings();	
		initializeGameLog();
		
		initializeStatusText();
		
		showSettings();
		
	}
	
	public Integer getPort() {
		return Integer.parseInt(((TextField) settingsVBox.lookup("#port")).getText());
	}
	
	public Integer getNumPlayers() {
		return (int) ((Slider) root.lookup("#numPlayers")).getValue();
	}
	
	public void setStatusText(String text) {
		((Text) root.lookup("#statusText")).setText(text);
	}
	
	public void setConnectedPlayersText(List<String> playerNames) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Connected players (" + playerNames.size() + "): ");
		
		for (String name : playerNames) {
			sb.append(name);
			
			if (playerNames.indexOf(name) != playerNames.size()-1) {
				sb.append(", ");
			}
		}
		
		connectedPlayersText.setText(sb.toString());
		
	}

	public void appendToLog(final String text) {
		
		// Run on the JavaFX thread
		Platform.runLater(new Runnable() {
			
			@Override public void run() {
				textLog.appendText(text + "\n");
			}
			
		});
		
	}
	
	public void showSettings() {
		root.setCenter(settingsVBox); 
	}
	
	public void showLog() {
		root.setCenter(logVBox);
	}
	
	private void initializeGameLog() {
		
		logVBox = new VBox(10);
		logVBox.setStyle("-fx-background-color: #FFFFFF");
		
		textLog = TextAreaBuilder.create().cursor(Cursor.DEFAULT).editable(false).wrapText(true).build();
		VBox.setVgrow(textLog, Priority.ALWAYS);
		
		connectedPlayersText = new Text("connected players: ");
		connectedPlayersText.setFont(Font.font("Lucida Sans", 12));
		connectedPlayersText.setFill(Color.GREEN);
		VBox.setMargin(connectedPlayersText, new Insets(0, 0, 5, 5));
		
		logVBox.getChildren().addAll(textLog, connectedPlayersText);
		
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
		
		settingsVBox = new VBox(20);
		settingsVBox.setStyle("-fx-background-color: #FFFFFF");
		settingsVBox.setAlignment(Pos.CENTER);
	
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
		playerNumSlider.setValue(2);
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
		settingsVBox.getChildren().addAll(logoImg, grid);
		
		// Add port field
		addPortField();
	}
	
	private void addPortField() {
		
		GridPane grid = (GridPane) settingsVBox.lookup("#settingsGrid");
		
		Label portLabel = new Label("Port: ");
		TextField portField = new TextField("9000");
		portField.setId("port");
		
		GridPane.setConstraints(portLabel, 0, 1);
		GridPane.setConstraints(portField, 1, 1);
		
		grid.getChildren().addAll(portLabel, portField);

		GridPane.setConstraints(settingsVBox.lookup("#startButton"), 0, 2+1, 2, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(settingsVBox.lookup("#exitButton"), 0, 3+1, 2, 1, HPos.CENTER, VPos.CENTER);
		
	}
	
}
