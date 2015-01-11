package Domain;

/**
 * This class is an abstract class about levels. It contains the basic elements 
 * and functions that every level should contain
 */

public abstract class Level {

	int levelNumber;
	int type;
	int targetScore;
	int row;
	int column;
	boolean unlock;
	int moveLeft;

	public Level(int type,int row,int column,int target,int moveLeft,boolean isUnlocked){
		this.type=type;
		unlock=isUnlocked;
		this.row=row;
		this.moveLeft=moveLeft;
		this.column=column;
		targetScore=target;

	}
	public int getType(){
		return type;
	}
	/**
	 * Return how many rows that this particular level should have.
	 * 
	 * @requires Creator should define number of rows when defining the level.
	 * @param row 		Number of rows
	 */
	public int getRow() {

		return row;

	}
	public void setUnlocked(boolean dummy){
		unlock=dummy;
	}

	public int getTarget(){
		return targetScore;
	}
	/**
	 * Return how many columns that this particular level should have.
	 * 
	 * @requires Creator should define number of columns when defining the level.
	 * @param column 		Number of columns
	 */
	public int getColumn() {

		return column;

	}
	public int getMoveLeft() {
		return moveLeft;
	}
	
	public boolean IsTimeLevel() {
		if(type==2){
			return true;
		}
		return false;
	}

}
