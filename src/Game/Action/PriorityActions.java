package Game.Action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityActions {
	PriorityQueue<Action> selected;

	public PriorityActions(ArrayList<Action> selected) {
		this();
		for(Action action :selected) {
			this.selected.add(action);
		}
	}

	public PriorityActions() {
		this.selected = new PriorityQueue<Action>(1,new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Action a1 = (Action) o1;
				Action a2 = (Action) o2;
				return a1.getType() < a2.getType()?-1:1;
			}
		});
	}

	public Action pop() {
		return selected.poll();
	}

	public void push(Action action) {
		selected.add(action);
	}
}
