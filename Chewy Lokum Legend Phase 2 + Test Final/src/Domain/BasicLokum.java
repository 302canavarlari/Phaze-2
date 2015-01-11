package Domain;






import UserInterface.GameBoardGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class BasicLokum extends Lokum {

	static final int RED=1;
	static final int GREEN=2;
	static final int WHITE=3;
	static final int BROWN=4;

	/*
	 * creates new BasicLokum.
	 */
	public BasicLokum(int lokumType, int r, int c) {
		super(lokumType, r, c);
		type=lokumType;



	}




}
