package Game.Action;

import Tile.Tile;

public class Action {
	public static final int
			PON = 0,
			CHI = 1,
			KAN = 2,
			TSUMO = 3,
			RON = 4,
			TSUMO_HORA=5,
			DISCARD = 6,
			IGNORE = 7;

	private int type;
	private Tile discarded;

	public int getType() {
		return type;
	}
	public Tile getDiscarded() {
		return discarded;
	}

	private Action(int type){
		this.type = type;
	}

	public Action(int type,Tile discarded){
		this(type);
		this.discarded = discarded;
	}
}
