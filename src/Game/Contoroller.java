package Game;

import java.util.ArrayList;

import Game.Action.Action;
import Pile.Mountain;

public class Contoroller {
	private ArrayList<Player> players;
	private Rule rule;
	private Mountain mountain;
	private State state;

	public Contoroller(Rule rule) {
		this.rule = rule;
		this.players = new ArrayList<Player>();
		this.mountain = new Mountain(rule);

		for(int i = 0;i < (rule.isSanma()?3:4);i++)
			players.add(new Player(mountain.pop_multi(13)));
	}

	public void doAction(ArrayList<Action> selected) {

		State next_state = null;
		if(state.getPhase() == State.BEFORE_TSUMO) {

		}
		else if(state.getPhase() == State.TAKING_TSUMO) {

		}
		else if(state.getPhase() == State.BEFORE_TSUMO) {

		}
		else if(state.getPhase() == State.DISCARD_TILE) {

		}
		else if(state.getPhase() == State.END_GAME) {

		}
		else {
			System.err.println("ERRRRRRRRRRROR");
		}
		state = next_state;
	}


	public boolean isEnd() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public ArrayList<ArrayList<Action>> getActions() {
		//各プレーヤの取れる行動をリスト化
		ArrayList<ArrayList<Action>> playable_actions = new ArrayList<ArrayList<Action>>();

		for(Player player : players) {
			playable_actions.add(player.playableAction(state));
		}

		return playable_actions;
	}

}
