package Test;

import Game.Rule;
import Pile.Mountain;
import Pile.River;

public class KawaTest {

	public static void main(String[] args) {
		Rule rule = new Rule(Rule.YONMA);
		Mountain mountain = new Mountain(rule);
		River river = new River();
		

		System.out.println("初期状態");
		System.out.println("山");
		System.out.println(mountain);
		System.out.println("河");
		System.out.println(river);

		
		for(int i=0;i<15;i++)
			river.add(mountain.pop());

		System.out.println("15枚捨てた");
		System.out.println("山");
		System.out.println(mountain);
		System.out.println("河");
		System.out.println(river);
	}

}
