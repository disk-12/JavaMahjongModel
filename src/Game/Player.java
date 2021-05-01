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
	Player upper_player, lower_player, opposite_player; //上家，下家，対面

	public Player(ArrayList<Tile> initial_hand) {
		river = new River();
		hand = new Hand(initial_hand);
	}

	public void tsumo(Mountain mountain) {
		hand.add(mountain.pop());
	}

	public ArrayList<Action> playableAction(State state) {
		ArrayList<Action> playable = new ArrayList<Action>();

		if (state.getPhase() == State.BEFORE_TSUMO) {
			playable.add(new Action(Action.IGNORE, this));
			if (canRon(state)) {
				playable.add(new Action(Action.RON, this));
			}
			if (canChi(state)) {
				playable.add(new Action(Action.CHI, this));
			}
			if (canPon(state)) {
				playable.add(new Action(Action.PON, this));
			}
			if (canKan(state)) {
				playable.add(new Action(Action.KAN, this));
			}
			return playable;
		}

		if (state.getPhase() == State.AFTER_TSUMO) {
			//if(canHora(state))
			return null;
		}
		if (state.getPhase() == State.DISCARD_TILE) {
			for (Tile tile : hand.getDiscardable()) {
				playable.add(new Action(Action.DISCARD, this, tile));
			}
			return playable;
		}

		return null;
	}

	private boolean canKan(State state) {
		return hand.in(3, state.getPrev_discarded()) || //大明槓
				(hand.hadPoned(state.getPrev_discarded()) && state.getTurn_player() == this);//小明槓
	}

	private boolean canPon(State state) {
		return hand.in(2, state.getPrev_discarded());
	}

	private boolean canChi(State state) {
		Tile discarded = state.getPrev_discarded();

		if (discarded.is3genpai() || discarded.isFanpai())
			return false;

		Tile prev = discarded.prev();
		Tile next = discarded.next();
		Tile prevprev = prev == null ? null : prev.prev();
		Tile nextnext = next == null ? null : next.next();

		if (prevprev != null && prev != null && hand.in(discarded.prev()) && hand.in(discarded.prev().prev()))
			return true;
		if (next != null && prev != null && hand.in(discarded.prev()) && hand.in(discarded.next()))
			return true;
		if (nextnext != null && next != null && hand.in(discarded.prev()) && hand.in(discarded.prev().prev()))
			return true;

		return false;
	}

	private boolean canRon(State state) {
		return YakuAPI.toScore(hand, state.getPrev_discarded()) != -1;
	}

}
