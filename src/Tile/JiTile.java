package Tile;

public class JiTile extends Tile {

	public JiTile(int type, int number) {
		super(type,number);

	}

	public Tile prev() {
		return null;
	}

	@Override
	public Tile next() {
		if(isFanpai())
			return new JiTile(type,(number+1)%4);
		if(is3genpai())
			return new JiTile(type,(number+1-4)%3+4);
		return null;
	}

	@Override
	public String toString() {
		switch(number) {
			case TON:return "東";
			case NAN:return "南";
			case SYA:return "西";
			case PEI:return "北";
			case HAKU:return "白";
			case HATSU:return "発";
			case CHUN:return "中";
			default:
				System.err.printf("JI(%d,%d)",type,number);
				return "?";
		}
	}
}
