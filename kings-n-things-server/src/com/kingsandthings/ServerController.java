package com.kingsandthings;

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.common.controller.Controller;
import com.kingsandthings.common.model.PlayerManager;
import com.kingsandthings.common.network.GameServerImpl;
import com.kingsandthings.util.Dialog;

public class ServerController extends Controller {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(PlayerManager.class.getName());
	
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
		
		setupHandlers();
		
	}
	
	protected void handleStartButtonAction(Event event) {
	
		if (gameServer == null) {
			gameServer = new GameServerImpl(view.getNumPlayers());
			gameServer.start(view.getPort());
			
			view.setStatusText("Server started. Waiting for " + view.getNumPlayers() + " players to connect.");
		}
		
	}
	
	protected void handleExitButtonAction(Event event) {
		gameServer.end();
		stage.close();
	}
	
	private void setupHandlers() {
		
		Parent root = view.getRoot();
		
		addEventHandler(root, "startButton", "setOnAction", "handleStartButtonAction");
		addEventHandler(root, "exitButton", "setOnAction", "handleExitButtonAction");
		
	}
	
}
