package game.entity.event;

public enum StatWeight {

	HeavyNegative("---",-1.0),
	ModerateNegative("--",-0.5),
	SlightNegative("-",-0.25),
	SlightEither("",0.0),
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
	
	public static StatWeight getWeightFromSymbol(String symbol){
		for(StatWeight weight : values()){
			if(weight.getSymbol().equals(symbol)){
				return weight;
			}
		}
		
		return null;
	}
}
