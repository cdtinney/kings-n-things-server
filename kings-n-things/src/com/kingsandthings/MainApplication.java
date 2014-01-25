package com.kingsandthings;
	
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	private static Logger LOGGER = Logger.getLogger(MainApplication.class.getName());
	
	private final float VERSION = 0.1f;
	
	private MainMenuController mainMenuController = new MainMenuController();
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Kings & Things v" + VERSION);
		
		mainMenuController.initialize(primaryStage);
		
		primaryStage.show();
		
	}
	
	@Override
	public void stop() {
		LOGGER.info("Application has stopped.");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
