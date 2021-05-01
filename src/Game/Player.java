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
	Player upper_player,lower_player,opposite_player;		//上家，下家，対面

	public Player(ArrayList<Tile> initial_hand) {
		river = new River();
		hand = new Hand(initial_hand);
	}

	public void tsumo(Mountain mountain) {
		hand.add(mountain.pop());
	}

	public ArrayList<Action> playableAction(State state){
		ArrayList<Action> playable = new ArrayList<Action>();

		if(state.getPhase()==State.BEFORE_TSUMO) {
			playable.add(new Action(Action.IGNORE,this));
			if(canRon(state)) {
				playable.add(new Action(Action.RON,this));
			}
			if(canChi(state)) {
				playable.add(new Action(Action.CHI,this));
			}
			if(canPon(state)) {
				playable.add(new Action(Action.PON,this));
			}
			if(canKan(state)) {
				playable.add(new Action(Action.KAN,this));
			}
			return playable;
		}

		if(state.getPhase()==State.AFTER_TSUMO) {
			//if(canHora(state))
			return null;
		}
		if(state.getPhase()==State.DISCARD_TILE) {
			for(Tile tile : hand.getDiscardable()) {
				playable.add(new Action(Action.DISCARD,this,tile));
			}
			return playable;
		}

		return null;
	}

	private boolean canKan(State state) {
		return hand.in(3,state.getPrev_discarded())|| //大明槓 or 暗槓
				(hand.hadPoned(state.getPrev_discarded())&&state.getTurn_player()==this);//小明槓
	}

	private boolean canPon(State state) {
		return hand.in(2,state.getPrev_discarded());
	}

	private boolean canChi(State state) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	private boolean canRon(State state) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	private boolean canFuro(State state) {
		return false;
	}
}
