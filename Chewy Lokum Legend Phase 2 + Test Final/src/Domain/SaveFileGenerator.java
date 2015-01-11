package Domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
 
public class SaveFileGenerator {
	
	public static int currentScore ;
	public static int currentLevel;
	public static int moveLeft;
	public static int currentSpecialMove;
	public static int boardRowNo;
	public static int boardColNo;
	public static String playerName;
	public static String boardStatus;

	public static int[] levelArray = {1,0,0,0,0};
	public static File[] listOfFiles;
	
	
	public SaveFileGenerator() {
		listOfFiles =  getXMLFiles(new File("SaveFiles"));

	}
	
	public static File[] getSaveFiles(){
		return listOfFiles;
	}
 
	public static void generateSaveFile(int cs, int cl ,int ml, String board, int bRowNo,int bColNo, int[] levels,String pName) {
		
		currentScore = cs;
		currentLevel = cl;
		moveLeft = ml;
		boardStatus = board;
		boardRowNo = bRowNo;
		boardColNo = bColNo;
		levelArray = levels;
		playerName = pName;

	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Game");
		doc.appendChild(rootElement);
 
		Element gameState = doc.createElement("GameState");
		rootElement.appendChild(gameState);
		
		Element pNam = doc.createElement("playerName");
		pNam.appendChild(doc.createTextNode(playerName));
		gameState.appendChild(pNam);
 
		Element Cscore = doc.createElement("currentScore");
		Cscore.appendChild(doc.createTextNode(Integer.toString(currentScore)));
		gameState.appendChild(Cscore);
		
		Element CLevel = doc.createElement("currentLevel");
		CLevel.appendChild(doc.createTextNode(Integer.toString(currentLevel)));
		gameState.appendChild(CLevel);
		
		Element moveL = doc.createElement("moveLeft");
		moveL.appendChild(doc.createTextNode(Integer.toString(moveLeft)));
		gameState.appendChild(moveL);
		
		Element boardState = doc.createElement("boardState");
		boardState.appendChild(doc.createTextNode(boardStatus));
		gameState.appendChild(boardState);
		
		Element boardRN = doc.createElement("boardRowNo");
		boardRN.appendChild(doc.createTextNode(Integer.toString(boardRowNo)));
		gameState.appendChild(boardRN);
		
		Element boardCN = doc.createElement("boardColNo");
		boardCN.appendChild(doc.createTextNode(Integer.toString(boardColNo)));
		gameState.appendChild(boardCN);
		
		Element levelState = doc.createElement("LevelUnlockState");
		rootElement.appendChild(levelState);
		
		Element level1 = doc.createElement("level1");
		level1.appendChild(doc.createTextNode(Integer.toString(levelArray[0])));
		levelState.appendChild(level1);
		
		Element level2 = doc.createElement("level2");
		level2.appendChild(doc.createTextNode(Integer.toString(levelArray[1])));
		levelState.appendChild(level2);
		
		Element level3 = doc.createElement("level3");
		level3.appendChild(doc.createTextNode(Integer.toString(levelArray[2])));
		levelState.appendChild(level3);
		
		Element level4 = doc.createElement("level4");
		level4.appendChild(doc.createTextNode(Integer.toString(levelArray[3])));
		levelState.appendChild(level4);
		
		Element level5 = doc.createElement("level5");
		level5.appendChild(doc.createTextNode(Integer.toString(levelArray[4])));
		levelState.appendChild(level5);
		
		
		String SaveDestination = "SaveFiles/" + playerName +".xml";
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(SaveDestination));
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
	


	public static void read(String ReadDestination) {
		
		try {
			 
			File fXmlFile = new File(ReadDestination);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
		 
			NodeList GameStateList = doc.getElementsByTagName("GameState");
		 
		
			for (int temp = 0; temp < GameStateList.getLength(); temp++) {
		 
				org.w3c.dom.Node GameStateNode = GameStateList.item(temp);
		 		 
				if (GameStateNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) GameStateNode;
					
					currentScore = Integer.parseInt(eElement.getElementsByTagName("currentScore").item(0).getTextContent());
					currentLevel = Integer.parseInt(eElement.getElementsByTagName("currentLevel").item(0).getTextContent());
					moveLeft = Integer.parseInt(eElement.getElementsByTagName("moveLeft").item(0).getTextContent());
					boardStatus = eElement.getElementsByTagName("boardState").item(0).getTextContent();
					boardRowNo = Integer.parseInt(eElement.getElementsByTagName("boardRowNo").item(0).getTextContent());
					boardColNo = Integer.parseInt(eElement.getElementsByTagName("boardColNo").item(0).getTextContent());
					
		 
				}
				
				
			}
			
			NodeList LevelList = doc.getElementsByTagName("LevelUnlockState");
			 
			
			for (int temp2 = 0; temp2 < LevelList.getLength(); temp2++) {
				 
				org.w3c.dom.Node LevelNode = LevelList.item(temp2);
		 
		 
				if (LevelNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) LevelNode;
					
					int[] ll = {
							Integer.parseInt(eElement.getElementsByTagName("level1").item(0).getTextContent()),
							Integer.parseInt(eElement.getElementsByTagName("level2").item(0).getTextContent()),
							Integer.parseInt(eElement.getElementsByTagName("level3").item(0).getTextContent()),
							Integer.parseInt(eElement.getElementsByTagName("level4").item(0).getTextContent()),
							Integer.parseInt(eElement.getElementsByTagName("level5").item(0).getTextContent()),
						
					};
					
					levelArray = ll;
					
					printStatus();
					
	 
				}

			}
			
		    } catch (Exception e) {
		    String s = "";
			int[] a = {1,0,0,0,0};
			generateSaveFile(0,0,20,"1 3 4 4 3 4 4 1 4 4 1 4 1 4 3 2 2 3 4 1 2 2 3 3 1 3 3 1 1 4 4 1 2 2 3 1",6,6,a,"Default");
		    }
		
	}
	
	public static File[] getXMLFiles(File folder) {
	    ArrayList<File> aList = new ArrayList<File>();

	    File[] files = folder.listFiles();
	    for (File pf : files) {

	      if (pf.isFile() && getFileExtensionName(pf).indexOf("xml") != -1) {
	        aList.add(pf);
	      }
	    }
	    return aList.toArray(new File[aList.size()]);
	  }

	  public static String getFileExtensionName(File f) {
	    if (f.getName().indexOf(".") == -1) {
	      return "";
	    } else {
	      return f.getName().substring(f.getName().length() - 3, f.getName().length());
	    }
	  }
	
	public static void printStatus(){
		
		System.out.println("-------------Level Unlock Status---------------");
		System.out.println(Arrays.toString(levelArray));
		
		System.out.println("-------------Current State Of The Game---------------");
		System.out.println("PlayerName : " + playerName);
		System.out.println("currentScore : " + currentScore);
		System.out.println("currentLevel : " + currentLevel);
		System.out.println("moveLeft : " + moveLeft);
		System.out.println("boardRowNo : " + boardRowNo);
		System.out.println("boardColwNo : " + boardColNo);
		System.out.println("boardState : " + boardStatus);

	}
	
}