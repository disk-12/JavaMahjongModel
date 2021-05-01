package Pile;

import java.util.ArrayList;

import Tile.Tile;

public class Hand {
	private ArrayList<Tile> tiles;
	private ArrayList<Furo> furos;

	public Hand(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		furos = new ArrayList<Furo>();
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

	public boolean in(int n, Tile key) {
		//tilesにkeyがn個以上あるか判定
		int c = 0;
		for(Tile tile:tiles) {
			if(tile.equals(key)) {
				c++;
			}
		}
		return c>=n;
	}

	public boolean in(Tile key) {
		//tilesにkeyがあるか判定
		int c = 0;
		for(Tile tile:tiles) {
			if(tile.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean hadPoned(Tile key) {
		//furo_tilesにkeyのポンがあるか
		for(Furo furo: furos) {
			if(furo.getType()==Furo.PON&&furo.getTile(0).equals(key)) {
				return true;
			}
		}
		return false;
	}


}
