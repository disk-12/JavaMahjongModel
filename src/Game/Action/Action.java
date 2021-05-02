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
	private Player player;

	public int getType() {
		return type;
	}

	public Action(int type,Player player){
		this.type = type;
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}

	public boolean isEnd() {
		return type == RON||type == TSUMO_HORA||type == NINE_RYUKYOKU;
	}

	public boolean isFuro() {
		// TODO 自動生成されたメソッド・スタブ
		return type == PON||type == CHI||type == KAN;
	}
	static private final String[] name = {"RON","99","KAN","PON","CHI","TSUMO","TSUMO_AGARI","DISCARD","IGNORE"};
	@Override
	public String toString() {

		// TODO 自動生成されたメソッド・スタブ
		return name[type];
	}



}
