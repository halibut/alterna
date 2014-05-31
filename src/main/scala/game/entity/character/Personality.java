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
		return -255;
	}
	public int getMax(){
		return 255;
	}
	
	public int getRange(){
		return getMax() - getMin();
	}
}
