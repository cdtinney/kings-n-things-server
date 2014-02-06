package com.kingsandthings.game.player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.kingsandthings.game.InitializableView;
import com.kingsandthings.model.Player;

public class PlayerView extends Pane implements InitializableView {
	
	private static final Image rackImg = new Image("/images/extra/rack.png");
	
	// Model
	private Player player;
	
	// View elements
	private Text playerNameText;
	private Text numGoldText;
	
	public PlayerView(Player player) {
		this.player = player;
	}
	
	@Override
	public void initialize() {

		setPrefWidth(425);
		setPrefHeight(165);
		
		getStyleClass().addAll("player");
		
		addRackImages();
		addPlayerNameText();
		addNumGoldText();
		addPlayerControlMarker();
		
		// TASK - subscribe to when Player Things are modified
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setNumGoldText(int numGold) {
		numGoldText.setText("gold: " + numGold);
	}
	
	public void setActive(boolean active) {
		
		if (active) {
			getStyleClass().add("activePlayer");
		} else {
			getStyleClass().remove("activePlayer");
		}
		
	}
	
	private void addNumGoldText() {

		numGoldText = new Text("gold: " + player.getNumGold());
		numGoldText.setFont(Font.font("Lucida Sans", 16));
		numGoldText.setFill(Color.GOLD);
		numGoldText.setLayoutX(340);
		numGoldText.setLayoutY(30);
		
		getChildren().add(numGoldText);
		
	}
	
	private void addRackImages() {
		
		ImageView rackImageView1 = getRackImageView(15, 55);
		ImageView rackImageView2 = getRackImageView(15, 55 + rackImageView1.fitHeightProperty().get() + 50);
		getChildren().addAll(rackImageView1, rackImageView2);
		
	}
	
	private void addPlayerNameText() {
		
		playerNameText = new Text(player.getName());
		playerNameText.setFont(Font.font("Lucida Sans", 20));
		playerNameText.setFill(Color.WHITE);
		playerNameText.setLayoutX(70);
		playerNameText.setLayoutY(35);
		
		getChildren().add(playerNameText);
		
	}
	
	private void addPlayerControlMarker() {
		
		ImageView controlMarker = new ImageView(player.getControlMarker());
		controlMarker.setPreserveRatio(true);
		controlMarker.setCache(true);
		controlMarker.setFitWidth(30);
		controlMarker.setX(30);
		controlMarker.setY(15);
		
		getChildren().add(controlMarker);
		
	}
	
	private ImageView getRackImageView(double x, double y) {
		
		ImageView imageView = new ImageView(rackImg);
		imageView.setPreserveRatio(true);
		imageView.setCache(true);
		imageView.setFitWidth(290);
		imageView.setX(x);
		imageView.setY(y);
		
		return imageView;
		
	}

}
