package tk.owab.hammerdice;

public class DiceProb {


	/**
	 * zmiana modelu
	 */
	private static long newtonBinomial(int n, int k) {
		
		long newtonBinomial = 1;
		for (int i=1;i<k+1; i++ ) {
			newtonBinomial = newtonBinomial *  (n - i + 1) / i;
            //Log.d("hammer", newtonBinomial+"");
        }
		return newtonBinomial;
	}
	
	private static double Bernoulii( int n, int k, double p) {
		
	    double q=1.0-p;
	    double probability=1.0;
	    probability=newtonBinomial(n,k) * Math.pow(p, k)  * Math.pow(q,(n-k));
	    
	    return probability;
	}
	public static double fightSimulator(int numberDice, int minimumKills, double chanceToSave, double chanceToWound, double chanceToHit) {
	    
	    double resault=0;
	    for (int i = minimumKills; i < (numberDice + 1); i++) {
	        resault=resault+Bernoulii(numberDice,i,chanceToHit)*secondCondition(minimumKills, i, chanceToWound, (1 - chanceToSave));
	    }
	    return resault;
	}
	//Function of the oneCase
	private static double thirdCondition(int minimumKills, int numberWins, double chanceToSave) {
	    		    
		double resault=0;
		for (int i=minimumKills; i<(numberWins + 1) ; i++){
		    resault=resault+Bernoulii(numberWins,i,chanceToSave);
		    //print resault
		}
		return resault;
	}
	//Function of the oneCase and secondCase
	private static double secondCondition(int minimumKills, int numberWins, double chanceToWound, double chanceToSave) {
	    		    
		double resault=0;
	    for(int i=minimumKills; i< (numberWins + 1) ; i++) {
	    	resault=resault+Bernoulii(numberWins, i, chanceToWound)*thirdCondition(minimumKills, i, chanceToSave);
	    }
	    return resault;
	}
	
}