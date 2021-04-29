package Game;

public class State {
	public static final int
		BEFORE_TSUMO=0,
		TAKING_TSUMO=1,
		AFTER_TSUMO=2,
		DISCARD_TILE=3,
		END_GAME=4;
	private int phase;

	public int transition(int next) {
		return phase = next;
	}

	public int getPhase() {
		return phase;
	}

}
