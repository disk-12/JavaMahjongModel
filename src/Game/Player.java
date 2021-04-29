package Game;

import java.util.ArrayList;

import Game.Action.Action;
import Pile.Hand;
import Pile.Mountain;
import Pile.River;
import Tile.Tile;

public class Player {
	Hand hand;
	River river;

	public Player(Hand hand) {
		river = new River();
	}

	public void tsumo(Mountain mountain) {
		hand.add(mountain.pop());
	}

	public ArrayList<Action> playableAction(State state){
		ArrayList<Action> playable = new ArrayList<Action>();

		if(state.getPhase()==State.BEFORE_TSUMO) {
			//if(canFuro(state))
			return null;
		}
		if(state.getPhase()==State.AFTER_TSUMO) {
			//if(canHora(state))
			return null;
		}
		if(state.getPhase()==State.DISCARD_TILE) {
			for(Tile tile : hand.getDiscardable()) {
				playable.add(new Action(Action.DISCARD,tile));
			}
			return playable;
		}

		return null;
	}
}
