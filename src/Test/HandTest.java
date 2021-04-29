package Test;

import Game.Rule;
import Pile.Hand;
import Pile.Mountain;

public class HandTest {

	public static void main(String[] args) {
		Rule rule = new Rule(Rule.YONMA);
		Mountain mountain = new Mountain(rule);


		System.out.println("初期状態");
		System.out.println("山");
		System.out.println(mountain);

		Hand hand1 = new Hand(mountain.pop_multi(13));
		Hand hand2 = new Hand(mountain.pop_multi(13));
		Hand hand3 = new Hand(mountain.pop_multi(13));
		Hand hand4 = new Hand(mountain.pop_multi(13));

		System.out.println("４人に配牌");
		System.out.println("山");
		System.out.println(mountain);

		System.out.println("hand1");
		System.out.println(hand1);
		System.out.println("hand2");
		System.out.println(hand2);
		System.out.println("hand3");
		System.out.println(hand3);
		System.out.println("hand4");
		System.out.println(hand4);

	}

}
