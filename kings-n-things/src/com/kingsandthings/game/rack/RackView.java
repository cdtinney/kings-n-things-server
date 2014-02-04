package com.kingsandthings.game.rack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
		
		final RackView instance = this;
		
		PlayerManager.INSTANCE.addChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				
				String oldName = ((Player) event.getOldValue()).getName();
				Text oldText = (Text) instance.lookup("#" + oldName.replaceAll("\\s+",""));
				oldText.setText(oldName);
				
				if (event.getNewValue() != null) {
					String newName = ((Player) event.getNewValue()).getName();
					Text newText = (Text) instance.lookup("#" + newName.replaceAll("\\s+",""));
					newText.setText("* " + newName);
				}
				
			}
			
		});
	}
	
	private void addPlayerRacks() {
		
		int xOffset = 25;
		int yOffset = 50;

		int yGap = 140;
		int textYOffset = 15;
		
		for (int i=0; i<numPlayers; ++i) {
			
			Player player = players.get(i);

			String name = player.getName();
			
			String displayText = name;
			
			if (PlayerManager.INSTANCE.getActivePlayer() == player) {
				displayText = "* " + displayText;
			}
			
			int pos = PlayerManager.INSTANCE.getPosition(player);
			int y = (yGap * (pos -1)) + yOffset;
			
			ImageView controlMarker = new ImageView(player.getControlMarker());
			controlMarker.setPreserveRatio(true);
			controlMarker.setCache(true);
			controlMarker.setFitWidth(20);
			controlMarker.setX(xOffset - 5);
			controlMarker.setY(y - 32);
			
			Text text = new Text(displayText);
			text.setId(name.replaceAll("\\s+",""));
			text.setFont(Font.font("Lucida Sans", 20));
			text.setFill(Color.WHITE);
			text.setLayoutX(xOffset - 5 + 27);
			text.setLayoutY(y - textYOffset);
			
			ImageView rackImageView1 = getRackImageView(xOffset, y);
			ImageView rackImageView2 = getRackImageView(xOffset, y + rackImageView1.fitHeightProperty().get() + 50);
			getChildren().addAll(text, rackImageView1, rackImageView2, controlMarker);
			
		}
		
	}
	
	private ImageView getRackImageView(double x, double y) {
		
		ImageView imageView = new ImageView(rackImg);
		imageView.setPreserveRatio(true);
		imageView.setCache(true);
		imageView.setFitWidth(250);
		imageView.setX(x);
		imageView.setY(y);
		
		return imageView;
		
	}
	
}
