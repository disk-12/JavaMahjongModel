package Tile;

public abstract  class Tile {
	public final static int MANZU = 7;
	public final static int PINZU = 8;
	public final static int SOZU = 9;
	public final static int JIHAI = 10;

	public final static int TON = 0;
	public final static int NAN = 1;
	public final static int SYA = 2;
	public final static int PEI = 3;

	public final static int HAKU = 4;
	public final static int HATSU = 5;
	public final static int CHUN = 6;

	protected int number;
	protected int type;

	Tile(int type,int number){
		this.number = number;
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public int getType() {
		return type;
	}

	public abstract Tile next();
	public abstract Tile prev();

	public boolean equals(Tile t) {
		return this.number == t.number && this.type ==t.type;
	}
	public boolean isFanpai() {
		return getType()==JIHAI && TON <= getNumber() && getNumber() <= PEI;
	}

	public boolean is3genpai() {
		return getType()==JIHAI && HAKU <= getNumber() && getNumber() <= CHUN;
	}


}
