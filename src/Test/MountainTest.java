package Test;

import Game.Rule;
import Pile.Mountain;

public class MountainTest {

	public static void main(String[] args) {
		Rule rule = new Rule(Rule.YONMA);
		Mountain mt = new Mountain(rule);
		System.out.println(mt);
	}

}
