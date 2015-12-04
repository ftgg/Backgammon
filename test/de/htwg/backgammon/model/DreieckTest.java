package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Dreieck;
import de.htwg.backgammon.model.Stein;

public class DreieckTest {

	
	
	Dreieck d;
	@Before
	public void setUp() throws Exception {
		d = new Dreieck();
	}

	@Test
	public void add() {
		Stein s = new Stein(Stein.BLACK);
		d.add(s);
		assertSame(s, d.remove());
		assertNull(d.add(new Stein(Stein.BLACK)));
		assertNull(d.add(new Stein(Stein.BLACK)));
		assertNotNull(d.add(new Stein(Stein.WHITE)));
		
		d.clear();
		d.add(new Stein(Stein.BLACK));
		assertNotNull(d.add(new Stein(Stein.WHITE)));
		
	}
	
	@Test
	public void remove(){
		d.clear();
		Stein s = new Stein(Stein.WHITE);
		d.add(s);
		assertSame (s,d.remove());
		assertNull(d.remove());
	}
	
	@Test
	public void count(){
		Stein s = new Stein(Stein.WHITE);
		int count = d.count();
		d.add(s);
		assertSame(count + 1 ,d.count());
	}
	
	@Test
	public void getColor(){
		Stein s = new Stein(Stein.WHITE);
		d.clear();
		d.add(s);
		assertSame(Stein.WHITE,d.getColor());
		
	}
	
	@Test
	public void clear(){
		d.add(new Stein(Stein.WHITE));
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
		d.add(new Stein(Stein.WHITE));
		assertTrue(d.count() < 2 == d.unsecure());
	}
	
}
