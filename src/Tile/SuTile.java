package Tile;

public class SuTile extends Tile {

	public SuTile(int type, int number) {
		super(type,number);
	}

	@Override
	public Tile next() {
		if(number==9)
			return null;
		return new SuTile(type,number+1);
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

	@Override
	public Tile prev() {
		if(number==1)
			return null;
		return new SuTile(type,number-1);
	}
}
