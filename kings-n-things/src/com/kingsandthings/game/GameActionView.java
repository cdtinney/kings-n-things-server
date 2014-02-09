package com.kingsandthings.game;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.Game;
import com.kingsandthings.model.phase.Phase;
import com.kingsandthings.model.phase.PhaseManager;

public class GameActionView extends VBox implements InitializableView {

	@Override
	public void initialize() {
		
		setPrefWidth(235);
		getStyleClass().addAll("pane", "board");
		
		setAlignment(Pos.TOP_CENTER);
		setSpacing(10);
		
		addCup();
		addDice();
		addPhaseActions();
		
		addListeners();
		
	}
	
	@SuppressWarnings("unchecked")
	public void toggleThingList() {

		ListView<String>  list = (ListView<String>) lookup("#thingList");
		Node selectButton = lookup("#selectThings");
		
		// If its already visible, clear selection and hide
		if (list.visibleProperty().get()) {
			
			list.getSelectionModel().clearSelection();
			
			list.setVisible(false);
			selectButton.setVisible(false);
			
		} else {
			
			// Update list of Things
			list.setItems(FXCollections.observableArrayList(Game.getInstance().getCup().getThingNames()));
			
			list.setVisible(true);
			selectButton.setVisible(true);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getSelectedThings() {
		ListView<String>  list = (ListView<String>) lookup("#thingList");
		return list.getSelectionModel().getSelectedIndices();
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
		
		Button drawThingButton = new Button("Draw Thing");
		drawThingButton.setId("drawThing");
		drawThingButton.getStyleClass().addAll("nofocus");
		
		ListView<String> list = new ListView<String>();
		list.setId("thingList");
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		list.setMaxWidth(205);
		list.setPrefHeight(210);
		
		list.managedProperty().bind(list.visibleProperty());
		list.setVisible(false);
		
		Button selectThings = new Button("Select Things");
		selectThings.setId("selectThings");
		selectThings.getStyleClass().addAll("nofocus");

		selectThings.managedProperty().bind(selectThings.visibleProperty());
		selectThings.setVisible(false);
		
		getChildren().addAll(imgView, drawThingButton, list, selectThings);		
		
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
		
		VBox.setMargin(grid, new Insets(10, 0, 0, 0));
		
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
