package Domain;
//
// GameEngine class is thought to be the most important class in this project.
//Because it create Level CLass which includes level types. 
//Additionally, GameEngine class is tied with Board Class which creates base case of the game. 
//It creates base case of game because it controls move events of the lokums, and includes object class. 
//Object Class is a abstract class for creating Lokums and Obstacles. 
//Therefore, created lokums and obstacles are showed on the board class.
//GameEngine class helps to start game with method of start game and welcome screen is created in the GameEngine Class.
//Also Settings are created in this class. With the method of selectLevel(Level l), levels will be selected.
//Array of SetOfLevels is created the levels according to their 
// achieving score goal and movement of left.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import UserInterface.*;
import Domain.*;
public class GameEngine extends JComponent{
	public Board b ;
	int score;
	boolean shouldMixBoard;
	public ImageIcon boardIcon;
	public boolean didStarted;
	private InfoBoard panel;
	Random rand = new Random(); 
	public ArrayList<ArrayList<Lokum>> silinecekLokum = new ArrayList<ArrayList<Lokum>>();
	//GameEngine construct with InfoBoard.
	public GameEngine(Board bo,InfoBoard ib) {
		didStarted=false;
		b = bo;
		panel=ib;
		generateBoardString();
		panel.setboardRowNumber(b.getRow());
		panel.setboardColNumber(b.getCol());
	}
	public void generateBoardString() {
		int[][] la = new int[b.getRow()][b.getCol()];
		String s="";
		String a="";
		for(int row = 0; row < b.getRow(); row++){
			for(int col= 0; col < b.getCol(); col++){
				la [row][col] = b.getLokumAt(row, col).type;
				Lokum l= b.getLokumAt(row, col);
				s += " ";
				a+=" ";
				s += Integer.toString(l.type);
				a+= Integer.toString(l.type);
			}
			a+='\n';
		}
		System.out.println(a);
		panel.setBoardString(s);
	}
	public void removeSilinecekLokums() {
		markDeleted();
		calculateDrop();
		applyDrop();
		fillEmpty();
		endSession();
		generateBoardString();
	}
	public boolean isStable(){
		checkRows();
		checkColumns();
		return silinecekLokum.isEmpty();
	}
	public void markDeleted(int color){
		score = panel.getCurrentScore();
		int count=0;
		for(int row = 0; row < b.getRow(); row++)
		{
			for(int col= 0; col < b.getCol(); col++)
			{
				Lokum temp= b.getLokumAt(row, col);
				if(temp.type%10==color%10||temp.type==99){
					temp.type=0;
					count++;
				}
			}
		}
		score+=count*count*60;
		panel.setCurrentScore(score);
		calculateDrop();
		applyDrop();
		fillEmpty();
		endSession();
	}
	public void markDeleted() {
		score = panel.getCurrentScore();
		boolean dummy;
		for (ArrayList<Lokum> tempSilinecekLokum: silinecekLokum){
			for(int i =0; i < tempSilinecekLokum.size();i++){
				Lokum lokum= tempSilinecekLokum.get(i);
//				System.out.println(lokum.type);

				if(lokum.type>=41&&lokum.type<=44){
//					System.out.println(lokum.type);
					panel.setTimeLeft(panel.getTimeLeft()+5);
//					System.out.println(" pedric patladi");

				}


			}
			dummy=false;
			if(didStarted){
				if (tempSilinecekLokum.size() == 5) {
					score+=200;
					SpecialLokum SERDAR_HOCA_COK_YASA = new SpecialLokum(SpecialLokum.SERDAR_HOCA_COK_YASA,1,tempSilinecekLokum.get(0).getRow(),
							tempSilinecekLokum.get(0).getCol());
					PutSerdarHoca(tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol(),SERDAR_HOCA_COK_YASA);
				} else if(tempSilinecekLokum.size() == 4) {
					score+=120;
					if(tempSilinecekLokum.get(0).type%10==2){
						if(rand.nextInt(2)==0){
							SpecialLokum GREEN_WRAPPED_HORIZANTAL = new SpecialLokum(SpecialLokum.GREEN_STRIPED_HORIZANTAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutGreenStrippedHorizantal(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), GREEN_WRAPPED_HORIZANTAL);
						}else {
							SpecialLokum GREEN_WRAPPED_VERTICAL = new SpecialLokum(SpecialLokum.GREEN_STRIPED_VERTICAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							putGreenStrippedVertical(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), GREEN_WRAPPED_VERTICAL);
						}
					} else if (tempSilinecekLokum.get(0).type%10==3){
						if(rand.nextInt(2)==0){
							SpecialLokum WHITE_WRAPPED_HORIZANTAL = new SpecialLokum(SpecialLokum.WHITE_STRIPED_HORIZANTAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutWhiteStrippedHorizantal(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), WHITE_WRAPPED_HORIZANTAL);
						}else {
							SpecialLokum WHITE_WRAPPED_VERTICAL = new SpecialLokum(SpecialLokum.WHITE_STRIPED_VERTICAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutWhiteStrippedVertical(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), WHITE_WRAPPED_VERTICAL);
						}
					} else if(tempSilinecekLokum.get(0).type%10==4){
						if(rand.nextInt(2)==0){
							SpecialLokum BROWN_WRAPPED_HORIZANTAL = new SpecialLokum(SpecialLokum.BROWN_STRIPED_HORIZANTAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutBrownStrippedHorizantal(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), BROWN_WRAPPED_HORIZANTAL);
						}else {
							SpecialLokum BROWN_WRAPPED_VERTICAL = new SpecialLokum(SpecialLokum.BROWN_STRIPED_VERTICAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutBrownStrippedVertical(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), BROWN_WRAPPED_VERTICAL);
						}
					} else if(tempSilinecekLokum.get(0).type%10==1){
						if(rand.nextInt(2)==0){
							SpecialLokum RED_WRAPPED_HORIZANTAL = new SpecialLokum(SpecialLokum.RED_STRIPED_HORIZANTAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutRedStrippedHorizantal(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), RED_WRAPPED_HORIZANTAL);
						}else {
							SpecialLokum RED_WRAPPED_VERTICAL = new SpecialLokum(SpecialLokum.RED_STRIPED_VERTICAL,1,
									tempSilinecekLokum.get(0).getRow(),tempSilinecekLokum.get(0).getCol());
							PutRedStripedVertical(tempSilinecekLokum.get(0).getRow(), tempSilinecekLokum.get(0).getCol(), RED_WRAPPED_VERTICAL);
						}
					}
				}
				else if (tempSilinecekLokum.size()==3)
					score+=20;
			}
			for(Lokum lok : tempSilinecekLokum){
				if(lok.type>10&&lok.type<30){
					dummy=true;
				}
			}

			if(dummy){
				markDeletedMoreLokums(tempSilinecekLokum);
			}

			for (Lokum lok: tempSilinecekLokum){
				if(lok.type==0){
					//										if(didStarted){						
					//											SpecialLokum WRAPPED = new SpecialLokum(SpecialLokum.RED_WRAPPED, 1, lok.getRow(), lok.getCol()); 
					//											b.putLokumAt(lok.getRow(), lok.getCol(), WRAPPED);
					//											b.repaint();
					//										}			
				}
				else
				{ 
					lok.type = 0;
				} 
			}


		}
		silinecekLokum.clear();
		panel.setCurrentScore(score);
	}
	public void markAllDeleted() {
		score = panel.getCurrentScore();
		int count=0;
		for(int row = 0; row < b.getRow(); row++)
		{
			for(int col= 0; col < b.getCol(); col++)
			{
				Lokum temp= b.getLokumAt(row, col);
				temp.type=0;
				count++;
			}
		}
		score+=count*count*60;
		panel.setCurrentScore(score);
		calculateDrop();
		applyDrop();
		fillEmpty();
		endSession();
	}
	public void markCrossDeleted(Lokum focus, Lokum clicked) {
		score = panel.getCurrentScore();
		int count=0;
		int r = focus.getRow();
		int c = focus.getCol();
		for(int i = 0; i<b.columnNo;i++){
			Lokum temp = b.getLokumAt(r, i);
			temp.type=0;
			count++;
		}
		for(int i =0; i < b.rowNo;i++){
			Lokum temp = b.getLokumAt(i, c);
			temp.type=0;
			count++;
		}
		calculateDrop();
		applyDrop();
		fillEmpty();
		endSession();
		score+=count*60;
		panel.setCurrentScore(score);
	}
	public void markDeletedMoreLokums(ArrayList<Lokum> tempSilinecekLokum){
		int count=0;
		score = panel.getCurrentScore();
		for(Lokum turkishLokum: tempSilinecekLokum){
			if(turkishLokum.type>10 && turkishLokum.type/10==1){
				for(int i =0;i<b.rowNo;i++){
					b.getLokumAt(i,turkishLokum.getCol()).type=0;
					count++;
				}
			}
			else if(turkishLokum.type>10 && turkishLokum.type/10==2){
				for(int i =0;i<b.columnNo;i++){
					b.getLokumAt(turkishLokum.getRow(),i).type=0;
					count++;
				}
			}
		}
		score+=count*60;
		panel.setCurrentScore(score);
	}
	public void calculateDrop() {
		int temp;
		for (int col=0;col<b.getCol();++col){
			for (int row=(b.getRow()-1);row>=0;--row){
				Lokum bottom = b.getLokumAt(row, col);
				bottom.beforeDrop = row;
				if(bottom.type == 0){
					for(temp = row-1; temp >= 0;temp--){
						Lokum above = b.getLokumAt(temp, col);
						above.willDrop = true;
						above.dropDistance++;
					}
				}
			}
		}
	}
	public void applyDrop() {
		for(int col = 0; col < b.getCol(); col++){
			for(int row = (b.getRow()-1); row >= 0; row--){
				Lokum lok = b.getLokumAt(row, col);
				if(lok.willDrop && !(lok.type == 0)){
					b.putLokumAt(row+lok.dropDistance, col, lok);
					lok.setCol(col);
					b.putLokumAt(row, col, new BasicLokum(0,row,col)); 
				}
			}
		}
	}
	public void fillEmpty() {
		for (int row=0;row<b.getRow();row++){
			for (int col=0;col<b.getCol();col++){
				Lokum lok = b.getLokumAt(row, col);
				if (lok.type == 0){
					int LokumNum = rand.nextInt(4);
					int timed=rand.nextInt(20);
					if(b.isTimedLevel){
						if(timed<19||!didStarted){
							Lokum randLok = b.createRandomLokum(LokumNum,row,col);
							b.putLokumAt(row, col, randLok);
						}
						else{
							Lokum randLok=b.createRandomLokumWithTime(LokumNum, row, col);
							b.putLokumAt(row, col, randLok);
						}
					}
					else{
						Lokum randLok = b.createRandomLokum(LokumNum,row,col);
						b.putLokumAt(row, col, randLok);
					}
				}
			}
		}
	}
	public void endSession() {
		for (int row=0;row<b.getRow();row++){
			for (int col=0;col<b.getCol();col++){
				Lokum lok = b.getLokumAt(row, col);
				lok.beforeDrop = col;
				lok.dropDistance = 0;
				lok.willDrop = false;
			}
		}
	}
	private void checkRows() {
		int tmp; 
		for(int row = 0; row < b.getRow(); row++)
		{
			for(int col= 0; col < b.getCol()-2; col++)
			{
				Lokum start = b.getLokumAt(row, col);
				ArrayList<Lokum> tempSilinecekLokum = new ArrayList<Lokum>(Math.max(b.rowNo+5, b.columnNo+5));
				tempSilinecekLokum.add(start);
				for (tmp=(col+1);tmp<b.getCol();tmp++){
					Lokum next = b.getLokumAt(row, tmp);
					if (next.type%10 == start.type%10){
						tempSilinecekLokum.add(next);
					}
					else { break;}
				}
				if (tempSilinecekLokum.size() > 2){ 

					this.silinecekLokum.add(tempSilinecekLokum);
				}
				col = tmp - 1;
			}
		}
	}
	private void checkColumns() {
		int tmp;
		for(int col = 0; col < b.getCol(); col++)
		{
			for(int row= 0; row < b.getRow(); row++)
			{
				Lokum start = b.getLokumAt(row, col);
				ArrayList<Lokum> tempSilinecekLokum = new ArrayList<Lokum>(Math.max(b.columnNo+5, b.rowNo+5));
				tempSilinecekLokum.add(start);
				for (tmp=(row+1);tmp<b.getRow();tmp++){
					Lokum next = b.getLokumAt(tmp,col);
					if (next.type%10== start.type%10){
						tempSilinecekLokum.add(next);
					}
					else { break;}
				}
				if (tempSilinecekLokum.size() > 2){

					this.silinecekLokum.add(tempSilinecekLokum);
					row = tmp - 1;
				}
			}
		}
	}
	private void PutRedStripedVertical(int row, int col,
			SpecialLokum rED_STRIPED_VERTICAL) {
		b.putLokumAt(row, col, rED_STRIPED_VERTICAL);
		b.repaint(); 
	}
	private void PutRedStrippedHorizantal(int row, int col,
			SpecialLokum rED_WRAPPED_HORIZANTAL) {
		b.putLokumAt(row, col, rED_WRAPPED_HORIZANTAL);
		b.repaint(); 
	}
	private void PutBrownStrippedVertical(int row, int col,
			SpecialLokum bROWN_WRAPPED_VERTICAL) {
		b.putLokumAt(row, col, bROWN_WRAPPED_VERTICAL);
		b.repaint(); 
	}
	private void PutBrownStrippedHorizantal(int row, int col,
			SpecialLokum bROWN_WRAPPED_HORIZANTAL) {
		b.putLokumAt(row, col, bROWN_WRAPPED_HORIZANTAL);
		b.repaint();
	}
	private void PutWhiteStrippedVertical(int row, int col,
			SpecialLokum wHITE_WRAPPED_VERTICAL) {
		b.putLokumAt(row, col, wHITE_WRAPPED_VERTICAL);
		b.repaint();
	}
	private void PutWhiteStrippedHorizantal(int row, int col,
			SpecialLokum wHITE_WRAPPED_HORIZANTAL) {
		b.putLokumAt(row, col, wHITE_WRAPPED_HORIZANTAL);
		b.repaint();
	}
	private void putGreenStrippedVertical(int row, int col,
			SpecialLokum GREEN_WRAPPED_VERTICAL) {
		b.putLokumAt(row, col, GREEN_WRAPPED_VERTICAL);
		b.repaint();
	}
	private void PutGreenStrippedHorizantal(int row, int col,
			SpecialLokum GREEN_WRAPPED_HORIZANTAL) {
		b.putLokumAt(row, col, GREEN_WRAPPED_HORIZANTAL);
		b.repaint();
	}
	private void PutSerdarHoca(int row,int col,SpecialLokum SERDAR_HOCA_COK_YASA) {
		b.putLokumAt(row, col, SERDAR_HOCA_COK_YASA);
		b.repaint();
	}
	public void updateGame() throws InterruptedException {
		if (!isStable()) {
			markDeleted();
			calculateDrop();
		}
	}
	public void cleanBoard() {
		applyDrop();
		fillEmpty();
		endSession();
	}
	public void youlose() {
		for(int row = 0; row < b.getRow(); row++)
		{
			for(int col= 0; col < b.getCol(); col++)
			{
				Board.board[row][col]=new BasicLokum(66,row,col);
				repaint();
				Lokum randLok = new BasicLokum(66,row,col);
				b.putLokumAt(row, col, randLok);
				repaint();
			}
		}
	}
	public void youwin() {
		for(int row = 0; row < b.getRow(); row++)
		{
			for(int col= 0; col < b.getCol(); col++)
			{
				Board.board[row][col]=new BasicLokum(65,row,col);
				repaint();
				Lokum randLok = new BasicLokum(65,row,col);
				b.putLokumAt(row, col, randLok);
				repaint();
			}
		} 
	}
}