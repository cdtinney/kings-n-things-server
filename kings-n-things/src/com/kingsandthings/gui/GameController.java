package com.kingsandthings.gui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.kingsandthings.gui.util.WindowUtils;

public class GameController extends Controller {
	
	private static Logger LOGGER = Logger.getLogger(GameController.class.getName());
	
	private final String MAIN_MENU_VIEW = "/resources/MainMenu.fxml";
	
	public BorderPane rootPane;

	public void initialize(Stage stage) {
		
		addMenuBar(rootPane);
		
	}
	
	public void handleAboutMenuItemAction(ActionEvent event) {
		LOGGER.info("TODO: Open about dialog (version, authors, last date modified");
	}
	
	public void handleCloseMenuItemAction(ActionEvent event) {
		WindowUtils.changeScene(rootPane, true, MAIN_MENU_VIEW, 600, 400);
	}
	
	private void addMenuBar(BorderPane pane) {
		
		MenuBar menuBar = new MenuBar();
		
		// Game menu
		Menu gameMenu = new Menu("Game");
		
		MenuItem closeItem = new MenuItem("Quit Game");
		addEventHandler(closeItem, "setOnAction", "handleCloseMenuItemAction");
		
		gameMenu.getItems().addAll(closeItem);
		
		// Help menu
		Menu helpMenu = new Menu("Help");
		
		MenuItem aboutItem = new MenuItem("About");
		addEventHandler(aboutItem, "setOnAction", "handleAboutMenuItemAction");
		
		helpMenu.getItems().addAll(aboutItem);
		
		// Add all menus to bar
		menuBar.getMenus().addAll(gameMenu, helpMenu);
		
		pane.setTop(menuBar);
		
	}

}
