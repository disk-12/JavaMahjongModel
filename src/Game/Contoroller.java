package Game;

import java.util.ArrayList;

import Game.Action.Action;
import Game.Action.DiscardAction;
import Game.Action.PriorityActions;
import Pile.Mountain;
import Tile.Tile;

public class Contoroller {
	private ArrayList<Player> players;
	private Rule rule;
	private Mountain mountain;
	private State state;
	private boolean finished;

	public Contoroller(Rule rule) {
		this.rule = rule;
		this.players = new ArrayList<Player>();
		this.mountain = new Mountain(rule);
		this.finished = false;
		for(int i = 0;i < (rule.isSanma()?3:4);i++)
			players.add(new Player(mountain.pop_multi(13)));
		for(int i = 0;i < (rule.isSanma()?3:4);i++) {
			if(rule.isSanma())
				players.get(i).setOthers(players.get((i-1+3)%3),players.get((i+1+3)%3));
			else
				players.get(i).setOthers(players.get((i-1+4)%4),players.get((i+1+4)%4),players.get((i+2+4)%4));
		}

		this.state = new State(State.TAKING_TSUMO,players.get(0),null);
	}

	public void doAction(ArrayList<Action> selected) {
		State next_state = null;
		Tile discarded = state.getPrev_tile();
		Player turn_player = state.getTurn_player();
		PriorityActions priority_actions = new PriorityActions(selected);
		Action prior_action = priority_actions.pop();
		int phase = state.getPhase();
		if(phase == State.TAKING_TSUMO) {
			Tile tsumo = turn_player.tsumo(mountain);
			next_state = new State(State.DISCARD,turn_player,tsumo);
		}
		else if(phase== State.DISCARD) {
			if(prior_action.getType()==Action.DISCARD) {
				DiscardAction discard = (DiscardAction) prior_action;
				turn_player.discard(discard.getDiscarded());
				next_state = new State(State.AFTER_DISCARD,turn_player,discard.getDiscarded());
			}
		}
		else if(phase == State.AFTER_DISCARD) {
			if(prior_action.isEnd()) {
				next_state = new State(State.END_GAME,prior_action.getPlayer(),null);
			}
			else if(prior_action.isFuro()) {
				next_state = new State(State.AFTER_DISCARD,prior_action.getPlayer(),state.getPrev_tile());
			}
			else{
				if(mountain.length()<=14)
					//流局
					next_state = new State(State.END_GAME,null,null);
				else
					next_state = new State(State.TAKING_TSUMO,nextPlayer(turn_player),null);
			}
		}
		else if(phase == State.END_GAME) {
			finished=true;
		}
		else {
			System.err.println("ERRRRRRRRRRROR");
		}
		state = next_state;
	}


	private Player nextPlayer(Player turn_player) {
		return turn_player.getLower_player();
	}

	public boolean isEnd() {
		// TODO 自動生成されたメソッド・スタブ
		return finished;
	}

	public ArrayList<ArrayList<Action>> getActions() {
		//各プレーヤの取れる行動をリスト化
		ArrayList<ArrayList<Action>> playable_actions = new ArrayList<ArrayList<Action>>();

		for(Player player : players) {
			playable_actions.add(player.playableAction(state));
		}

		return playable_actions;
	}

	@Override
	public String toString() {
		String ret = "";
		ret +="STATE:"+state+"\r\n";
		for(int i =0;i<players.size();i++)
			ret +="Player"+(i+1)+":\r\n"+indent(players.get(i).toString())+"\r\n";
		return ret;
	}
	static public String indent(String str) {
		return "\t"+str.replaceAll("\n", "\n\t");
	}
}
