package Game.Action;

import Game.Player;
import Tile.Tile;

public class Action {
	public static final int
			RON = 0,			//ロン
			NINE_RYUKYOKU = 1,	//９種９牌
			KAN = 2,			//カン
			PON = 3,			//ポン
			CHI = 4,			//チー
			TSUMO = 5,			//ツモ
			TSUMO_HORA = 6,		//ツモ和了
			DISCARD = 7,		//打牌
			IGNORE = 8;			//無視（何もしない）

	//アクションの種類(ロン，カンなど)
	private int type;
	//打牌用
	private Tile discarded;

	public int getType() {
		return type;
	}
	public Tile getDiscarded() {
		return discarded;
	}

	public Action(int type,Player player){
		this.type = type;
	}

	public Action(int type,Player player,Tile discarded){
		this(type,player);
		this.discarded = discarded;
	}

}
