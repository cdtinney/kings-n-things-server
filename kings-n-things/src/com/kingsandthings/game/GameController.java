package com.kingsandthings.game;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.Controller;
import com.kingsandthings.MainMenuController;
import com.kingsandthings.game.board.BoardController;
import com.kingsandthings.game.player.PlayerManager;
import com.kingsandthings.game.rack.RackController;
import com.kingsandthings.model.Player;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	// View
	private GameView view;
	
	// Sub-controllers
	private BoardController boardController;
	private RackController rackController;
	
	// Parent controller
	private MainMenuController parent;
	
	public void initialize(Stage stage, List<String> playerNames, MainMenuController parent) {
		
		this.parent = parent;
		
		view = new GameView();
		view.initialize();
		
		PlayerManager manager = PlayerManager.getInstance();
		
		manager.setNumPlayers(playerNames.size());
		manager.addAllPlayers(playerNames);
		
		List<Player> players = manager.getPlayers();
		
		// Initialize sub controllers
		boardController = new BoardController();
		boardController.initialize(players);
		
		rackController = new RackController();
		rackController.initialize(players);
		
		// Add sub-views
		view.addToBorderPane(boardController.getView(), "center");
		view.addToBorderPane(rackController.getView(), "right");
		
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
		LOGGER.info("TODO: Open about dialog (version, authors, last date modified");
	}
	
	protected void handleQuitGameMenuItemAction(Event event) {
		// TODO - refactor so objects (e.g. panes) aren't being created multiple times
		parent.initialize ((Stage) view.getWindow());
	}

}
