package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.Map;
import java.util.HashMap;

public class StrategyFactory{

    private Map<Integer, DecisionStrategy> strategies = new HashMap<Integer, DecisionStrategy>();

    public StrategyFactory(){   
        strategies.put(1, new PhaseOneStrategy());
        strategies.put(2, new PhaseTwoStrategy());
    }

    public DecisionStrategy getStrategy(int phase){
        return strategies.get(phase);
    }
}

