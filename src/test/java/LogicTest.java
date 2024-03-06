import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Logics;
import e1.LogicsImpl;
import e1.Pair;

public class LogicTest {

    Logics logic;
    Pair<Integer, Integer> knightCoordinate;
    Pair<Integer, Integer> pawnCoordinate;
    private static int GRID_SIZE = 5;
    private static int SHIFT_NOT_ALLOWED = 1;
    private static int SHIFT_ALLOWED = 2;
   
    @BeforeEach
    void beforeEach() {
        logic = new LogicsImpl(GRID_SIZE);
        this.setPawnAndKnightPair();
    }

    private void setPawnAndKnightPair() {
        for(int i = 0; i < GRID_SIZE; i ++) {
            for(int j = 0; j < GRID_SIZE; j ++) {
                this.setKnightPair(j, i);
                this.setPawnPair(i, j);
            }
        }
    }

    private void setKnightPair(int row, int col) {
        if(logic.hasKnight(row, col)) {
            this.knightCoordinate = new Pair<Integer,Integer>(row, col);
        }
    }

    private void setPawnPair(int row, int col) {
        if(logic.hasPawn(row, col)) {
            this.pawnCoordinate = new Pair<Integer,Integer>(row, col);
        }
    }

    private int getNewCoordinate(int actualValue, int stepSize) {
        return actualValue + stepSize < GRID_SIZE ? actualValue + stepSize : actualValue - stepSize;
    }

    private Pair<Integer, Integer> getHitAllowedNewPairPos(int shiftX, int shiftY) {
        int newX = getNewCoordinate(this.knightCoordinate.getX(), shiftX);
        int newY = getNewCoordinate(this.knightCoordinate.getY(), shiftY);

        logic.hit(newX, newY);

        this.setPawnAndKnightPair();

        return new Pair<Integer,Integer>(newX, newY);
    }

    @Test
    public void pawnPositionTest() {
        assertTrue(logic.hasPawn(pawnCoordinate.getX(), pawnCoordinate.getY()));
    }

    @Test
    public void knightPositionTest() {
        assertTrue(logic.hasKnight(knightCoordinate.getX(), knightCoordinate.getY()));
    }

    @Test
    public void hitOutOfBounds() {
        int sizeOutOfBound = GRID_SIZE + SHIFT_NOT_ALLOWED;
        assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(sizeOutOfBound, sizeOutOfBound));
    }

    @Test
    public void isNotClosedTest() {
        assertFalse(knightCoordinate.equals(pawnCoordinate));
    }

    @Test
    public void moveKnightToNotAllowedPosition() {
        int knightActualX = this.knightCoordinate.getX();
        int knightActualY = this.knightCoordinate.getY();

        Pair<Integer, Integer> notAllowedPosition = new Pair<Integer,Integer>(knightActualX + SHIFT_NOT_ALLOWED, knightActualY + SHIFT_NOT_ALLOWED);
        assertFalse(logic.hit(notAllowedPosition.getX(), notAllowedPosition.getY()));
    }

    @Test
    public void moveKnightVerticalToAllowedPosition() {
        Pair<Integer, Integer> newPosition = getHitAllowedNewPairPos(SHIFT_ALLOWED, SHIFT_NOT_ALLOWED);

        assertEquals(new Pair<Integer, Integer>(newPosition.getX(), newPosition.getY()), knightCoordinate);
    }

    @Test
    public void moveKnightHorizontalToAllowedPosition() {
        Pair<Integer, Integer> newPosition = getHitAllowedNewPairPos(SHIFT_NOT_ALLOWED, SHIFT_ALLOWED);

        assertEquals(new Pair<Integer, Integer>(newPosition.getX(), newPosition.getY()), knightCoordinate);
    }

}