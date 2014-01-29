package com.kingsandthings.game.board;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TileView extends ImageView {
	
	private static Image tileImg = new Image("/images/tile.png");
	private static Image tileHoverImg = new Image("/images/tile_hover.png");
	
	public TileView (String id, int x, int y) {
		
		if (id != null) {
			setId(id);
		}
		
		setImage(tileImg);

		setPreserveRatio(true);
		setCache(true);
		
		setFitWidth(75);
		setX(x);
		setY(y);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ImageView image = (ImageView) event.getSource();
				image.setImage(tileHoverImg);				
				
			};
		
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ImageView image = (ImageView) event.getSource();
				image.setImage(tileImg);				
				
			};
		
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println( ((Node) event.getSource()).getId() + " clicked");
			}
			
		});
	
	}

}
