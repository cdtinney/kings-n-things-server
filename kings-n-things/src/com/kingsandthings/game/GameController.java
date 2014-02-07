package com.kingsandthings.game;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.Controller;
import com.kingsandthings.MainMenuController;
import com.kingsandthings.game.board.BoardController;
import com.kingsandthings.game.phase.Phase;
import com.kingsandthings.game.phase.PhaseManager;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.game.player.PlayerPaneController;
import com.kingsandthings.model.Player;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	// View
	private GameView view;
	
	// Sub-controllers
	private BoardController boardController;
	private PlayerPaneController playerController;
	private GameActionController gameActionController;
	
	// Parent controller
	private MainMenuController parent;
	
	public void initialize(Stage stage, List<String> playerNames, MainMenuController parent) {
		
		this.parent = parent;
		
		view = new GameView();
		view.initialize();
		
		PlayerManager playerManager = PlayerManager.getInstance();
		
		playerManager.setNumPlayers(playerNames.size());
		playerManager.addAllPlayers(playerNames);
		
		PhaseManager phaseManager = PhaseManager.getInstance();
		Phase firstPhase = phaseManager.getCurrentPhase();
		firstPhase.begin();
		
		List<Player> players = playerManager.getPlayers();
		
		// Initialize sub controllers
		boardController = new BoardController();
		boardController.initialize(players);
		
		playerController = new PlayerPaneController();
		playerController.initialize(players);
		
		gameActionController = new GameActionController();
		gameActionController.initialize();
		
		// Add sub-views
		view.addToBorderPane(boardController.getView(), "center");
		view.addToBorderPane(playerController.getView(), "right");
		view.addToBorderPane(gameActionController.getView(), "left");
		
		stage.setScene(view);
		stage.centerOnScreen();
		
		setupHandlers();
	}
	
	public void setupHandlers() {
		
		Parent parent = view.getRoot();
		
		addEventHandler(parent, "aboutMenuItem", "setOnAction", "handleAboutMenuItemAction");
		addEventHandler(parent, "quitGameMenuItem", "setOnAction", "handleQuitGameMenuItemAction");
		 
	}
	
	protected void handleAboutMenuItemAction(Event event) {
		LOGGER.info("TODO: Open about dialog");
	}
	
	protected void handleQuitGameMenuItemAction(Event event) {
		// TODO - refactor so objects (e.g. panes) aren't being created multiple times
		parent.initialize ((Stage) view.getWindow());
	}

}
