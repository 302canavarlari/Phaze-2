package Domain;



// With GameGUI class, info board panel and game board are created. 
//In GameGUI class, board size and location are arranged.
//Board location is arranged in the middle of screen and for the size of dimension 850,600 is chosen.
//To show game name, title of "Chewy Lokum Legend" is added on the GUI.
//For closing game, close operation is arranged.
// GameGUI class shows InfoBoard, then player can see its score, level, and move left.


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics; 
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import UserInterface.GameBoardGUI;
import UserInterface.InfoBoard;
import UserInterface.WelcomeScreen;

public class GameGUI extends JFrame {



	static InfoBoard Panel;
	static GameBoardGUI gb ;

	public BufferedImage boardBackground;
	public ImageIcon boardIcon;




	/**
	 * Arrange the size of screen, set the location of platform and add the title of screen.
	 * 
	 *@requires Game is started. 
	 * @param 
	 * @modifies borderLayouts. 
	 */


	public GameGUI(Level levelNo, int levelNum) {
		
		if(!levelNo.IsTimeLevel()){
			Panel= new InfoBoard(levelNum,false);
			gb= new GameBoardGUI(levelNo.getRow(),
					levelNo.getColumn(),Panel,false,false);
			this.setPreferredSize(new Dimension(levelNo.getColumn()*50+200,levelNo.getRow()*50+50)); 
			setLocationByPlatform(true);
			setTitle("Chewy Lokum Legend");
			setLayout(new BorderLayout());
			setDefaultCloseOperation (EXIT_ON_CLOSE);
			Container content = getContentPane();
			content.add(gb,BorderLayout.CENTER);
			content.add(Panel,BorderLayout.EAST);
			pack();
			setVisible(true);
		}
		else {
			Panel= new InfoBoard(levelNum,true);
			gb= new GameBoardGUI(levelNo.getRow(),
					levelNo.getColumn(),Panel,false,true);
			this.setPreferredSize(new Dimension(levelNo.getColumn()*50+200,levelNo.getRow()*50+50)); 
			setLocationByPlatform(true);
			setTitle("Chewy Lokum Legend");
			setLayout(new BorderLayout());
			setDefaultCloseOperation (EXIT_ON_CLOSE);
			Container content = getContentPane();
			content.add(gb,BorderLayout.CENTER);
			content.add(Panel,BorderLayout.EAST);
			pack();
			setVisible(true);

		}
	}

	public GameGUI(Level levelNo, int levelNum, String SaveFileName, boolean saveState) {
		if(!levelNo.IsTimeLevel()){
			Panel= new InfoBoard(levelNum,false);

			gb= new GameBoardGUI(levelNo.getRow(),
					levelNo.getColumn(),Panel,saveState,SaveFileName,false);
			this.setPreferredSize(new Dimension(levelNo.getColumn()*50+200,levelNo.getRow()*50+50)); 
			setLocationByPlatform(true);
			setTitle("Chewy Lokum Legend");
			setLayout(new BorderLayout());
			setDefaultCloseOperation (EXIT_ON_CLOSE);
			Container content = getContentPane();
			content.add(gb,BorderLayout.CENTER);
			content.add(Panel,BorderLayout.EAST);
			pack();
			setVisible(true);
		}

		else{
			Panel= new InfoBoard(levelNum,true);

			gb= new GameBoardGUI(levelNo.getRow(),
					levelNo.getColumn(),Panel,saveState,SaveFileName,true);
			this.setPreferredSize(new Dimension(levelNo.getColumn()*50+200,levelNo.getRow()*50+50)); 
			setLocationByPlatform(true);
			setTitle("Chewy Lokum Legend");
			setLayout(new BorderLayout());
			setDefaultCloseOperation (EXIT_ON_CLOSE);
			Container content = getContentPane();
			content.add(gb,BorderLayout.CENTER);
			content.add(Panel,BorderLayout.EAST);
			pack();
			setVisible(true);
		}
	}



}
