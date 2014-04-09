package com.kingsandthings.server;
	
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ServerApplication extends Application {
	
	private ServerController serverController = new ServerController();
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("K&T - Server");
		primaryStage.getIcons().add(new Image("/images/icon.png"));
		
		serverController.initialize(primaryStage);
		
		primaryStage.show();
		
	}
	
	@Override
	public void stop() {
		
		serverController.stop();
		System.out.println("Application has stopped.");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
