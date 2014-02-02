package com.kingsandthings.game.rack;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.kingsandthings.game.InitializableView;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.model.Player;

public class RackView extends Pane implements InitializableView {
	
	private static final Image rackImg = new Image("/images/extra/rack.png");
	
	private int numPlayers;
	
	private List<Player> players;
	
	public RackView(List<Player> players) {
		this.numPlayers = players.size();
		this.players = players;
	}

	@Override
	public void initialize() {
		
		setPrefWidth(300);
		
		getStyleClass().addAll("pane", "board");
		
		addPlayerRacks();	
	}
	
	private void addPlayerRacks() {
		
		int xOffset = 25;
		int yOffset = 50;

		int yGap = 140;
		int textYOffset = 15;
		
		for (int i=0; i<numPlayers; ++i) {

			String name = players.get(i).getName();
			int pos = PlayerManager.INSTANCE.getPosition(players.get(i));
			
			int y = (yGap * (pos -1)) + yOffset;
			
			Text text = new Text(name + " - [position " + pos + "]");
			text.setFont(Font.font("Lucida Sans", 20));
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
