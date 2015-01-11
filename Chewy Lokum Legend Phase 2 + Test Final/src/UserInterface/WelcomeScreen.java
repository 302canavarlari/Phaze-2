package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Domain.*;

/**
 * This class creates a the welcome screen of the game.
 */

public class WelcomeScreen implements ActionListener{

	int selected;
	private JButton l1;
	private JButton l2;
	private JButton l3;
	private JButton l4;
	private JButton l5;
	private JButton cont;
	public static ImageLibrary imageLibrary = new ImageLibrary();
    static public boolean startFromSave;
    int[] la;
    String LoadOption;
	
	JOptionPane optionPane;
	SaveFileGenerator sg = new SaveFileGenerator();
	

	public WelcomeScreen(){
		
		saveScreen();
		
		startFromSave = false;
		l1 = new JButton("Level 1");
		l2 = new JButton("Level 2");
		l3 = new JButton("Level 3");
		l4 = new JButton("Level 4");
		l5 = new JButton("Level 5");
		
		cont = new JButton("Continue Game");

		Object complexMsg[] = { 
				"Please Select a Level",
				l1,
				l2,
				l3,
				l4,
				l5,
				"Continue From Your Save",
				cont,
		"Press OK to Start a New Game " };
		
		if(la[0] == 0) {l1.setEnabled(false);} else { l1.setEnabled(true);}
		if(la[1] == 0) {l2.setEnabled(false);} else { l2.setEnabled(true);}
		if(la[2] == 0) {l3.setEnabled(false);} else { l3.setEnabled(true);}
		if(la[3] == 0) {l4.setEnabled(false);} else { l4.setEnabled(true);}
		if(la[4] == 0) {l5.setEnabled(false);} else { l5.setEnabled(true);}
		
		l1.addActionListener(this);
		l2.addActionListener(this);
		l3.addActionListener(this);
		l4.addActionListener(this);
		l5.addActionListener(this);
		cont.addActionListener(this);
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("Images/mostSpecial.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(bufferedImage);
	
		optionPane = new JOptionPane();
		optionPane.setMessage(complexMsg);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		optionPane.setIcon(imageIcon);
		JDialog dialog = optionPane.createDialog(null, "Welcome to Chewy Lokum Legend");
		dialog.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(l1)) {
			selected = 0;
			optionPane.getRootFrame().dispose();
		}
		if (e.getSource().equals(l2)) {
			selected = 1;
			optionPane.getRootFrame().dispose();
		}
		if (e.getSource().equals(l3)) {
			selected = 2;
			optionPane.getRootFrame().dispose();
		}
		if (e.getSource().equals(l4)) {
			selected = 3;
			optionPane.getRootFrame().dispose();
		}
		if (e.getSource().equals(l5)) {
			selected = 4;
			optionPane.getRootFrame().dispose();
		}
		if (e.getSource().equals(cont)) {
			startFromSave = true;
			selected = sg.currentLevel;
			optionPane.getRootFrame().dispose();
		}
		
	}

	/**
	 * Return index of the selected level
	 * 
	 * @returns index of the selected level
	 */

	public int getLevelIndex() {
		return selected;
	}

	public boolean getStartFromSave() {
		return startFromSave;
	}
	
	public void saveScreen(){
		
		LoadOption = null;
		
		JFrame frame = new JFrame("Load Game");
	    File LoadOpt = (File) JOptionPane.showInputDialog(frame, 
	        "Do you want to Load from save file?",
	        "Load Game",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        sg.getSaveFiles(), 
	        sg.getSaveFiles()[0]);

		if (LoadOpt != null) LoadOption = LoadOpt.toString();
	    
	    if ((LoadOption != null) && (LoadOption.length() > 0)) {
	    	sg.read(LoadOption);
	    	la = sg.levelArray;
			System.out.println(LoadOption);
		} else { 
			int[] le = {1,0,0,0,0};
			la = le;
		}

	}
	
	public String getLoadOptionName() {
		
		return LoadOption;
	}


}