package com.kingsandthings.game.rack;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.kingsandthings.game.View;

public class RackView extends Pane implements View<Node> {
	
	private static final Image rackImg = new Image("/images/extra/rack.png");
	
	// TODO - store racks mapped to players (?)
	private int numPlayers;
	
	public RackView(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	@Override
	public Node initialize() {
		
		setPrefWidth(300);
		
		getStyleClass().addAll("pane", "board");
		
		addPlayerRacks();
		
		return this;		
	}
	
	private void addPlayerRacks() {
		
		int xOffset = 25;
		int yOffset = 50;

		int yGap = 140;
		int textYOffset = 15;
		
		for (int i=0; i<numPlayers; ++i) {
			
			int y = (yGap * i) + yOffset;
			
			Text text = new Text("Player " + (i + 1));
			text.setFont(Font.font("Verdana", 20));
			text.setFill(Color.WHITE);
			text.setLayoutX(xOffset - 5);
			text.setLayoutY(y - textYOffset);
			
			ImageView rackImageView1 = getRackImageView(xOffset, y);
			ImageView rackImageView2 = getRackImageView(xOffset, y + rackImageView1.fitHeightProperty().get() + 50);
			
			getChildren().addAll(text, rackImageView1, rackImageView2);
			
		}
		
	}
	
	private ImageView getRackImageView(double x, double y) {
		
		ImageView imageView = new ImageView();
		imageView.setPreserveRatio(true);
		imageView.setCache(true);
		imageView.setFitWidth(250);
		imageView.setImage(rackImg);
		imageView.setX(x);
		imageView.setY(y);
		
		return imageView;
		
	}
	
}
