package e1;

import java.util.Random;

public class RandomEmptyPairInitializer {
    
    private final Random random = new Random();
    private int size;

    public RandomEmptyPairInitializer(int size) {
        this.size = size;
    }

    public Pair<Integer, Integer> getEmptyPair(Pair<Integer, Integer> pawnPair) {
        Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(this.size),this.random.nextInt(this.size));
    	// the recursive call below prevents clash with an existing pawn
    	return pawnPair!=null && pawnPair.equals(pos) ? this.getEmptyPair(pawnPair) : pos;
    }
}
