package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	Controller c;

	@Before
	public void setUp() throws Exception {
		c = new Controller();
	}

	@Test
	public void wuerfeln() {
		
		for(int j = 0; j < 36;j++){
		int[] erg = c.getZuege();
		int[] cur = c.getWuerfelC();
		for (int i = 0; i < 2; i++) {
			assertEquals(cur[i], erg[i]);
		}
		
		if(cur[0] == cur[1]){
			assertEquals(erg[2],cur[0]);
			assertEquals(erg[3],cur[1]);
		}else{
			assertEquals(erg[2],0);
			assertEquals(erg[3],0);
		}
		
		}
		

	}
	
	@Test
	public void testParseAction(){
		assertEquals(new int[]{3,5},c.parseAction("3 5"));
		assertEquals(new int[]{20,-1},c.parseAction("20 h"));
		assertEquals(new int[]{-2,5},c.parseAction("b 5"));
	}
	

}
