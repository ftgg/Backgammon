package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.Stein;

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
	

}
