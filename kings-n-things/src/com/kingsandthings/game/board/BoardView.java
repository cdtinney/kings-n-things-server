package com.kingsandthings.game.board;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import com.kingsandthings.game.View;
import com.kingsandthings.model.board.Tile;

public class BoardView extends Pane implements View<Node> {
	
	// View elements
	private TileView[][] tiles = new TileView[10][10];
	
	public BoardView(int numPlayers) {
		
		
	}
	
	@Override
	public Node initialize() {
		
		getStyleClass().addAll("pane", "board");
		
		int initialX = 50;
		int initialY = 150;
		
		int xOffset = 74;
		int yOffset = 84;
		
		int columnOffset = 40;
		
		tiles = generateTiles(initialX, initialY, xOffset, yOffset, columnOffset, 10);
		
		addTiles(tiles);
		
		return this;
	}
	
	public void setTiles(Tile[][] modelTiles) {
		
		for (int i=0; i<modelTiles.length; ++i) {
			for (int j=0; j<modelTiles[i].length; ++j) {
				
				Tile tile = modelTiles[i][j];
				TileView view = tiles[i][j];
				
				if (tile != null && view != null) {
					view.setTileImage(tile.getImage());
				}
				
			}
		}
		
	}

	public TileView[][] getTiles() {
		return tiles;
	}
	
	private TileView[][] generateTiles(int initialX, int initialY, int xOffset, int yOffset, int columnOffset, int size) {

		TileView[][] tiles = new TileView[size][size];
		
		// column 0
		tiles[0][0] = new TileView(null, initialX, initialY);
		tiles[1][0] = new TileView(null, initialX, initialY + yOffset);
		tiles[2][0] = new TileView(null, initialX, initialY + yOffset*2);
		tiles[3][0] = new TileView(null, initialX, initialY + yOffset*3);
		
		// column 1
		tiles[0][1] = new TileView(null, initialX + xOffset, initialY - columnOffset);
		tiles[1][1] = new TileView(null, initialX + xOffset, initialY + yOffset - columnOffset);
		tiles[2][1] = new TileView(null, initialX + xOffset, initialY + yOffset*2 - columnOffset);
		tiles[3][1] = new TileView(null, initialX + xOffset, initialY + yOffset*3 - columnOffset);
		tiles[4][1] = new TileView(null, initialX + xOffset, initialY + yOffset*4 - columnOffset);
		
		// column 3
		tiles[0][2] = new TileView(null, initialX + xOffset*2, initialY - columnOffset*2);
		tiles[1][2] = new TileView(null, initialX + xOffset*2, initialY + yOffset - columnOffset*2);
		tiles[2][2] = new TileView(null, initialX + xOffset*2, initialY + yOffset*2 - columnOffset*2);
		tiles[3][2] = new TileView(null, initialX + xOffset*2, initialY + yOffset*3 - columnOffset*2);
		tiles[4][2] = new TileView(null, initialX + xOffset*2, initialY + yOffset*4 - columnOffset*2);
		tiles[5][2] = new TileView(null, initialX + xOffset*2, initialY + yOffset*5 - columnOffset*2);
		
		// column 4 (center)
		tiles[0][3] = new TileView(null, initialX + xOffset*3, initialY - columnOffset*3);
		tiles[1][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset - columnOffset*3);
		tiles[2][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset*2 - columnOffset*3);
		tiles[3][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset*3 - columnOffset*3);
		tiles[4][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset*4 - columnOffset*3);
		tiles[5][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset*5 - columnOffset*3);
		tiles[6][3] = new TileView(null, initialX + xOffset*3, initialY + yOffset*6 - columnOffset*3);
		
		// column 5
		tiles[0][4] = new TileView(null, initialX + xOffset*4, initialY - columnOffset*2);
		tiles[1][4] = new TileView(null, initialX + xOffset*4, initialY + yOffset - columnOffset*2);
		tiles[2][4] = new TileView(null, initialX + xOffset*4, initialY + yOffset*2 - columnOffset*2);
		tiles[3][4] = new TileView(null, initialX + xOffset*4, initialY + yOffset*3 - columnOffset*2);
		tiles[4][4] = new TileView(null, initialX + xOffset*4, initialY + yOffset*4 - columnOffset*2);
		tiles[5][4] = new TileView(null, initialX + xOffset*4, initialY + yOffset*5 - columnOffset*2);

		// column 6
		tiles[0][5] = new TileView(null, initialX + xOffset*5, initialY - columnOffset);
		tiles[1][5] = new TileView(null, initialX + xOffset*5, initialY + yOffset - columnOffset);
		tiles[2][5] = new TileView(null, initialX + xOffset*5, initialY + yOffset*2 - columnOffset);
		tiles[3][5] = new TileView(null, initialX + xOffset*5, initialY + yOffset*3 - columnOffset);
		tiles[4][5] = new TileView(null, initialX + xOffset*5, initialY + yOffset*4 - columnOffset);
		
		// column 7
		tiles[0][6] = new TileView(null, initialX + xOffset*6, initialY);
		tiles[1][6] = new TileView(null, initialX + xOffset*6, initialY + yOffset);
		tiles[2][6] = new TileView(null, initialX + xOffset*6, initialY + yOffset*2);
		tiles[3][6] = new TileView(null, initialX + xOffset*6, initialY + yOffset*3);
		
		return tiles;
		
	}
	
	private void addTiles(TileView[][] tiles) {
		
		for (int i=0; i<tiles.length; ++i) {
			for (int j=0; j<tiles[i].length; ++j) {
				if (tiles[i][j] != null) {
					getChildren().add(tiles[i][j]);
				}
			}
		}
		
	}

}
