package Domain;

public class CreateLevels{
	public static Level[] setOfLevels= new Level[5]; 

	public CreateLevels(){
		setOfLevels[0]=new TimeBasedLevel(70000,6,6,50,true);
		setOfLevels[1]=new ScoreBasedLevel(5000,4,4,2,false);
		setOfLevels[2]=new ScoreBasedLevel(350000,7,5,4,false);
		setOfLevels[3]=new ScoreBasedLevel(666666666, 4, 6,55,false);
		setOfLevels[4]=new ScoreBasedLevel(30000, 5, 5,11,false);

	}
	public void setLevelUnlocked(int levelNo){
		if(levelNo<setOfLevels.length-1){
		setOfLevels[levelNo+1].setUnlocked(true);
		}
	}
}
