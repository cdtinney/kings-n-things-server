package com.kingsandthings.game;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GameActionView extends VBox implements InitializableView {

	@Override
	public void initialize() {
		
		setPrefWidth(225);
		getStyleClass().addAll("pane", "board");
		
		addCup();
	}
	
	private void addCup() {
		
		ImageView imgView = new ImageView(new Image("/images/extra/cup.png"));
		imgView.setPreserveRatio(true);
		imgView.setFitWidth(200);
		
		VBox.setMargin(imgView, new Insets(0, 0, 0, 10));
		
		getChildren().add(imgView);		
		
	}

}
