package com.kingsandthings.game;

import java.util.List;

public class GameSettings {
	
	public int numPlayers;
	public List<String> playerNames;
	
	public GameSettings(int numPlayers, List<String> playerNames) {
		this.numPlayers = numPlayers;
		this.playerNames = playerNames;
	}

}
