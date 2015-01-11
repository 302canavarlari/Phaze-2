import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import UserInterface.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import UserInterface.InfoBoard;
import Domain.*;

public class BlackBox {
	CreateLevels c = new CreateLevels();
	
	Level[] level = new Level [3];
	InfoBoard ib = new InfoBoard(1,false);
	Board board = new Board(CreateLevels.setOfLevels[1].getRow(), CreateLevels.setOfLevels[1].getColumn(), ib,false);
	Board beforeboard = board;
	ArrayList<ArrayList<Lokum>> silinecekLokum = new ArrayList<ArrayList<Lokum>>();
	GameEngine gme = new GameEngine(board,ib);
	ArrayList<Lokum> adsiz =new ArrayList<Lokum>();
	
	
	
	@Before
	public void setUp() throws Exception {
		gme.didStarted=true;
		adsiz.add(board.getLokumAt(1, 1));
		adsiz.add(board.getLokumAt(1, 2));
		adsiz.add(board.getLokumAt(1, 3));
		silinecekLokum.add(adsiz);
		

	}

	@Test
	public void testMarkDeletedBos() {
		gme.markDeleted();
		gme.generateBoardString();
		
		System.out.println("Silinecek lokum arrayi bos oldugu icin board hic degismicek  \n");
		System.out.println("Bos markDeleted test tamam");
		System.out.println("--------------------");
	}
	
	@Test
	public void testMarkDeletedDolu() {
		System.out.println(silinecekLokum.get(0).get(0).type+" marked as deleted");
		System.out.println(silinecekLokum.get(0).get(1).type+" marked as deleted");
		System.out.println(silinecekLokum.get(0).get(2).type+" marked as deleted \n");
		
		gme.silinecekLokum=this.silinecekLokum;
		gme.markDeleted();
		gme.generateBoardString();
		
		System.out.println("1,1 1,2 ve 1,3 lokumlarini sil dedik o yuzden bu lokumlar 0 olarak isaretlendi. \n");

		System.out.println("Dolu markDeleted test tamam");
		System.out.println("--------------------");

	}
	
	@Test
	public void testSerdarinGucu(){
		gme.markDeleted(2);
		gme.generateBoardString();
		System.out.println("Serdar Hoca test tamam \n");
		System.out.println("Bu test sonucunda boarddaki Typei 2 olan bŸtŸn lokumlar gidiyor. Ancak 2ler silindikten sonra");
		System.out.println("yeni olusan yerlerde random bir sekilde 2ler geliyor ve bu da dogru davranis.");
		System.out.println("--------------------");

	}
	
	@Test
	public void testPutLokumAt() {
		Lokum lok = new BasicLokum(44,0,0);
		gme.b.putLokumAt(0, 0, lok);
		
		Lokum lok2 = new BasicLokum(99,1,1);
		gme.b.putLokumAt(1, 1, lok2);
		
		Lokum lok3 = new BasicLokum(22,2,2);
		gme.b.putLokumAt(2, 2, lok3);
		
		gme.generateBoardString();
		
		System.out.println("Bu test sonucunda");
		System.out.println("0,0 -> 44,");
		System.out.println("1,1 -> 99,");
		System.out.println("2,2 -> 22, ");
		System.out.println("nolu lokum koyuldu. \n");
		
		System.out.println("Put Lokum At test tamam");
		System.out.println("--------------------");
		

	}
	
}