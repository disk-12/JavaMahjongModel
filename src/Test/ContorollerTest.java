package Test;

import java.util.ArrayList;

import Game.Contoroller;
import Game.Rule;
import Game.Action.Action;

public class ContorollerTest {


	public static void main(String[] args) {
		Rule rule  = new Rule(Rule.YONMA);
		Contoroller controller = new Contoroller(rule);

		while(!controller.isEnd()) {
			ArrayList<ArrayList<Action>> player_actions = controller.getActions();
			ArrayList<Action> selected = selectAction(player_actions);
			controller.doAction(selected);
			System.out.println(controller);
		}

	}

	private static ArrayList<Action> selectAction(ArrayList<ArrayList<Action>> player_actions) {
		ArrayList<Action> selected = new ArrayList<Action>();
		for(ArrayList<Action> player : player_actions) {
			selected.add(player.get(0));
		}
		return selected;
	}
}
