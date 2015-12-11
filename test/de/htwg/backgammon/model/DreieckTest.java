package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Dreieck;
import de.htwg.backgammon.model.implementation.Stein;

public class DreieckTest {

	
	
	Dreieck d;
	@Before
	public void setUp() throws Exception {
		d = new Dreieck();
	}

	@Test
	public void add() {
		Stein s = new Stein(TokenColor.BLACK);
		d.add(s);
		assertSame(s, d.remove());
		assertNull(d.add(new Stein(TokenColor.BLACK)));
		assertNull(d.add(new Stein(TokenColor.BLACK)));
		assertNotNull(d.add(new Stein(TokenColor.WHITE)));
		
		d.clear();
		d.add(new Stein(TokenColor.BLACK));
		assertNotNull(d.add(new Stein(TokenColor.WHITE)));
		
	}
	
	@Test
	public void remove(){
		d.clear();
		Stein s = new Stein(TokenColor.WHITE);
		d.add(s);
		assertSame (s,d.remove());
		assertNull(d.remove());
	}
	
	@Test
	public void count(){
		Stein s = new Stein(TokenColor.WHITE);
		int count = d.count();
		d.add(s);
		assertSame(count + 1 ,d.count());
	}
	
	@Test
	public void getColor(){
		Stein s = new Stein(TokenColor.WHITE);
		d.clear();
		d.add(s);
		assertSame(TokenColor.WHITE,d.getColor());
		
	}
	
	@Test
	public void clear(){
		d.add(new Stein(TokenColor.WHITE));
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
		d.add(new Stein(TokenColor.WHITE));
		assertTrue(d.count() < 2 == d.unsecure());
	}
	
}
