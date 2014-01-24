package com.kingsandthings;
	
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.kingsandthings.gui.util.WindowUtils;

public class MainApplication extends Application {
	
	private static Logger LOGGER = Logger.getLogger(MainApplication.class.getName());
	
	private final float VERSION = 0.1f;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MainMenu.fxml"));
			Parent root = (Parent) loader.load();
			
			MainMenuController controller = (MainMenuController) loader.getController();
			controller.initialize(primaryStage);
			
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/resources/MainMenu.css").toExternalForm());

			WindowUtils.positionStageCenter(primaryStage, scene);
			
			primaryStage.setTitle("Kings & Things v" + VERSION);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void stop() {
		LOGGER.info("Application has stopped.");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
