package UserInterface;

import javax.imageio.*;

import Domain.Lokum;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageLibrary {
	private HashMap<Integer,BufferedImage> imageCollection;
	public ImageLibrary() {
		imageCollection = new HashMap(100);
		BufferedImage img;
		try {
			img = ImageIO.read(new File("Images/focus.png"));
			imageCollection.put(0, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/mostSpecial.png"));
			imageCollection.put(99, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/ismailAbi.png"));
			imageCollection.put(77, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/red.png"));
			imageCollection.put(1, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/green.png"));
			imageCollection.put(2, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/white.png"));
			imageCollection.put(3, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/brown.png"));
			imageCollection.put(4, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/redSpecial.png"));
			imageCollection.put(11, img);
		} catch (IOException e) {System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/greenSpecial.png"));
			imageCollection.put(12, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/whiteSpecial.png"));
			imageCollection.put(13, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/brownSpecial.png"));
			imageCollection.put(14, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/redSpecial2.png"));
			imageCollection.put(21, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/greenSpecial2.png"));
			imageCollection.put(22, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/whiteSpecial2.png"));
			imageCollection.put(23, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/brownSpecial2.png"));
			imageCollection.put(24, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/whiteTime.png"));
			imageCollection.put(43, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/redTime.png"));
			imageCollection.put(41, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/greenTime.png"));
			imageCollection.put(42, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/brownTime.png"));
			imageCollection.put(44, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/skull.png"));
			imageCollection.put(66, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }
		try {
			img = ImageIO.read(new File("Images/smile.png"));
			imageCollection.put(65, img);
		} catch (IOException e) { System.out.println(e.getMessage()); }

	}
	public Image getImage(int type) {
		return imageCollection.get(type);
	}

}