package Tile;

public class SuTile extends Tile {

	public SuTile(int type, int number) {
		super(type,number);
	}

	@Override
	public Tile next() {
		return new SuTile(type,(number+1)%10);
	}

	@Override
	public String toString() {
		switch(type) {
		case MANZU:return number+"m";
		case SOZU:return number+"s";
		case PINZU:return number+"p";
		default :
			System.err.printf("SU(%d,%d)",type,number);
			return "?";
		}
	}
}
