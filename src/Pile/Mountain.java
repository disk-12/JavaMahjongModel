package Pile;

import java.util.ArrayList;
import java.util.Collections;

import Game.Rule;
import Tile.JiTile;
import Tile.SuTile;
import Tile.Tile;

public class Mountain {
	private ArrayList<Tile> tiles;

	public Mountain(Rule rule){
		//Arraylistを初期化してシャッフル
		tiles = new ArrayList<Tile>();
		for(int i=0;i<4;i++) {
			for(int j = 1; j<=9;j++) {
				if(rule.isYonma()||(i==1&&i==9))
					tiles.add(new SuTile(Tile.MANZU,j));
				tiles.add(new SuTile(Tile.PINZU,j));
				tiles.add(new SuTile(Tile.SOZU,j));
			}
			for(int j = 0; j<=6;j++) {
				tiles.add(new JiTile(Tile.JIHAI,j));
			}
		}

		Collections.shuffle(tiles);
	}

	public Tile pop() {
		if(tiles.size()==0)
			return null;

		Tile top = tiles.get(0);
		tiles.remove(0);
		return top;
	}

	public ArrayList<Tile> pop_multi(int n) {
		ArrayList<Tile> ret = new ArrayList<Tile>();

		for(int i =0;i<n;i++)
			ret.add(pop());


		return ret;
	}

	@Override
	public String toString() {
		String ret = "";
		//現在の山を出力
		for(Tile tile : tiles) {
			ret +=tile+" ";
		}

		return ret;
	}

}
