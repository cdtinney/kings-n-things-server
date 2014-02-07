package com.kingsandthings.game.player;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.kingsandthings.game.InitializableView;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.things.Thing;
import com.kingsandthings.util.DataImageView;

public class PlayerView extends Pane implements InitializableView {
	
	private static final Image rackImg = new Image("/images/extra/rack.png");
	
	private static final int RACK_ITEM_HOVER_WIDTH = 85;
	private static final int RACK_ITEM_WIDTH = 30;
	private static final int RACK_ITEM_GAP = 23;
	
	private static final int INITIAL_RACK_ITEM_X = 37;
	private static final int INITIAL_RACK_X = 15;
	private static final int INITIAL_RACK_Y = 55;
	
	// Model
	private Player player;
	
	// View elements
	private Text playerNameText;
	private Text numGoldText;
	
	private List<DataImageView> rackItems;
	
	private ImageView currentHoverImage;
	
	public PlayerView(Player player) {
		this.player = player;
	}
	
	@Override
	public void initialize() {

		setPrefWidth(425);
		setPrefHeight(165);
		
		getStyleClass().addAll("player");
		
		rackItems = new ArrayList<DataImageView>();
		
		addRackImages();
		addPlayerNameText();
		addNumGoldText();
		addPlayerControlMarker();
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<DataImageView> getRackImageViews() {
		return rackItems;
	}
	
	public void setRackThings(List<Thing> things) {
		
		for (int i=0; i<things.size(); ++i) {
			
			Thing thing = things.get(i);
			
			rackItems.get(i).setImage(thing.getImage());
			rackItems.get(i).setData(thing);
			
		}
		
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
	
	public void showHoverImage(ImageView rackImageView) {
		
		double x = rackImageView.getLayoutX();
		double y = rackImageView.getLayoutY();

		Image image = rackImageView.getImage();
		
		if (currentHoverImage == null) {
			currentHoverImage = new ImageView();
			currentHoverImage.setOpacity(0.95);
			currentHoverImage.setPreserveRatio(true);
			currentHoverImage.setFitWidth(RACK_ITEM_HOVER_WIDTH);
			currentHoverImage.managedProperty().bind(currentHoverImage.visibleProperty());
			
			getChildren().add(currentHoverImage);
		}
		
		currentHoverImage.setImage(image);
		
		// Compute y
		double imageHeight = currentHoverImage.getBoundsInParent().getHeight();
		double viewHeight = PlayerView.this.getHeight();
		
		double imageY = y;
		if (imageY + imageHeight > viewHeight) {
			imageY -= (imageHeight / 2);
		}
		
		currentHoverImage.relocate(x+RACK_ITEM_WIDTH+5, imageY);
		currentHoverImage.setVisible(true);
		
	}
	
	public void hideHoverImage() {
		
		if (currentHoverImage != null) {
			currentHoverImage.setVisible(false);
		}
		
	}

	private void addRackImages() {
		
		// Add the actual images of the rack
		ImageView rackImageView1 = getRackImageView(INITIAL_RACK_X, INITIAL_RACK_Y);
		ImageView rackImageView2 = getRackImageView(INITIAL_RACK_X, INITIAL_RACK_Y + rackImageView1.fitHeightProperty().get() + 50);
		
		getChildren().addAll(rackImageView1, rackImageView2);
		
		// Create placeholder images for the items on the rack
		for (int i=0; i<10; ++i) {
			
			DataImageView imgView = new DataImageView(this);
			imgView.setPreserveRatio(true);
			imgView.setFitWidth(RACK_ITEM_WIDTH);
			
			// Compute the coordinates
			final int x = INITIAL_RACK_ITEM_X + (i%5)*RACK_ITEM_WIDTH + (i%5)*RACK_ITEM_GAP;
			final int y = (i < 5 ? INITIAL_RACK_Y : INITIAL_RACK_Y + 50);
			imgView.relocate(x, y);
			
			rackItems.add(imgView);
			
			getChildren().add(imgView);
			
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
