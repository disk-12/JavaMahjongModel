package Game.Action;

import Game.Player;

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

	public int getType() {
		return type;
	}

	public Action(int type,Player player){
		this.type = type;
	}



}
