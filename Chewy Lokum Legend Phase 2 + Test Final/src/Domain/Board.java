package Domain;

/**
 * This class keeps track of the gameObjects in a 2D array
 */



import java.awt.Color;

import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JComponent;

import UserInterface.*;

public class Board extends JComponent{
	int rowNo ;
	Random rand=new Random();
	int columnNo ;
	InfoBoard infb;
	public GameEngine solver;
	public static  Lokum[][] board;
	public boolean isTimedLevel;

	/**
	 * Construct the board.
	 * @param row number
	 * @param column number
	 * @modifies rowNo
	 * @modifies columnNo
	 */
	public Board( int row, int column,InfoBoard ib,boolean TimedLevel){
		infb = ib;
		solver=new GameEngine(this, ib);
		rowNo = row;
		isTimedLevel=TimedLevel;
		columnNo = column;
		board = new Lokum[row][column];
//		if(TimedLevel){
//			fillInitialBoard();
//		}
//		else{
			fillInitialBoard();
//		}
	}

	public Board(String s, int row, int column,InfoBoard ib,boolean TimedLevel){
		infb = ib;
		rowNo = row;
		isTimedLevel=TimedLevel;
		columnNo = column;
		board = new Lokum[row][column];
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) {
			for(int r=0;r<rowNo;r++){
				for (int c =0; c<columnNo;c++){
					int i = Integer.parseInt(st.nextToken());
					fillBoardWithCertainLokum(i,r,c);							
				}
			}
		}
		solver=new GameEngine(this, ib);

	}
	public int getRow(){
		return rowNo;
	}
	public int getCol(){
		return columnNo;
	}
	public Lokum[][] getBoardArray(){
		return board;
	}
	public Lokum getLokumAt(int row,int col) {
		return  (Lokum) board[row][col];
	}
	public void putLokumAt(int row,int col,Lokum lok){
		lok.setCol(col);
		lok.setRow(row);
		board[row][col] = lok;
	}
	/**
	 * Creates initial board with random game objects.
	 * 
	 * @requires 	game must be started.
	 * @requires	board must be created.
	 * @param 		gets level's obstacles' position.
	 * @modifies 	draw board for the first time.
	 */
	private void fillInitialBoard(){

		Random rand = new Random(); 
		for (int r=0;r<rowNo;r++){
			for (int c=0;c<columnNo;c++){
				int LokumNum = rand.nextInt(4);
				board[r][c] = createRandomLokum(LokumNum,r, c);
//				System.out.println(getLokumAt(r, c).lokumColor.toString());
			}
		}
	}
	private void fillBoardWithCertainLokum(int lokType, int r, int c){
		Lokum L;
		if (lokType < 10) L =  new BasicLokum (lokType,r, c);
		else L = new SpecialLokum (lokType,1,r, c);
		board[r][c] = L;
	}
	public BasicLokum createRandomLokum(int lokumNum,int row,int col){
		BasicLokum lok = null;
		lok = new BasicLokum(lokumNum + 1,row,col);
		return lok;
	}
	
	
	/**
	 * moves lokums accordingly player's decision.
	 * @throws InterruptedException 
	 * 
	 * @requires 	game must be started.
	 * @requires 	player must select two adjacent lokums which can be *******************************
	 * @modifies 	destroy necessary lokums, update score, fill with new random lokums, if necessary create special lokum.
	 */
	public void Move(Lokum l1, Lokum l2) throws InterruptedException{
		int c1 = l1.column;
		int c2 = l2.column;
		int r1 = l1.row;
		int r2 = l2.row;

		l1.setCol(c2);
		l1.setRow(r2);
		l2.setCol(c1);
		l2.setRow(r1);

		board[r1][c1] = l2;
		board[r2][c2] = l1;
		
		if(!infb.getSpecialSwapEnabled()){
		if(!GameBoardGUI.solver.isStable()){
			if(!isTimedLevel){
			decreaseMoves();}
		}
		
			if(GameBoardGUI.solver.isStable()){
				MoveBack(l1,l2);
			} 	
			}
		else infb.setSpecialMove(); 	
		GameGUI.gb.cleanBoard();
		GameGUI.gb.repaint();
		GameGUI.gb.updateGame();
		GameGUI.gb.checkGameOver();

	}
	private void decreaseMoves() {
		GameBoardGUI.ib.setMoveLeft(GameBoardGUI.ib.getMoveLeft()-1);
	}

	private void MoveBack(Lokum l1, Lokum l2) {
		int c1 = l1.column;
		int c2 = l2.column;
		int r1 = l1.row;
		int r2 = l2.row;

		l1.setCol(c2);
		l1.setRow(r2);
		l2.setCol(c1);
		l2.setRow(r1);

		board[r1][c1] = l2;
		board[r2][c2] = l1;
	}

	/**
	 * moves two special lokums accordingly player's decision.
	 * 
	 * @requires 	game must be started.
	 * @requires 	player must select two adjacent special lokums.
	 * @modifies 	destroy necessary lokums, update score, fill with new random lokums.
	 */
	public void serdarinGucu(Lokum l1){


		int color= l1.type;
		solver.markDeleted(color);


	}

	/**
	 * Mix the lokums on the board
	 * 
	 * @requires shouldMixBoard should be true
	 * @modifies location of lokums
	 */
	private void mixing() {

		Random random = new Random();

		for (int i = board.length - 1; i > 0; i--) {
			for (int j = board[i].length - 1; j > 0; j--) {
				int m = random.nextInt(i + 1);
				int n = random.nextInt(j + 1);

				Lokum temp = board[i][j];
				board[i][j] = board[m][n];
				board[m][n] = temp;
			}
		}
	}

	/**
	 * show win/lose game screen according to reaching level goal.
	 * 
	 * @requires 	level goal is achieved or remaining number of moves is 0.
	 * @modifies 	if level goal is achieved, unlock next level, shows end game screen.
	 */
	private void endGameScreen(){

	}
	public void serdarinSuperGucu() {
		solver.markAllDeleted();	
	}
	public void specialMove(Lokum focus, Lokum clicked) {
		solver.markCrossDeleted(focus,clicked);
	}

	public Lokum createRandomLokumWithTime(int lokumNum, int row, int col) {
		TimeLokum lok = null;
		lok = new TimeLokum(lokumNum +41,row,col);
		return lok;	
	}
}
