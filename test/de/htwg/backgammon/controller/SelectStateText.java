package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SelectStateText {

	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void SelectTest() {
		SelectState s = new SelectState(4,false,1);
		assertEquals(4,s.getIndex());
		assertFalse(s.isTop());
		assertEquals(1,s.getSelect());
	}

}
