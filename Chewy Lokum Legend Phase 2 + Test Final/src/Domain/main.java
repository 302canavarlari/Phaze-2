package Domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import UserInterface.GameBoardGUI;
import sun.audio.*;
import UserInterface.InfoBoard;

import UserInterface.SoundLibrary;
import UserInterface.WelcomeScreen;

public class main {
	
	static InfoBoard Panel;
	static GameBoardGUI gb ;
	static GameGUI gg;
	
	
	 public static void main(String[] args){
    	 new CreateLevels();
    	 WelcomeScreen wc=new WelcomeScreen();
    	 
    	 String str = wc.getLoadOptionName();
    	 
    	 if(str==null )
    	gg = new GameGUI(CreateLevels.setOfLevels[wc.getLevelIndex()],wc.getLevelIndex()); 
    	 else 
    	gg = new GameGUI(CreateLevels.setOfLevels[wc.getLevelIndex()],wc.getLevelIndex(),str,wc.getStartFromSave()); 
	 }

	 public static void dis(){
		 gg.dispose();
		 
	 }
	 
}
