package Pile;

import java.util.ArrayList;

import Tile.Tile;

public class River {
	private ArrayList<Tile> tiles;

	public River() {
		tiles = new ArrayList<Tile>();
	}

	public void add(Tile throwed) {
		tiles.add(throwed);
	}

	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < tiles.size() ; i++) {
			if(i%6==0 && i!=0)
				ret+="\r\n";
			ret+=tiles.get(i)+" ";
			//ret+=format(tiles.get(i)+" ",4);
		}
		return ret;
	}
}
