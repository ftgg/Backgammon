package de.htwg.backgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SteinTest {

	Stein steinB;
	Stein steinW;
	Stein steinX;
	
	@Before
	public void setUp() throws Exception{
		steinB = new Stein(Stein.Black);
		steinW = new Stein(Stein.White);
		
		try{
			steinX = new Stein(-1);
		}catch (IllegalArgumentException e){
		}
		
		steinX = new Stein(Stein.Black);
		
	}
	
	@Test
	public void getColorTest(){
		assertSame(Stein.Black,steinB.getColor());
		assertSame(Stein.White,steinW.getColor());
	}
	
	@Test
	public void setState(){
		steinB.setState(0);
		assertSame(0, steinB.getState());
		
		try{
			steinX.setState(-1);
		}catch (IllegalArgumentException e){
		}
		
	}
	
	@Test
	public void getState(){
		steinB.setState(1);
		assertSame(1, steinB.getState());
	}

}
