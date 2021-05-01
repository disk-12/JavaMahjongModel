package Game.Action;

import Game.Player;
import Pile.Furo;

public class FuroAction extends Action{
	private Furo furo;
	public FuroAction(int type,Player player,Furo furo){
		super(type,player);
		this.furo = furo;
	}
}
