package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Triangle;
import de.htwg.backgammon.model.implementation.Token;

public class DreieckTest {

	
	
	Triangle d;
	@Before
	public void setUp() throws Exception {
		d = new Triangle();
	}

	@Test
	public void add() {
		Token s = new Token(TokenColor.BLACK);
		d.add(s);
		assertSame(s, d.remove());
		assertNull(d.add(new Token(TokenColor.BLACK)));
		assertNull(d.add(new Token(TokenColor.BLACK)));
		assertNotNull(d.add(new Token(TokenColor.WHITE)));
		
		d.clear();
		d.add(new Token(TokenColor.BLACK));
		assertNotNull(d.add(new Token(TokenColor.WHITE)));
		
	}
	
	@Test
	public void remove(){
		d.clear();
		Token s = new Token(TokenColor.WHITE);
		d.add(s);
		assertSame (s,d.remove());
		assertNull(d.remove());
	}
	
	@Test
	public void count(){
		Token s = new Token(TokenColor.WHITE);
		int count = d.count();
		d.add(s);
		assertSame(count + 1 ,d.count());
	}
	
	@Test
	public void getColor(){
		Token s = new Token(TokenColor.WHITE);
		d.clear();
		d.add(s);
		assertSame(TokenColor.WHITE,d.getColor());
		
	}
	
	@Test
	public void clear(){
		d.add(new Token(TokenColor.WHITE));
		d.clear();
		assertSame(0, d.count());
	}
	
	@Test
	public void isEmpty(){
		d.clear();
		assertTrue(d.isEmpty());
		assertSame(0, d.count());
	}
	
	@Test
	public void unsecure(){
		d.clear();
		d.add(new Token(TokenColor.WHITE));
		assertSame(d.count() < 2 , d.unsecure());
	}
	
}
