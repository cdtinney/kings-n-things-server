package com.kingsandthings.game.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.kingsandthings.model.Player;

/**
 * @author Colin Tinney
 * 
 * Singleton manager class for players.
 *
 */
public final class PlayerManager {
	
	private static Logger LOGGER = Logger.getLogger(PlayerManager.class.getName());
	
	private static final PlayerManager INSTANCE = new PlayerManager();
	
	private Integer numPlayers;
	
	private Map<String, Player> players;
	private Map<Player, Integer> positions;
	
	public PlayerManager() {
		
		if (INSTANCE != null) {
			LOGGER.warning("Already instantiated");
		}
		
		players = new HashMap<String, Player>();
		positions = new HashMap<Player, Integer>();
		
	}
	
	public static PlayerManager getInstance() {
		return INSTANCE;
	}
	
	public List<Player> getPlayers() {
		return new ArrayList<Player>(players.values());
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = (this.numPlayers == null) ? numPlayers : this.numPlayers;
	}
	
	public boolean addAllPlayers(List<String> names) {
		
		boolean modified = false;
		
		for (String name : names) {
			modified = addPlayer(name) == true ? true : modified;
		}
		
		return modified;
	}

	public boolean addPlayer(String name) {
		
		if (name == null || name.trim().length() == 0) {
			LOGGER.warning("Cannot add player with with null or empty name.");
			return false;
		}
		
		if (numPlayers == null || numPlayers == 0) {
			LOGGER.warning("Cannot add players before maximum number of players is set.");
			return false;
		}

		if (players.size() > numPlayers) {
			LOGGER.warning("Cannot add additional players - maximum number of players reached.");
			return false;
		}
		
		if (players.containsKey(name)) {
			LOGGER.warning("Player '" + name + "' has already been added.");
			return false;
		}
		
		Player player = new Player(name);
		
		players.put(name, player);
		setInitialPosition(player, players.size());
		
		return true;
	}
	
	public boolean setInitialPosition(Player player, int position) {
		
		if (players.containsKey(player.getName())) {
			
			if (positions.containsKey(player)) {
				LOGGER.warning("Player is already assigned an initial position.");
				return false;
			}
			
			if (positions.containsValue(position)) {
				LOGGER.warning("Initial position " + position + " already assigned to player.");
				return false;
			}
			
			positions.put(player, position);
			
		}
		
		return false;		
	}
	
	public int getPosition(Player player) { 
		
		if (positions.containsKey(player)) {
			return positions.get(player);
		}
		
		return 0;
		
	}

}
