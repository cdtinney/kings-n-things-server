package com.kingsandthings;
	
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.kingsandthings.util.CustomFormatter;

public class MainApplication extends Application {
	
	private static Logger LOGGER = Logger.getLogger(MainApplication.class.getName());
	
	private final float VERSION = 0.1f;
	
	private MainMenuController mainMenuController = new MainMenuController();
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Kings & Things v" + VERSION);
		primaryStage.getIcons().add(new Image("/images/icon.png"));
		
		mainMenuController.initialize(primaryStage);
		
		primaryStage.show();
		
	}
	
	@Override
	public void stop() {
		LOGGER.info("Application has stopped.");
	}
	
	private static void setupLogging() {
		
		Logger parent = LOGGER.getParent();
		
		for (Handler handler: parent.getHandlers()) {
			parent.removeHandler(handler);
		}
		
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new CustomFormatter());
		
		parent.addHandler(consoleHandler);
		
	}
	
	public static void main(String[] args) {
		
		setupLogging();
		
		launch(args);
	}
}
