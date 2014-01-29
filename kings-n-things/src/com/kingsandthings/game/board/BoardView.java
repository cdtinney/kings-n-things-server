package com.kingsandthings.game.board;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import com.kingsandthings.game.View;
import com.kingsandthings.model.Board;

public class BoardView extends Pane implements View<Node> {
	
	// Model
	private Board board;
	
	// View elements
	private TileView[][] tiles = new TileView[10][10];
	
	public BoardView(int numPlayers) {
		
		board = new Board(numPlayers);
		
		// get tiles from board
		// loop through tiles, get (x,y)
		// create new tile view with (x,y) and image from tile
		
	}

	@Override
	public Node initialize() {
		
		getStyleClass().addAll("pane", "board");
		
		Canvas canvas = new Canvas(300, 300);
		
		canvas.setLayoutX(300);
		canvas.setLayoutY(200);
		
		getChildren().add(canvas);
		
		int initialX = 450;
		int initialY = 300;
		
		int xOffset = 75;
		int yOffset = 75;
		
		int hexagonSideLength = 4;
		
		for (int i=0; i<hexagonSideLength; ++i) {
			
			// center tile (0,0)
			if (i == 0) {
				TileView tile = new TileView(null, initialX, initialY);
				getChildren().add(tile);
			}
			
			int numTiles = i*hexagonSideLength + i*2;
			
			for (int j=0; j<numTiles; ++j) {

				//getChildren().add(tile);
			}
			
		}
		
		return this;
	}
	
//	public static float CalculateH(float side) {
//	    return (float) (Math.sin(DegreesToRadians(30)) * side);
//	}
//
//	public static float CalculateR(float side) {
//	    return (float) (Math.cos(DegreesToRadians(30)) * side);
//	} 
//	
//	public static double DegreesToRadians(double degrees) {
//	    return degrees * Math.PI / 180;
//	}

}
