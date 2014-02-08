package com.kingsandthings.game.player;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import com.kingsandthings.game.InitializableView;
import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.Rack;
import com.kingsandthings.model.things.Thing;

public class PlayerPane extends VBox implements InitializableView {
	
	private List<PlayerView> playerViews;
	
	public PlayerPane(List<Player> players) {
		
		playerViews = new ArrayList<PlayerView>();
		for (Player player : players) {
			playerViews.add(new PlayerView(player));
		}
		
	}

	@Override
	public void initialize() {
		
		getStyleClass().addAll("pane", "board");
		
		addPlayerViews();
		
		PropertyChangeDispatcher.getInstance().addListener(PlayerManager.class, "activePlayer", this, "updateActivePlayer");
		PropertyChangeDispatcher.getInstance().addListener(Player.class, "numGold", this, "updatePlayerGold");
		PropertyChangeDispatcher.getInstance().addListener(Rack.class, "things", this, "updatePlayerRack");
	}
	
	public List<PlayerView> getPlayerViews() {
		return playerViews;
	}
	
	private void addPlayerViews() {
		
		for (PlayerView playerView: playerViews) {
			
			playerView.initialize();

			// Check current active player
			if (PlayerManager.getInstance().getActivePlayer() == playerView.getPlayer()) {
				playerView.setActive(true);
			}
			
			setMargin(playerView, new Insets(30, 10, 0, 10));
			getChildren().add(playerView);
			
		}
		
	}
	
	private PlayerView getPlayerView(Player player) {
		
		for (PlayerView playerView : playerViews) {
			if (playerView.getPlayer() == player) {
				return playerView;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private void updateActivePlayer(PropertyChangeEvent event) {
		
		Player oldPlayer = (Player) event.getOldValue();
		Player newPlayer = (Player) event.getNewValue();
		
		if (oldPlayer != null) {
			getPlayerView(oldPlayer).setActive(false);
		}
		
		if (newPlayer != null) {
			getPlayerView(newPlayer).setActive(true);
		}
		
	}
	
	@SuppressWarnings("unused")
	private void updatePlayerGold(PropertyChangeEvent event) {
		
		Player player = (Player) event.getSource();
		int numGold = player.getNumGold();
		
		getPlayerView(player).setNumGoldText(numGold);
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void updatePlayerRack(PropertyChangeEvent event) {
		
		Rack rack = (Rack) event.getSource();
		
		List<Thing> newThings = (List<Thing>) event.getNewValue();
		
		getPlayerView(rack.getOwner()).setRackThings(newThings);
		
	}
	
}
