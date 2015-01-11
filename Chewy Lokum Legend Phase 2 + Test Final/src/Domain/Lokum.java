package Domain;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import UserInterface.GameBoardGUI;

public abstract class Lokum  {
	/*
	 * this is an abstract class for different kinds of lokums(basicLokum,specialLokum,...)
	 * objectType for lokums assigned to be 1.
	 * each lokum has row, column and color.
	 */
	static int LOKUM_TYPE=1;
	
	int row;
	int column;
	public 
	int type;
	public String lokumColor;

	public boolean inFocus;
	public boolean willDrop;
	public int beforeDrop;
	public int dropDistance;
	ImageIcon gemIcon=new ImageIcon();

	public Lokum(int lokumType, int r, int c) {
		/*
		 * depends on lokumType, determine lokums color.
		 */
		row = r;
		column = c;

		type = lokumType;
		switch (type) {
		case 0:
			lokumColor=null;
			break;
		case 1:
			lokumColor="red";
			break;
		case 2:
			lokumColor="green";
			break;
		case 3:
			lokumColor="white";
			break;
		case 4:
			lokumColor="brown";
		}
		if (this.type != 0){
			this.gemIcon.setImage(GameBoardGUI.imageLibrary.getImage(type));
		}

	}
	public void draw(Graphics g){
		if (type!=0)
			gemIcon.paintIcon(null, g, column*50, row*50);
		if (inFocus){
			ImageIcon focusIcon = new ImageIcon();
			focusIcon.setImage(GameBoardGUI.imageLibrary.getImage(0));
			focusIcon.paintIcon(null,g,column*50,row*50);
		}    

	}



	public boolean isNeighbor(Lokum other) {
		if (Math.abs(row-other.row) == 1 || Math.abs(column-other.column) == 1){
			return true;
		}
		else if (Math.abs(row-other.row) == 0 && Math.abs(column-other.column) == 1){
			return true;
		}
		else {return false;}
	}


	//returns lokums type for indicate its color.
	public int getType(){
		return type;
	}

	//	in the case of need for move method.
	public void setRow(int row){
		this.row = row;
	}
	public void setCol(int col){ 
		this.column = col;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return column;
	}

}
