import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Logics;
import e1.LogicsImpl;
import e1.Pair;

public class LogicTest {

    Logics logic;
    Pair<Integer, Integer> knightCoordinate;
    Pair<Integer, Integer> pawnCoordinate;
    private static int SIZE = 5;
    private static int SIZE_OUT_OF_BOUND = 6;
   
    @BeforeEach
    void beforeEach() {
        logic = new LogicsImpl(SIZE);
        knightCoordinate = this.inizializeEmptyPair();
        pawnCoordinate = this.inizializeEmptyPair();
    }

    private Pair<Integer, Integer> inizializeEmptyPair() {
        return new Pair<Integer,Integer>(null, null);
    }
    private void knightPositionCalculate(int row, int col) {
        if(logic.hasKnight(row, col)) {
            this.knightCoordinate = new Pair<Integer,Integer>(row, col);
        }
    }

    private void pawnPositionCalculate(int row, int col) {
        if(logic.hasPawn(row, col)) {
            this.pawnCoordinate = new Pair<Integer,Integer>(row, col);
        }
    }


    private int getRowVal() {
        int actual_row = pawnCoordinate.getX();
        int actual_col = pawnCoordinate.getY();

        Random random = new Random();
        int row = random.nextInt(SIZE) ;
    }

    @Test
    public void pawnAndKnightPositionTest() {
        for(int i = 0; i < SIZE; i ++) {
            for(int j = 0; j < SIZE; j ++) {
                this.pawnPositionCalculate(j, i);
                this.knightPositionCalculate(i, j);
            }
        }

        assertTrue(logic.hasPawn(pawnCoordinate.getX(), pawnCoordinate.getY()));
        assertTrue(logic.hasKnight(knightCoordinate.getX(), knightCoordinate.getY()));

        int row = new Random().nextInt(SIZE);
        int col = new Random().nextInt(SIZE);

        assertFalse(logic.hasPawn(this.getRowVal(), col));
    }

    @Test
    public void hitOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(SIZE_OUT_OF_BOUND, SIZE_OUT_OF_BOUND));
    }

}