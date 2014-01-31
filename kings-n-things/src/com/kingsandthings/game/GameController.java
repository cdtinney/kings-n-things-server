package com.kingsandthings.game;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.kingsandthings.Controller;
import com.kingsandthings.MainMenuController;
import com.kingsandthings.game.board.BoardController;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	// View
	private GameView view;
	
	// Sub-controllers
	private BoardController boardController;
	
	// Parent controller
	private MainMenuController parent;
	
	public void initialize(Stage stage, GameSettings settings, MainMenuController parent) {
		
		this.parent = parent;
		
		view = new GameView();
		view.initialize();
		
		// Initialize sub controllers
		boardController = new BoardController();
		boardController.initialize(settings.numPlayers);
		
		// TODO - use settings.playernames with some controller to display them all
		
		// Add sub-views
		view.addToBorderPane(boardController.getView(), "center");
		
		stage.setScene(view);
		stage.centerOnScreen();
		
		setupHandlers();
	}
	
	public void setupHandlers() {
		
		Parent parent = view.getRoot();
		
		addEventHandler(parent, "aboutMenuItem", "setOnAction", "handleAboutMenuItemAction");
		addEventHandler(parent, "quitGameMenuItem", "setOnAction", "handleQuitGameMenuItemAction");
		
	}
	
	public void handleAboutMenuItemAction(ActionEvent event) {
		LOGGER.info("TODO: Open about dialog (version, authors, last date modified");
	}
	
	public void handleQuitGameMenuItemAction(ActionEvent event) {
		// TODO - refactor so objects (e.g. panes) aren't being created multiple times
		parent.initialize ((Stage) view.getWindow());
	}

}
