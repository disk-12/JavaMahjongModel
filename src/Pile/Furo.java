package Pile;

import java.util.List;

import Tile.Tile;

public class Furo {

	static public final int PON = 0,
			CHI = 1,
			MINKAN = 2,
			ANKAN = 3,
			UPPER = 4,
			LOWER = 5,
			OPPSITE = 6;

	protected List<Tile> tiles;
	protected int type;
	protected int direction;


	public Furo(List<Tile> tiles,int type,int direction) {
		this.type = type;
		this.tiles = tiles;
		this.direction = direction;
	}


	public int getType() {
		return type;
	}

	public int getDirection() {
		return direction;
	}

	public Tile getTile(int index) {
		return tiles.get(index);
	}
}
