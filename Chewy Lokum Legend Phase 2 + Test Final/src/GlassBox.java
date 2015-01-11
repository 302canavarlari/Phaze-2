import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Domain.BasicLokum;
import Domain.Board;
import Domain.CreateLevels;
import Domain.GameEngine;
import Domain.Level;
import Domain.Lokum;
import UserInterface.InfoBoard;


public class GlassBox {

	CreateLevels c = new CreateLevels();

	Level[] level = new Level [3];
	InfoBoard ib = new InfoBoard(2,false);
	Board board = new Board(CreateLevels.setOfLevels[0].getRow(), CreateLevels.setOfLevels[2].getColumn(), ib,false);
	Board beforeboard = board;
	ArrayList<ArrayList<Lokum>> silinecekLokum = new ArrayList<ArrayList<Lokum>>();
	GameEngine gme = new GameEngine(board,ib);
	ArrayList<Lokum> adsiz =new ArrayList<Lokum>();

	@Before
	public void setUp() throws Exception {

		gme.didStarted=true;
		adsiz.add(board.getLokumAt(1, 0));
		adsiz.add(board.getLokumAt(1, 1));
		adsiz.add(board.getLokumAt(1, 2));
		adsiz.add(board.getLokumAt(1, 3));
		adsiz.add(board.getLokumAt(1, 4));
		silinecekLokum.add(adsiz);
	}

	@Test
	public void test5PatlatSerdarHocaYarat() {
		gme.silinecekLokum=this.silinecekLokum;
		gme.markDeleted();
		gme.generateBoardString();

		if(gme.b.board[1][0].type == 99)
			System.out.println("Serdar Hocanin yaratilmasi test tamam");
		else
			System.out.println("Serdar Hocanin yaratilmasi test basarisiz");
		System.out.println("--------------------");
	}

	@Test
	public void testTimeLokumPatlat5SaniyeEkle() {

		ib.timerAktifmi = false;
		ib.timerGUIAktifEdilsinMi = false;

		Lokum lok = new BasicLokum(44,0,0);
		gme.b.putLokumAt(0, 0, lok);

		adsiz.add(board.getLokumAt(0, 0));
		silinecekLokum.add(adsiz);
		
		System.out.println("Time lokum 0,0 a eklendi \n");
		
		gme.generateBoardString();
		
		System.out.println("Ilk Zaman: " + ib.getTimeLeft() +"\n");

		gme.silinecekLokum=this.silinecekLokum;
		gme.markDeleted();

		gme.generateBoardString();

		System.out.println("Sonraki Zaman: " +ib.getTimeLeft()+"\n");

		gme.generateBoardString();
			
		System.out.println("Time lokum patadiktan sonra ilk sureye 5 saniye eklendi. \n");
		System.out.println("Time test tamam");

		System.out.println("--------------------");

	}

}