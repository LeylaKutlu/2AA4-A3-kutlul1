package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class StrategyFactoryTest { 
    private StrategyFactory strategyFactory;
    private PhaseTwoStrategy phaseTwoStrategy;
    private PhaseOneStrategy phaseOneStrategy;

    @BeforeEach
    public void setUp() {
        strategyFactory = new StrategyFactory();
    }

    @Test
    public void phaseOneTest() {
        assertTrue(strategyFactory.getStrategy(1) instanceof PhaseOneStrategy);
    } 

    @Test
    public void phaseTwoTest() {
        assertTrue(strategyFactory.getStrategy(2) instanceof PhaseTwoStrategy);
    }
}