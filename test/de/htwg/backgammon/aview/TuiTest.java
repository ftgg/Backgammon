package de.htwg.backgammon.aview;



import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.aview.Tui;


public class TuiTest {

	Tui tui;
	
	@Before
	public void setUp() throws Exception{
		tui = new Tui();
	}
	
	@Test
	public void testprintField(){
		//tui.printField();
	}
	
	@Test
	public void testprint(){
		tui.print("");
	}
	

}