package UserInterface;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
/*
 this class monitors user input on the game board
 */
public class MouseListener extends MouseInputAdapter {
	private GameBoardGUI gamePanel;
	int c,r;
	boolean gameNotOver;
	int rooo, cooo;

	public MouseListener(GameBoardGUI gamePanel,int row, int column){
		this.gamePanel = gamePanel;
		gameNotOver=true;
		r=row;
		c=column;
	}
	public void mouseClicked (MouseEvent e){
		if(gameNotOver){
			int col = (e.getX())/50;
			int row = (e.getY())/50;
			rooo = row;
			cooo = col;
			if (col < 0) {col = 0; cooo = 0; }
			if (col >= c) {col = c-1; cooo = c-1; }
			if (row < 0) {row = 0; rooo = 0; }
			if (row >= r) {row = r-1; rooo = r-1; }
			System.out.println("Clicked Column:" + (col+1) + " Clicked Row:" + (row+1));
			gamePanel.clickPerformed(col, row);
			gamePanel.repaint();
		}
		
		
	}
	
	public String toString() {
		String s = "Clicked Column: " + Integer.toString(cooo+1) + " Clicked Row: " +  Integer.toString(rooo+1);
		return s;
	}
}
