package com.kingsandthings.model.board;

import com.kingsandthings.model.enums.Terrain;

public class Board {
	
	private Tile[][] tiles;
	
	public Board(int numPlayers) {
		
		// TODO - change depending on numPlayers
		int boardSize = 10;
		
		tiles = generateTiles(boardSize);
		
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
		
		return tiles;
		
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	
	
	//private List<Tile> getNeighbours(int x, int y);
	//private List<Tile> getTiles();
	//private Tile getTile(int x, int y);
	
	//public void setTile(Tile tile, int x, int y);
	
}
