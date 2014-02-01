package com.kingsandthings.game;

import java.util.logging.Logger;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.Controller;
import com.kingsandthings.MainMenuController;
import com.kingsandthings.game.board.BoardController;
import com.kingsandthings.game.rack.RackController;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	// View
	private GameView view;
	
	// Sub-controllers
	private BoardController boardController;
	private RackController rackController;
	
	// Parent controller
	private MainMenuController parent;
	
	public void initialize(Stage stage, GameSettings settings, MainMenuController parent) {
		
		this.parent = parent;
		
		view = new GameView();
		view.initialize();
		
		// Initialize sub controllers
		boardController = new BoardController();
		boardController.initialize(settings.numPlayers);
		
		rackController = new RackController();
		rackController.initialize(settings.numPlayers);
		
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
	
	public void handleAboutMenuItemAction(Event event) {
		LOGGER.info("TODO: Open about dialog (version, authors, last date modified");
	}
	
	public void handleQuitGameMenuItemAction(Event event) {
		// TODO - refactor so objects (e.g. panes) aren't being created multiple times
		parent.initialize ((Stage) view.getWindow());
	}

}
