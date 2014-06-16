package game.entity.event;

public enum StatWeight {

	HeavyNegative("---",-1.0),
	ModerateNegative("--",-0.5),
	SlightNegative("-",-0.25),
	SlightPositive("+",0.25),
	ModeratePositive("++",0.5),
	HeavyPositive("+++",1.0)
	;
	
	private String symbol;
	private double multiplier;
	
	private StatWeight(String symbol, double mult){
		this.symbol = symbol;
		this.multiplier = mult;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getMultiplier() {
		return multiplier;
	}
	
	public static double multiplier(StatWeight weight){
		return weight.getMultiplier();
	}
	
	public static StatWeight nextPositive(StatWeight weight){
		switch(weight){
		case HeavyNegative: return ModerateNegative;
		case ModerateNegative: return SlightNegative;
		case SlightNegative: return SlightPositive;
		case SlightPositive: return ModeratePositive;
		case ModeratePositive: return HeavyPositive;
		case HeavyPositive: return HeavyPositive;
		}
		
		return null;
	}
	
	public static StatWeight nextNegative(StatWeight weight){
		switch(weight){
		case HeavyNegative: return HeavyNegative;
		case ModerateNegative: return HeavyNegative;
		case SlightNegative: return ModerateNegative;
		case SlightPositive: return SlightNegative;
		case ModeratePositive: return SlightPositive;
		case HeavyPositive: return ModeratePositive;
		}
		
		return null;
	}
	
	public static StatWeight getWeightFromSymbol(String symbol){
		for(StatWeight weight : values()){
			if(weight.getSymbol().equals(symbol)){
				return weight;
			}
		}
		
		return null;
	}
}
