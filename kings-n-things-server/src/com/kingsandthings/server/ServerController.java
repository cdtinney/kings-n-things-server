package com.kingsandthings.server;

import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.common.controller.Controller;
import com.kingsandthings.common.network.GameServerImpl;
import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.util.Dialog;

public class ServerController extends Controller {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(ServerController.class.getName());
	
	// Primary stage
	private Stage stage;
	
	// View
	private ServerMenuView view;
	
	// Networking
	private GameServerImpl gameServer;
	
	public void initialize(Stage stage) {
		
		this.stage = stage;
		
		Dialog.setStage(stage);
		
		view = new ServerMenuView();
		view.initialize();
		
		stage.setScene(view);
		stage.centerOnScreen();
		
		addEventHandlers();
		addListeners();
		
	}
	
	protected void handleStartButtonAction(Event event) {
	
		if (gameServer == null) {
			
			int numPlayers = view.getNumPlayers();
			int port = view.getPort();
			
			gameServer = new GameServerImpl(numPlayers);
			gameServer.start(port);
			
			view.showLog();
			
			view.setStatusText("game server running on port " + port);
			view.addLogText("Server started! Waiting for " + numPlayers + " players to connect.");
			
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
		
		int connected = gameServer.numPlayersConnected();
		int remaining = gameServer.numPlayersRemaining();
		
		view.addLogText(connected + " player(s) connected. Waiting for " + remaining + " more player(s).");
		view.setConnectedPlayersText(gameServer.connectedPlayerNames());
		
	}
	
	private void addListeners() {
		PropertyChangeDispatcher.getInstance().addListener(GameServerImpl.class, "connectedPlayers", this, "onPlayerConnected");
	}
	
	private void addEventHandlers() {
		
		Parent root = view.getRoot();
		
		addEventHandler(root, "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
}
