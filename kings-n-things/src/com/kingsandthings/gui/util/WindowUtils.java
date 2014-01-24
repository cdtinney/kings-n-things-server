package com.kingsandthings.gui.util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import com.kingsandthings.gui.Controller;

public class WindowUtils {
	
	public static void changeScene(ActionEvent event, boolean init, String fxmlPath, int width, int height) {
		changeScene(getStage(event), init, fxmlPath, width, height);		
	}
	
	public static void changeScene(Node node, boolean init, String fxmlPath, int width, int height) {
		changeScene(getStage(node), init, fxmlPath, width, height);	
	}
	
	private static void changeScene(Stage stage, boolean init, String fxmlPath, int width, int height) {
		
		try {
			
			// Load FXML
			FXMLLoader loader = new FXMLLoader(WindowUtils.class.getClass().getResource(fxmlPath));
			Parent root = (Parent) loader.load();
			
			Scene scene = new Scene(root, width, height);  
			WindowUtils.positionStageCenter(stage, scene); 
			
			// Retrieve controller and initialize
			Controller controller = (Controller) loader.getController();
			controller.initialize(stage);			
			
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static void positionStageCenter(Stage stage, Scene scene) {
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();  
		stage.setX( (primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - scene.getWidth()) / 2);  
		stage.setY(( primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - scene.getHeight()) / 2);  
		
	}
	
	private static Stage getStage(ActionEvent event) {
		return getStage((Node) event.getSource());
	}
	
	private static Stage getStage(Node node) {
		return (Stage) node.getScene().getWindow();
	}

	public static void closeStage(ActionEvent event) {
		getStage(event).close();
	}

}
