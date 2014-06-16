package game.entity.character;

public enum Personality {
	
	Charismatic,
	Pacifist, 
	Friendly, 
	Good,
	Hardworking,
	Brave,
	Generous,
	MalePreference,
	FemalePreference,
	Satisfied
	;
	
	private Personality(){
		
	}

	public int getMin(){
		return -getMax();
	}
	public int getMax(){
		return 255;
	}
	
	public int getRange(){
		return getMax() - getMin();
	}
	
	public double normalized(int strength){
		double value = (double)strength / (double)getMax();
		
		return Math.max(-1.0, Math.min(1.0, value));
	}
}
