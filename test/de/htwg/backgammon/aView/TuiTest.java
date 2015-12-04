package de.htwg.backgammon.aView;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.util.Subject;

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