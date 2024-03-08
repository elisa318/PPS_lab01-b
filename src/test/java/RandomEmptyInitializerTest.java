import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import e1.Pair;
import e1.RandomEmptyPairInitializer;

public class RandomEmptyInitializerTest {
    
    private static int GRID_SIZE = 5;
    private RandomEmptyPairInitializer pairInitializer = new RandomEmptyPairInitializer(GRID_SIZE);
    
    @Test
    public void createRandomPair() {
        Pair<Integer, Integer> randomPair = pairInitializer.getEmptyPair(null);
        assertEquals(new Pair<Integer, Integer>(randomPair.getX(), randomPair.getY()), randomPair);
    }
    
}
