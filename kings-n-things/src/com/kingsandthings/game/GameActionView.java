package com.kingsandthings.game;

import java.beans.PropertyChangeEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.game.phase.Phase;
import com.kingsandthings.game.phase.PhaseManager;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.model.Player;

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
		
		addListeners();
		
	}
	
	private void addListeners() {
		PropertyChangeDispatcher.getInstance().addListener(PhaseManager.class, "currentPhase", this, "phaseChanged");
	}
	
	@SuppressWarnings("unused")
	private void phaseChanged(PropertyChangeEvent event) {
		
		Phase newPhase = (Phase) event.getNewValue();
		
		if (newPhase == null) {
			lookup("#skipTurn").setDisable(true);
			lookup("#endTurn").setDisable(true);
			setPhaseName("None");
			return;
		}
		
		lookup("#skipTurn").setDisable(newPhase.isMandatory());
		lookup("#endTurn").setDisable(newPhase.isMandatory() && newPhase.playerInteractionRequired());
		
		setPhaseName(newPhase.getName());
		
	}
	
	private void setPhaseName(String name) {
		Label label = (Label) lookup("#phaseName");
		label.setText("Current Phase: " + name);
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
		rollDiceButton.setDisable(true);
		
		ObservableList<String> diceRolls = FXCollections.observableArrayList("1 or 6", "1-6");
		ComboBox<String> comboBox = new ComboBox<String>(diceRolls);
		comboBox.setId("diceRollType");
		comboBox.getStyleClass().addAll("nofocus");
		comboBox.getSelectionModel().select(0);
		comboBox.setDisable(true);
		
		grid.add(rollDiceButton, 0, 0);
		grid.add(comboBox, 1, 0);
		
		getChildren().add(grid);		
		
	}
	
	private void addPhaseActions() {
		
		Label phaseName = new Label();
		phaseName.setFont(Font.font("Lucida Sans", 12));
		phaseName.setTextFill(Color.WHITE);
		phaseName.setId("phaseName");
		
		VBox.setMargin(phaseName, new Insets(100, 0, 0, 0));
		
		String currentPhaseName = PhaseManager.getInstance().getCurrentPhase().getName();
		phaseName.setText("Current Phase: " + currentPhaseName);
		
		HBox buttons = new HBox(5);
		buttons.setAlignment(Pos.CENTER);
		
		Button skipTurnButton = new Button("Skip Turn");
		skipTurnButton.setId("skipTurn");
		skipTurnButton.getStyleClass().add("nofocus");
		skipTurnButton.setDisable(true);
		
		Button endTurnButton = new Button("End Turn");
		endTurnButton.setId("endTurn");
		endTurnButton.getStyleClass().add("nofocus");
		
		endTurnButton.setDisable(PhaseManager.getInstance().getCurrentPhase().isMandatory());
		
		buttons.getChildren().addAll(skipTurnButton, endTurnButton);
		
		getChildren().addAll(phaseName, buttons);
		
		
	}

}
