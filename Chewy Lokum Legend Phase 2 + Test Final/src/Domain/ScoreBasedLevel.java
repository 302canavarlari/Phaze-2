package Domain;


/**
 * This class contains the basic elements 
 * and functions that every Score based level should contain
 */

public class ScoreBasedLevel extends Level {

	public int targetScore;
	
	
	/**
	 * create the score based level
	 * 
	 * @param level number
	 * @param target score
	 * @param i 
	 * @modifies type
	 * @modifies levelNumber
	 * @modifies targetScore
	 */
	public ScoreBasedLevel(int target, int row, int column,int moveLeft,boolean isUnlocked) {
		super(1,row,column,target,moveLeft,isUnlocked);
		targetScore = target;
		
		
	}
	
	
}
