package Game;

import Tile.Tile;

public class State {
	public static final int
		BEFORE_TSUMO=0,
		TAKING_TSUMO=1,
		AFTER_TSUMO=2,
		DISCARD_TILE=3,
		END_GAME=4;
	private int phase;
	private Player turn_player;
	private Tile prev_discarded;

	public int change_phase(int next) {
		return phase = next;
	}

	public int getPhase() {
		return phase;
	}

	public Tile getPrev_discarded() {
		return prev_discarded;
	}

	public Player getTurn_player() {
		return turn_player;
	}

	public void change_turn(Player next_player) {
		turn_player=next_player;
	}
}
