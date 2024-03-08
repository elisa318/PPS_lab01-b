package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final RandomEmptyPairInitializer randomPair;
	private final int size;
	private static int ABS_VALUE = 3;
	private static int START_INDEX = 0;
	 
    public LogicsImpl(int size){
    	this.size = size;
		this.randomPair = new RandomEmptyPairInitializer(this.size);
		this.pawn = this.randomPair.getEmptyPair(null);
		this.knight = this.randomPair.getEmptyPair(this.pawn);
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<START_INDEX || col<START_INDEX || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = this.getXValue(row);
		int y = this.getYValue(col);
		if (x!=START_INDEX && y!=START_INDEX && this.getAbsCondition(x, y)) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}

	private boolean getAbsCondition(int x, int y) {
		return Math.abs(x)+Math.abs(y)==ABS_VALUE;
	}

	private int getXValue(int row) {
		return row-this.knight.getX();
	}

	private int getYValue(int col) {
		return col-this.knight.getY();
	}
}
