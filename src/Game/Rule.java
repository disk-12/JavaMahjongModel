package Game;

public class Rule {
	public final static int SANMA = 0;
	public final static int YONMA = 1;

	private int ma;

	public Rule(int ma){
		this.ma = ma;
	}

	public boolean isSanma() {
		return ma ==SANMA;
	}

	public boolean isYonma() {
		return ma ==YONMA;
	}
}
