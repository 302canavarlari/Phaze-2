package Domain;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import UserInterface.GameBoardGUI;

public class SpecialLokum extends Lokum {
	/*
	 * to determine special lokum's power.
	 */
	int Specialtype;
	static final int GREEN_STRIPED_HORIZANTAL=12; 
	static final int GREEN_STRIPED_VERTICAL=22;
	static final int RED_STRIPED_HORIZANTAL=11;
	static final int RED_STRIPED_VERTICAL=21;
	static final int WHITE_STRIPED_HORIZANTAL=13;
	static final int WHITE_STRIPED_VERTICAL=23;
	static final int BROWN_STRIPED_HORIZANTAL=14;
	static final int BROWN_STRIPED_VERTICAL=24;
	static final int GREEN_WRAPPED=32; 
	static final int RED_WRAPPED=31;
	static final int WHITE_WRAPPED=33;
	static final int BROWN_WRAPPED=34;
	static final int SERDAR_HOCA_COK_YASA=99;
	
	public SpecialLokum(int SpeciallokumType, int specialType, int r, int c) {
		super(SpeciallokumType, r, c);
		type = SpeciallokumType;	
		
		this.gemIcon.setImage(GameBoardGUI.imageLibrary.getImage(SpeciallokumType));	
	}
}
