package com.kingsandthings.gui;

import java.util.logging.Logger;

import com.kingsandthings.gui.board.BoardController;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	// View
	private GameView view = new GameView();
	
	// Sub-controllers
	private BoardController boardController = new BoardController();
	
	public void initialize(Stage stage) {
		
		Scene scene = view.initialize().getScene();
		
		// Initialize sub-controllers
		boardController.initialize(scene);
		
		stage.setScene(scene);
		stage.centerOnScreen();
		
		setupHandlers();
	}
	
	public void setupHandlers() {
		
		Parent parent = view.getScene().getRoot();
		
		addEventHandler(parent, "aboutMenuItem", "setOnAction", "handleAboutMenuItemAction");
		addEventHandler(parent, "quitGameMenuItem", "setOnAction", "handleQuitGameMenuItemAction");
		
	}
	
	public void handleAboutMenuItemAction(ActionEvent event) {
		LOGGER.info("TODO: Open about dialog (version, authors, last date modified");
	}
	
	public void handleQuitGameMenuItemAction(ActionEvent event) {
		LOGGER.info("TODO: Close menu item action");
	}

}
