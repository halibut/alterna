package game.entity.character;

public enum Stat {

	MaxHP(1, 99999),
	MaxAP(1, 9999),
	Strength(1, 999),
	Defense(1, 999),
	Focus(1, 999),
	Spirit(1, 999),
	Accuracy(1, 999),
	Evasion(1, 999),
	Speed(1, 999),
	Luck(1, 999)
	;

	private int min;
	private int max;

	private Stat(int min, int max){
		this.min = min;
		this.max = max;
	}
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	
}
