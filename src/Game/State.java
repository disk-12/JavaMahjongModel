package Game;

import Tile.Tile;

public class State {
	public static final int
		TAKING_TSUMO=1,
		DISCARD=2,
		AFTER_DISCARD=3,
		END_GAME=4;

	private int phase;
	private Player turn_player;
	private Tile prev_tile;

	public State(int phase,Player turn_player,Tile prev_tile) {
		this.phase = phase;
		this.turn_player = turn_player;
		this.prev_tile = prev_tile;
	}
	public int change_phase(int next) {
		return phase = next;
	}

	public int getPhase() {
		return phase;
	}

	public Tile getPrev_tile() {
		return prev_tile;
	}

	public Player getTurn_player() {
		return turn_player;
	}

	public void change_turn(Player next_player) {
		turn_player=next_player;
	}
	@Override
	public String toString() {
		String ret ="";
		switch(phase) {
		case TAKING_TSUMO:
			ret += "TAKING_TSUMO";
			break;
		case DISCARD:
			ret += "DISCARD";
			break;
		case AFTER_DISCARD:
			ret += "AFTER_DISCARD";
			break;
		case END_GAME:
			ret += "END_GAME";
			break;
		}
		return ret;
	}
}
