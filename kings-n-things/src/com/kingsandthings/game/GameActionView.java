package com.kingsandthings.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameActionView extends VBox implements InitializableView {

	@Override
	public void initialize() {
		
		setPrefWidth(225);
		getStyleClass().addAll("pane", "board");
		
		setAlignment(Pos.TOP_CENTER);
		setSpacing(10);
		
		addCup();
		addDice();
		addPhaseActions();
	}
	
	private void addCup() {
		
		ImageView imgView = new ImageView(new Image("/images/extra/cup.png"));
		imgView.setPreserveRatio(true);
		imgView.setFitWidth(200);
		
		getChildren().add(imgView);		
		
	}
	
	private void addDice() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setAlignment(Pos.CENTER);
		
		ImageView imgView = new ImageView(new Image("/images/extra/dice.png"));
		imgView.setPreserveRatio(true);
		imgView.setFitWidth(30);
		
		Button rollDiceButton = new Button("Roll Dice", imgView);
		rollDiceButton.setId("rollDice");
		rollDiceButton.getStyleClass().addAll("diceButton", "nofocus");
		rollDiceButton.setPrefHeight(40);
		rollDiceButton.setPrefWidth(130);
		//rollDiceButton.setDisable(true);
		
		ObservableList<String> diceRolls = FXCollections.observableArrayList("1 or 6", "1-6");
		ComboBox<String> comboBox = new ComboBox<String>(diceRolls);
		comboBox.setId("diceRollType");
		comboBox.getStyleClass().addAll("nofocus");
		comboBox.getSelectionModel().select(0);
		//comboBox.setDisable(true);
		
		grid.add(rollDiceButton, 0, 0);
		grid.add(comboBox, 1, 0);
		
		getChildren().add(grid);		
		
	}
	
	private void addPhaseActions() {
		
		Button skipPhaseButton = new Button("Skip phase");
		skipPhaseButton.getStyleClass().add("nofocus");
		skipPhaseButton.setDisable(true);
		
		VBox.setMargin(skipPhaseButton, new Insets(20, 0, 0, 0));
		
		getChildren().add(skipPhaseButton);
		
		
	}

}
