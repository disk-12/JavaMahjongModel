package Game;

public class Contoroller {
	public void init() {

	}
	public void play() {
		State state = new State();
		State next = null;
		while(true) {
			if(state.getPhase() == State.BEFORE_TSUMO) {

			}
			else if(state.getPhase() == State.TAKING_TSUMO) {

			}
			else if(state.getPhase() == State.BEFORE_TSUMO) {

			}
			else if(state.getPhase() == State.DISCARD_TILE) {

			}
			else if(state.getPhase() == State.END_GAME) {
				break;
			}
			else {
				System.err.println("ERRRRRRRRRRROR");
				break;
			}
			state = next;
		}
	}
}
