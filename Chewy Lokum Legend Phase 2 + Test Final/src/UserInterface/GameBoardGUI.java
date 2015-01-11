package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import Domain.Board;
import Domain.CreateLevels;
import Domain.GameEngine;
import Domain.Lokum;
import Domain.SaveFileGenerator;

/**
 * This class creates a JButton array, fills it with random lokum colors 
 * and adds them to the boardGUI
 */

public class GameBoardGUI extends JComponent implements ActionListener{

	public int row;
	int column;

	private Board gameBoard;
	public static GameEngine solver;
	private Lokum focus;
	private boolean started;
	private int score;
	private int level;
	private int combo;
	public BufferedImage boardImg;
	public ImageIcon boardIcon;
	public static ImageLibrary imageLibrary = new ImageLibrary();
	public static SoundLibrary soundLibrary = new SoundLibrary();
	public static InfoBoard ib ;
	public SaveFileGenerator sg;
	public MouseListener ms;

	/**
	 * create the GUI of the board
	 * @requires Level is selected
	 * @param board with.
	 * @param board height
	 * @modifies gameBoardGUI
	 */
	public GameBoardGUI(int r, int c, InfoBoard panel, boolean startFromSave,boolean IsTimeNecessary) {
		
		ib= panel;
		row = r;
		column = c;

		soundLibrary.playAudio("Background");
		if (startFromSave) {

			gameBoard = new Board(sg.boardStatus, sg.boardRowNo, sg.boardColNo,ib,IsTimeNecessary);

			ib.setCurrentScore(sg.currentScore);
			ib.setMoveLeft(sg.moveLeft);
			solver=new GameEngine(gameBoard,ib);
			solver.didStarted=true;

		} else {

			gameBoard = new Board(r,c,ib,IsTimeNecessary);
			solver=new GameEngine(gameBoard,ib);
			while (!solver.isStable()) {
				solver.removeSilinecekLokums();
				;}
			solver.didStarted=true;
			ib.setCurrentScore(0);
		}

		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(r*50+100,
				c*50)); 
		ms = new MouseListener(this,r,c);
		this.addMouseListener(ms);	}

	public GameBoardGUI(int r, int c, InfoBoard panel, boolean startFromSave, String saveName,boolean IsTimeNecessary) {
		ib= panel;
		row = r;
		column = c;
		soundLibrary.playAudio("Background");

		if (startFromSave) {
			sg = new SaveFileGenerator();
			sg.read(saveName);

			gameBoard = new Board(sg.boardStatus, sg.boardRowNo, sg.boardColNo,ib,IsTimeNecessary);
			solver=new GameEngine(gameBoard,ib);
			ib.setCurrentScore(sg.currentScore);
			if (IsTimeNecessary) ib.setTimeLeft(sg.moveLeft);
			else ib.setMoveLeft(sg.moveLeft);
			solver=new GameEngine(gameBoard,ib);
			solver.didStarted=true;

		} else {

			gameBoard = new Board(r,c,ib,IsTimeNecessary);
			solver=new GameEngine(gameBoard,ib);
			while (!solver.isStable()) {
				solver.removeSilinecekLokums();}
			solver.didStarted=true;
			ib.setCurrentScore(0);
		}

		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(r*50+100,
				c*50)); 
		ms = new MouseListener(this,r,c);
		this.addMouseListener(ms);	}

	public void actionPerformed(ActionEvent e){

	}
	public void updateGame()   {	


		while (!solver.isStable()) {
			solver.removeSilinecekLokums();
			System.out.println("2");
			GameBoardGUI.soundLibrary.playAudio("match");


		}
	}
	public void checkGameOver() {
		if (ib.privateScoreWithNumber >= ib.getTargetScore() ){ 
			ms.gameNotOver = false;
			youWIN();
			ib.winScreen();

		}

		if (ib.moveLeftWithNumber == 0){ 
			ms.gameNotOver = false;
			youLOSE();
			ib.loseScreen();
		}

	}
	private void youWIN() {
		solver.youwin();	

	}

	public void cleanBoard() {
		solver.applyDrop();
		solver.fillEmpty();
		solver.endSession();

	}


	public void clickPerformed(int click_x,int click_y) {
		Lokum clicked = gameBoard.getLokumAt(click_y, click_x);


		if (focus == null) {
			focus = clicked;
			clicked.inFocus = true;
			GameBoardGUI.soundLibrary.playAudio("select");
		}
		else {
			if (focus.equals(clicked)) {
				clicked.inFocus = false;
				focus = null;
			}
			else {
				if(focus.isNeighbor(clicked) || ib.getSpecialSwapEnabled()){
					if(focus.type==99){
						if(clicked.type==99){
							gameBoard.serdarinSuperGucu();
						}
						else{
							gameBoard.serdarinGucu(clicked);
						}
					}
					else if(clicked.type==99){
						gameBoard.serdarinGucu(focus);
					}else if(clicked.type>10&&focus.type>10&&clicked.type<40&&focus.type<40){
						gameBoard.specialMove(focus,clicked);
					}

					focus.inFocus = false;
					try {
						gameBoard.Move(focus,clicked);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					focus = null;
				}
				else {
					focus.inFocus = false;
					focus = clicked;  
					clicked.inFocus = true;
				}
			}
		}
	}
	public void paintComponent(Graphics g){

		drawLokums(g);

	}
	private void drawLokums(Graphics g){
		int satir,sutun;
		for (satir=0;satir<row;satir++){
			for (sutun=0;sutun<column;sutun++){
				Lokum LO = gameBoard.getLokumAt(satir, sutun);
				LO.draw(g);
			}
		}

	}

	public void youLOSE() {
		solver.youlose();		
	}
}