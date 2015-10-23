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
	}
	
	@Test
	public void remove(){
		d.clear();
		Stein s = new Stein(Stein.White);
		d.add(s);
		assertSame (s,d.remove());
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
		assertNotSame(0,d.clear().size());
	}
	
	@Test
	public void isEmpty(){
		d.clear();
		assertTrue(d.isEmpty());
	}
	
	@Test
	public void unsecure(){
		d.clear();
		d.add(new Stein(Stein.White));
		d.add(new Stein(Stein.White));
		assertTrue(d.isEmpty());
	}
	
}
