package com.kingsandthings.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		
		addCup();
		addDice();
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
		
		Button diceButton = new Button("Roll Dice", imgView);
		diceButton.getStyleClass().addAll("diceButton", "nofocus");
		diceButton.setPrefHeight(40);
		diceButton.setPrefWidth(130);
		
		ObservableList<String> diceRolls = FXCollections.observableArrayList("1 or 6", "1-6");
		ComboBox<String> comboBox = new ComboBox<String>(diceRolls);
		comboBox.getStyleClass().addAll("nofocus");
		comboBox.getSelectionModel().select(0);
		
		grid.add(diceButton, 0, 0);
		grid.add(comboBox, 1, 0);
		
		getChildren().add(grid);		
		
	}

}
