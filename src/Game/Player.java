package Game;

import java.util.ArrayList;
import java.util.Arrays;

import Game.Action.Action;
import Game.Action.DiscardAction;
import Game.Action.FuroAction;
import Pile.Furo;
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
		Tile discarded = state.getPrev_tile();
		Player turn_player = state.getTurn_player();
		int phase = state.getPhase();

		if (phase == State.AFTER_TSUMO) {
			if(this == turn_player) {
				for (Tile tile : hand.getDiscardable()) {
					playable.add(new DiscardAction(Action.DISCARD, this, tile));
				}
				if(canHora(state)) {
					playable.add(new Action(Action.TSUMO_HORA, this));
				}
			}
			else
				playable.add(new Action(Action.IGNORE,this));
			return playable;
		}
		if (phase == State.DISCARD_TILE) {
			playable.add(new Action(Action.IGNORE, this));
			if (this != turn_player) {
				if (canRon(state)) {
					playable.add(new Action(Action.RON, this));
				}
				if (canChi(discarded)) {
					ArrayList<Furo> chis = ChiList(discarded,turn_player);
					for(Furo chi:chis)
						playable.add(new FuroAction(Action.CHI, this,chi));
				}
				if (canPon(discarded)) {
					Furo pon = new Furo(Arrays.asList(new Tile[] { discarded, discarded, discarded }), Furo.PON,
							direction(turn_player));
					playable.add(new FuroAction(Action.PON, this, pon));
				}
				if (canDaiminKan(discarded)) {
					playable.add(new Action(Action.KAN, this));
				}
			}
			return playable;
		}

		return null;
	}


	private boolean canHora(State state) {
		return YakuAPI.toScore(hand,state.getPrev_tile()) != -1;
	}

	private int direction(Player turn_player) {
		if (turn_player == lower_player)
			return Furo.LOWER;
		if (turn_player == upper_player)
			return Furo.UPPER;
		if (turn_player == opposite_player)
			return Furo.OPPSITE;
		return -1;
	}

	private boolean canAnKan(Tile discarded) {
		return hand.in(3, discarded);
	}

	private boolean canShominKan(Tile discarded, Player turn_player) {
		return hand.hadPoned(discarded);
	}

	private boolean canDaiminKan(Tile discarded) {
		return hand.in(3, discarded);
	}

	private boolean canPon(Tile discarded) {
		return hand.in(2, discarded);
	}

	private boolean canChi(Tile discarded) {

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

	private ArrayList<Furo> ChiList(Tile discarded,Player turn_player) {
		Tile prev = discarded.prev();
		Tile next = discarded.next();
		Tile prevprev = prev == null ? null : prev.prev();
		Tile nextnext = next == null ? null : next.next();
		ArrayList <Furo> chis = new ArrayList<Furo>();
		if (prevprev != null && prev != null && hand.in(discarded.prev()) && hand.in(discarded.prev().prev()))
			chis.add(new Furo(Arrays.asList(new Tile[] {prevprev,prev,discarded}),Furo.CHI,direction(turn_player)));
		if (next != null && prev != null && hand.in(discarded.prev()) && hand.in(discarded.next()))
			chis.add(new Furo(Arrays.asList(new Tile[] {prev,discarded,next}),Furo.CHI,direction(turn_player)));
		if (nextnext != null && next != null && hand.in(discarded.prev()) && hand.in(discarded.prev().prev()))
			chis.add(new Furo(Arrays.asList(new Tile[] {discarded,next,nextnext}),Furo.CHI,direction(turn_player)));
		return chis;
	}

	private boolean canRon(State state) {
		return YakuAPI.toScore(hand, state.getPrev_tile()) != -1;
	}

}
