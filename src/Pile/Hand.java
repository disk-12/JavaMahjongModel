package Pile;

import java.util.ArrayList;

import Tile.Tile;

public class Hand {
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> furo_tiles;

	public Hand(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		furo_tiles = new ArrayList<Tile>();
	}

	public void add(Tile throwed) {
		tiles.add(throwed);
	}

	public void remove(int i) {
		tiles.remove(i);
	}

	public void remove(Tile tile) {
		tiles.remove(tile);
	}


	@Override
	public String toString() {
		String ret = "";
		for(Tile tile:tiles) {
			ret += tile.toString()+" ";
		}
		return ret;
	}

	public boolean canFuro(Tile prev_throwed) {
		//副露できるか
		return canChi()||canPon();
	}

	public boolean canChi() {
		//チーできる？
		return false;
	}

	public boolean canPon() {
		//ポンできる？
		return false;
	}

	public void Chi() {

	}

	public void Pon() {

	}

	public ArrayList<Tile> getDiscardable() {
		return tiles;
	}


}
