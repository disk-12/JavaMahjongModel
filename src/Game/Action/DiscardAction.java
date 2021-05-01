package Game.Action;

import Game.Player;
import Tile.Tile;

public class DiscardAction extends Action {
	//打牌用
	private Tile discarded;

	public DiscardAction(int type,Player player,Tile discarded){
		super(type,player);
		this.discarded = discarded;
	}

	public Tile getDiscarded() {
		return discarded;
	}
}
