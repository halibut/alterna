package game.entity.character;

public enum StatType {

	MaxHP(1, 9999),
	MaxAP(1, 999),
	Strength(1, 255),
	Defense(1, 255),
	Focus(1, 255),
	Spirit(1, 255),
	Accuracy(1, 255),
	Evasion(1, 255),
	Speed(1, 255),
	Luck(1, 255)
	;

	private int min;
	private int max;

	private StatType(int min, int max){
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
