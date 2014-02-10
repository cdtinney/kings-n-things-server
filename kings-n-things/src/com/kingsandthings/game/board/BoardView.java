package com.kingsandthings.game.board;

import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import com.kingsandthings.game.InitializableView;
import com.kingsandthings.game.events.PropertyChangeDispatcher;
import com.kingsandthings.model.board.Tile;

public class BoardView extends Pane implements InitializableView {
	
	private static Logger LOGGER = Logger.getLogger(BoardView.class.getName());
	
	private TileView[][] tiles = new TileView[10][10];
	
	private static Label instructions;
	
	@Override
	public void initialize() {
		
		getStyleClass().addAll("pane", "board");
		
		int initialX = 70;
		int initialY = 225;
		
		int xOffset = 79;
		int yOffset = 91;
		
		int columnOffset = 45;
		
		tiles = generateTiles(initialX, initialY, xOffset, yOffset, columnOffset, 10);
		
		addTilesToView(tiles);
		addInstructionText();
		
	}

	public static void setInstructionText(String message) {
		
		if (instructions == null) {
			return;
		}
		
		instructions.setText(message == null? "" : message);		
	}
	
	private void addInstructionText() {
		instructions = new Label("instructions go here");
		instructions.getStyleClass().add("instructionsText");
		instructions.setPrefWidth(getBoundsInParent().getWidth() + 75);
		getChildren().add(instructions);
	}
	
	public TileView[][] getTiles() {
		return tiles;
	}
	
	public void setTileImages(Tile[][] modelTiles) {
		
		for (int i=0; i<modelTiles.length; ++i) {
			for (int j=0; j<modelTiles[i].length; ++j) {
				
				Tile tile = modelTiles[i][j];
				
				try {
					
					final TileView view = tiles[i][j];
					
					if (tile == null || view == null) {
						continue;
					}
					
					view.setTile(tile);
					
					// TODO - change this, too many listeners (or move to TileView)
					PropertyChangeDispatcher.getInstance().addListener(Tile.class, "owner", this, view, TileView.class, "handleTileOwnerChanged");
					PropertyChangeDispatcher.getInstance().addListener(Tile.class, "fort", this, view, TileView.class, "handleTileFortChanged");
					
				} catch (IndexOutOfBoundsException e) {
					LOGGER.warning("Model and view tile array size mismatch - " + e.getMessage());
				}
				
			}
		}
		
	}
	
	@SuppressWarnings("unused")
	private void handleTileFortChanged(TileView tileView) {
		tileView.updateFortView();		
	}

	@SuppressWarnings("unused")
	private void handleTileOwnerChanged(TileView tileView) {
		
		// Update the control marker
		tileView.updateControlMarkerView();
		
		// Set the image of the tile
		tileView.setImage(tileView.getTile().getImage());
		
	}
	
	private void addTilesToView(TileView[][] tiles) {
		
		for (TileView[] row : tiles) {
			for (TileView tile : row) {
				
				if (tile != null) {
					getChildren().add(tile);
				}
				
			}
		}
		
	}
	
	// TODO - move this into factory(?) class
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
	
}
