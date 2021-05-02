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

	public Tile tsumo(Mountain mountain) {
		Tile poped;
		hand.tsumo(poped = mountain.pop());
		return poped;
	}

	public ArrayList<Action> playableAction(State state) {
		ArrayList<Action> playable = new ArrayList<Action>();
		Player turn_player = state.getTurn_player();
		int phase = state.getPhase();

		if (phase == State.DISCARD) {
			if (this == turn_player) {
				for (Tile tile : hand.getDiscardable()) {
					playable.add(new DiscardAction(Action.DISCARD, this, tile));
				}
				if (canHora(state)) {
					playable.add(new Action(Action.TSUMO_HORA, this));
				}
			} else
				playable.add(new Action(Action.IGNORE, this));
			return playable;
		}
		if (phase == State.AFTER_DISCARD) {
			Tile discarded = state.getPrev_tile();
			playable.add(new Action(Action.IGNORE, this));
			if (this != turn_player) {
				if (canRon(state)) {
					playable.add(new Action(Action.RON, this));
				}
				if (canChi(discarded)) {
					ArrayList<Furo> chis = ChiList(discarded, turn_player);
					for (Furo chi : chis)
						playable.add(new FuroAction(Action.CHI, this, chi));
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
		} else
			playable.add(new Action(Action.IGNORE, this));
		return playable;
	}

	private boolean canHora(State state) {
		return YakuAPI.toScore(hand, state.getPrev_tile()) != -1;
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

		if (prevprev != null && prev != null && hand.in(prev) && hand.in(prevprev))
			return true;
		if (next != null && prev != null && hand.in(prev) && hand.in(next))
			return true;
		if (nextnext != null && next != null && hand.in(next) && hand.in(nextnext))
			return true;

		return false;
	}

	private ArrayList<Furo> ChiList(Tile discarded, Player turn_player) {
		Tile prev = discarded.prev();
		Tile next = discarded.next();
		Tile prevprev = prev == null ? null : prev.prev();
		Tile nextnext = next == null ? null : next.next();
		ArrayList<Furo> chis = new ArrayList<Furo>();
		if (prevprev != null && prev != null && hand.in(prev) && hand.in(prevprev))
			chis.add(new Furo(Arrays.asList(new Tile[] { prevprev, prev, discarded }), Furo.CHI,
					direction(turn_player)));
		if (next != null && prev != null && hand.in(prev) && hand.in(next))
			chis.add(new Furo(Arrays.asList(new Tile[] { prev, discarded, next }), Furo.CHI, direction(turn_player)));
		if (nextnext != null && next != null && hand.in(next) && hand.in(nextnext))
			chis.add(new Furo(Arrays.asList(new Tile[] { discarded, next, nextnext }), Furo.CHI,
					direction(turn_player)));
		return chis;
	}

	private boolean canRon(State state) {
		return YakuAPI.toScore(hand, state.getPrev_tile()) != -1;
	}

	public void discard(Tile discarded) {
		hand.discard(discarded);
		hand.pushTsumo();
		river.add(discarded);
	}

	public Player getLower_player() {
		return lower_player;
	}

	public void setOthers(Player upper_player, Player lower_player, Player opposite_player) {
		this.upper_player = upper_player;
		this.lower_player = lower_player;
		this.opposite_player = opposite_player;
	}

	public void setOthers(Player upper_player, Player lower_player) {
		setOthers(upper_player, lower_player, null);
	}

	@Override
	public String toString() {
		String ret ="";
		ret += "HAND:" + hand + "\r\n";
		ret += "RIVER:\r\n" + Contoroller.indent(river.toString());

		return ret;
	}
}
