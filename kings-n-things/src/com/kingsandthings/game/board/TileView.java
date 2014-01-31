package com.kingsandthings.game.board;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TileView extends ImageView {
	
	private static final int WIDTH = 100;
	
	private static Image defaultImg = new Image("/images/hex/tile_default.png");
	private Image tileImg;
	
	private List<TileView> neighbours;
	
	public TileView (String id, int x, int y) {
		
		neighbours = new ArrayList<TileView>();
		
		if (id != null) {
			setId(id);
		}
		
		setImage(defaultImg);

		setPreserveRatio(true);
		setCache(true);
		
		setFitWidth(WIDTH);
		setX(x);
		setY(y);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ImageView image = (ImageView) event.getSource();
				image.setImage(defaultImg);				
				
			};
		
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ImageView image = (ImageView) event.getSource();
				image.setImage(tileImg == null? defaultImg : tileImg);
				
			};
		
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println( ((Node) event.getSource()).getId() + " clicked");
			}
			
		});
	
	}

	public void setTileImage(Image image) {
		
		if (image != null) {
			setImage(image);
		}
		
		tileImg = image;
	}
	
	public List<TileView> getNeighbours() {
		return neighbours;
	}
	
	public void addNeighbour(TileView tileView) {
		neighbours.add(tileView);
	}

}
