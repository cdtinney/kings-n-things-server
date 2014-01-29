package com.kingsandthings.game;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GameView extends Scene implements View<Scene> {
	
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	private BorderPane root;
	
	public GameView() {
		super(new BorderPane(), WIDTH, HEIGHT);
		
		root = (BorderPane) getRoot();
		getStylesheets().add(getClass().getResource("/css/Game.css").toExternalForm());
	}
	
	@Override
	public Scene initialize() {
		
		addPanes();
		addMenuBar();
		
		return this;
		
	}
	
	public void addToBorderPane(Node node, String position) {
		
		if (position.equals("center")) {
			root.setCenter(node);
			
		} else if (position.equals("bottom")) {
			root.setBottom(node);
			
		} else if (position.equals("top")) {
			root.setTop(node);
			
		} else if (position.equals("left")) {
			root.setLeft(node);
			
		} else if (position.equals("right")) {
			root.setRight(node);
			
		}
		
	}
	
	private void addPanes() {
		
		ImageView bowlImageView = new ImageView();
		bowlImageView.setFitWidth(200);
		bowlImageView.setPreserveRatio(true);
		bowlImageView.setCache(true);
		bowlImageView.setImage(new Image("/images/Bowl.png"));
		
	}
	
	private void addMenuBar() {
		
		MenuBar menuBar = new MenuBar();
		
		// Game menu
		Menu gameMenu = new Menu("Game");
		
		MenuItem quitGameItem = new MenuItem("Quit Game");
		quitGameItem.setId("quitGameMenuItem");
		
		gameMenu.getItems().addAll(quitGameItem);
		
		// Help menu
		Menu helpMenu = new Menu("Help");
		
		MenuItem aboutItem = new MenuItem("About");
		aboutItem.setId("aboutMenuItem");
		
		helpMenu.getItems().addAll(aboutItem);
		
		// Add all menus to bar
		menuBar.getMenus().addAll(gameMenu, helpMenu);
		
		root.setTop(menuBar);
		
	}
	
}
