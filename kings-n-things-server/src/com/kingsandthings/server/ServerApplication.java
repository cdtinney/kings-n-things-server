package com.kingsandthings.server;
	
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.kingsandthings.logging.LogHandler;

public class ServerApplication extends Application {
	
	private static Logger LOGGER = Logger.getLogger(ServerApplication.class.getName());
	
	private ServerController serverController = new ServerController();
	
	@Override
	public void start(Stage primaryStage) {
		
		LogHandler.setHandler(LOGGER);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("SERVER");
		primaryStage.getIcons().add(new Image("/images/icon.png"));
		
		serverController.initialize(primaryStage);
		
		primaryStage.show();
		
	}
	
	@Override
	public void stop() {
		
		serverController.stop();
		LOGGER.info("Application has stopped.");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
