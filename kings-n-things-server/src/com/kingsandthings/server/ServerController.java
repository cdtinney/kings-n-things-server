package com.kingsandthings.server;

import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.common.controller.Controller;
import com.kingsandthings.common.events.PropertyChangeDispatcher;
import com.kingsandthings.common.network.GameServer;
import com.kingsandthings.server.logging.ServerLogHandler;

public class ServerController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(ServerController.class.getName());
	
	// Primary stage
	private Stage stage;
	
	// View
	private ServerView view;
	
	// Networking
	private GameServer gameServer;
	
	public void initialize(Stage stage) {
		
		this.stage = stage;
		
		view = new ServerView();
		view.initialize();
		
		stage.setScene(view);
		stage.centerOnScreen();
		
		addEventHandlers();
		addListeners();
		
		ServerLogHandler.setHandler(LOGGER, view);
		
	}
	
	public void stop() {
		
		if (gameServer != null) {
			gameServer.end();
		}
		
	}
	
	protected void handleStartButtonAction(Event event) {
	
		if (gameServer == null) {
			
			int numPlayers = view.getNumPlayers();
			int port = view.getPort();
			String state = view.getGameState();
			
			gameServer = new GameServer(numPlayers, state);
			gameServer.start(port);
			
			ServerLogHandler.setServer(gameServer);
			
			view.showLog();
			view.setStatusText("game server running on port " + port);
			
		}
		
	}
	
	protected void handleExitButtonAction(Event event) {
		
		if (gameServer != null) {
			gameServer.end();
		}
		
		stage.close();
	
	}
	
	@SuppressWarnings("unused")
	private void onPlayerConnected(PropertyChangeEvent event) {
		
		int connected = gameServer.getNumConnected();
		int remaining = gameServer.getNumRemaining();
		
		view.setConnectedPlayersText(gameServer.getConnectedPlayerNames());
		
	}
	
	private void addEventHandlers() {
		
		Parent root = view.getRoot();
		
		addEventHandler(root, "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
	private void addListeners() {
		
		PropertyChangeDispatcher.getInstance().addListener(GameServer.class, "connectedPlayers", this, "onPlayerConnected");
		
	}
	
}
