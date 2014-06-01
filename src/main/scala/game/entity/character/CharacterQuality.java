package game.entity.character;

public enum CharacterQuality {

	S(5,2, 4,2, 2,1),
	A(5,1, 4,1, 2,0),
	B(4,1, 3,1, 2,0),
	C(3,1, 2,1, 2,0),
	D(2,0, 2,0, 1,0),
	F(1,0, 1,0, 1,0)
	;
	
	private int pStatIncMax;
	private int pStatIncMin;
	private int sStatIncMax;
	private int sStatIncMin;
	private int oStatIncMax;
	private int oStatIncMin;
	
	private CharacterQuality(int pIncMax, int pIncMin, int sIncMax, int sIncMin, int oIncMax, int oIncMin){
		this.pStatIncMax = pIncMax;
		this.pStatIncMin = pIncMin;
		this.sStatIncMax = sIncMax;
		this.sStatIncMin = sIncMin;
		this.oStatIncMax = oIncMax;
		this.oStatIncMin = oIncMin;
	}

	public int getpStatIncMax() {
		return pStatIncMax;
	}

	public int getpStatIncMin() {
		return pStatIncMin;
	}

	public int getsStatIncMax() {
		return sStatIncMax;
	}

	public int getsStatIncMin() {
		return sStatIncMin;
	}

	public int getoStatIncMax() {
		return oStatIncMax;
	}

	public int getoStatIncMin() {
		return oStatIncMin;
	}
}
