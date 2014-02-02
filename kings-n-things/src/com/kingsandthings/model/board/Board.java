package com.kingsandthings.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.kingsandthings.model.Player;
import com.kingsandthings.model.enums.Terrain;

public class Board {
	
	private static Logger LOGGER = Logger.getLogger(Board.class.getName());
	
	private Tile[][] tiles;
	
	public Board(int numPlayers) {
		tiles = generateTiles(10);
	}
	
	public void toggleTileControl(Tile tile, Player player) {
		
		if (player == null) {
			return;
		}
		
		if (tile.getOwner() == null) {
			setTileControl(tile, player);
		} else {
			clearTileControl(tile, player);
		}
		
	}
	
	public void clearTileControl(Tile tile, Player player) {
		
		if (tile.getOwner() != player) {
			LOGGER.info("Cannot remove control marker from a tile the player does not own.");
			return;
		}
		
		if (player.getStartingTile() == tile) {
			LOGGER.info("Cannot remove control marker from starting tile.");
			return;
		}
		
		player.removeControlledTile(tile);
		
	}
	
	public void setTileControl(Tile tile, Player player) {
		
		if (!validInitialControlTile(tile, player)) {
			LOGGER.info("Tile is not valid for initial control marker placement.");
			return;
		}
		
		player.addControlledTile(tile);
		
	}
	
	/*
	 * Determines if a player can place an initial control marker on a tile.
	 * 
	 * Restrictions:
	 * - the tile must be adjacent to a previous tile owned by the player
	 * - the tile may not be adjacent to a tile owned by another player
	 */
	public boolean validInitialControlTile(Tile tile, Player player) { 
		
		List<Tile> neighbours = getNeighbours(tiles, tile);
		
		boolean playerNeighbour = false;
		boolean enemyNeighbour = false;
		
		for (Tile neighbour : neighbours) {
			
			if (neighbour.getOwner() == player) {
				playerNeighbour = true;
			} else if (neighbour.getOwner() != null) {
				enemyNeighbour = true;
			}
			
		}
		
		return playerNeighbour && !enemyNeighbour;
	}
	
	public void setStartingTile(Player player, int position) {
		
		switch(position) {
		
			case 1:
				player.setStartingTile(tiles[0][5]);
				break;
		
			case 2:
				player.setStartingTile(tiles[4][5]);
				break;
		
			case 3:
				player.setStartingTile(tiles[4][1]);
				break;
		
			case 4:
				player.setStartingTile(tiles[0][1]);
				break;
				
		}
		
	}
	
	private List<Tile> getNeighbours(Tile[][] tiles, Tile tile) {
		
		List<Tile> neighbours = new ArrayList<Tile>();
		
		int r = -1;
		int c = -1;
		
		for (int i=0; i<tiles.length; ++i) {
			for (int j=0; j<tiles[i].length; ++j) {
				
				if (tiles[i][j] != null && tiles[i][j] == tile) {
					r = i;
					c = j;
				}
				
			}
		}
		
		if (r == -1 || c == -1) {
			return null;
		}
		
		// Right above
		if (c < 3) {
			Tile rightAbove = tiles[r][c+1];
			neighbours.add(rightAbove);
			
		} else if (r > 0 && c >= 3) {
			Tile rightAbove = tiles[r-1][c+1];
			neighbours.add(rightAbove);
		} 
		
		// Right below
		if (c < 6) {
			int rBelowOffset = c < 3 ? 0 : 1;
			Tile rightBelow = tiles[r+1-rBelowOffset][c+1];
			neighbours.add(rightBelow);
		}
		
		// Columns from left -> center
		if (c > 0 && c <= 3) {
			
			if (r > 0) {
				Tile leftAbove = tiles[r-1][c-1];
				neighbours.add(leftAbove);
			}
			
			Tile leftBelow = tiles[r][c-1];
			neighbours.add(leftBelow);
		}
		
		// Columns from center+1 -> right
		if (c > 3) {
			
			Tile leftAbove = tiles[r][c-1];
			Tile leftBelow = tiles[r+1][c-1];
			
			neighbours.add(leftAbove);
			neighbours.add(leftBelow);
		}
		
		if (r < 6) {
			Tile below = tiles[r+1][c];
			neighbours.add(below);
		}
		
		if (r > 0) {
			Tile above = tiles[r-1][c];
			neighbours.add(above);
		}
	
		// TODO - "Fix" for bad grid coordinate system and laziness w.r.t conditions
		neighbours.removeAll(Collections.singleton(null));
		
		return neighbours;
		
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	private Tile[][] generateTiles(int size) {

		Tile[][] tiles = new Tile[size][size];
		
		// column 0
		tiles[0][0] = new Tile(Terrain.DESERT);		
		tiles[1][0] = new Tile(Terrain.FROZEN_WASTE);
		tiles[2][0] = new Tile(Terrain.FOREST);
		tiles[3][0] = new Tile(Terrain.MOUNTAIN);
		
		// column 1
		tiles[0][1] = new Tile(Terrain.SWAMP);
		tiles[1][1] = new Tile(Terrain.JUNGLE);
		tiles[2][1] = new Tile(Terrain.MOUNTAIN);
		tiles[3][1] = new Tile(Terrain.PLAINS);
		tiles[4][1] = new Tile(Terrain.JUNGLE);
		
		// column 3
		tiles[0][2] = new Tile(Terrain.MOUNTAIN);
		tiles[1][2] = new Tile(Terrain.PLAINS);
		tiles[2][2] = new Tile(Terrain.SWAMP);
		tiles[3][2] = new Tile(Terrain.FOREST);
		tiles[4][2] = new Tile(Terrain.DESERT);
		tiles[5][2] = new Tile(Terrain.PLAINS);
		
		// column 4 (center)
		tiles[0][3] = new Tile(Terrain.JUNGLE);
		tiles[1][3] = new Tile(Terrain.FROZEN_WASTE);
		tiles[2][3] = new Tile(Terrain.FOREST);
		tiles[3][3] = new Tile(Terrain.FROZEN_WASTE);
		tiles[4][3] = new Tile(Terrain.SEA);
		tiles[5][3] = new Tile(Terrain.FOREST);
		tiles[6][3] = new Tile(Terrain.DESERT);
		
		// column 5
		tiles[0][4] = new Tile(Terrain.SWAMP);
		tiles[1][4] = new Tile(Terrain.MOUNTAIN);
		tiles[2][4] = new Tile(Terrain.JUNGLE);
		tiles[3][4] = new Tile(Terrain.PLAINS);
		tiles[4][4] = new Tile(Terrain.SWAMP);
		tiles[5][4] = new Tile(Terrain.MOUNTAIN);

		// column 6
		tiles[0][5] = new Tile(Terrain.DESERT);
		tiles[1][5] = new Tile(Terrain.FROZEN_WASTE);
		tiles[2][5] = new Tile(Terrain.SWAMP);
		tiles[3][5] = new Tile(Terrain.DESERT);
		tiles[4][5] = new Tile(Terrain.JUNGLE);
		
		// column 7
		tiles[0][6] = new Tile(Terrain.FOREST);
		tiles[1][6] = new Tile(Terrain.PLAINS);
		tiles[2][6] = new Tile(Terrain.FOREST);
		tiles[3][6] = new Tile(Terrain.FROZEN_WASTE);
		
		for (Tile[] row : tiles) {
			for (Tile tile : row) {
				
				if (tile != null) {
					tile.setNeighbours(getNeighbours(tiles, tile));
				}
				
			}
		}
		
		return tiles;
		
	}
	
}
