package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DreieckTest {

	
	
	Dreieck d;
	@Before
	public void setUp() throws Exception {
		d = new Dreieck();
	}

	@Test
	public void add() {
		Stein s = new Stein(Stein.Black);
		d.add(s);
		assertSame(s, d.remove());
		assertNull(d.add(new Stein(Stein.Black)));
		assertNull(d.add(new Stein(Stein.Black)));
		assertNotNull(d.add(new Stein(Stein.White)));
		
		d.clear();
		d.add(new Stein(Stein.Black));
		assertNotNull(d.add(new Stein(Stein.White)));
		
	}
	
	@Test
	public void remove(){
		d.clear();
		Stein s = new Stein(Stein.White);
		d.add(s);
		assertSame (s,d.remove());
		assertNull(d.remove());
	}
	
	@Test
	public void count(){
		Stein s = new Stein(Stein.White);
		int count = d.count();
		d.add(s);
		assertSame(count + 1 ,d.count());
	}
	
	@Test
	public void getColor(){
		Stein s = new Stein(Stein.White);
		d.clear();
		d.add(s);
		assertSame(Stein.White,d.getColor());
		
	}
	
	@Test
	public void clear(){
		d.add(new Stein(Stein.White));
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
		d.add(new Stein(Stein.White));
		assertTrue(d.count() < 2 == d.unsecure());
	}
	
}
