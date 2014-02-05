package com.kingsandthings;
	
import java.util.logging.Handler;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.kingsandthings.logging.LogFormatter;
import com.kingsandthings.logging.LogHandler;
import com.kingsandthings.logging.LogLevel;

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
		
		LogHandler customHandler = new LogHandler();
		customHandler.setFormatter(new LogFormatter());
		customHandler.setLevel(LogLevel.DEBUG);		
		
		parent.setLevel(LogLevel.DEBUG);
		
		parent.addHandler(customHandler);
		
	}
	
	public static void main(String[] args) {
		
		setupLogging();
		
		launch(args);
	}
}
