package de.htwg.backgammon.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.util.Subject;

public class SubjectTest {

	Subject s;
	Observer o;
	
	@Before
	public void setUp() throws Exception{
		s = new SubTest();
		o = new ObsTest();
	}
	
	@Test
	public void getObsTest(){
		assertNotNull(s.getObs());
		s.add(o);
		assertTrue(s.getObs().contains(o));
		s.remove(o);
		assertFalse(s.getObs().contains(o));
	}
	

}