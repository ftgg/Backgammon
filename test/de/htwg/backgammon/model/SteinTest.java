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
		steinB = new Stein(Stein.BLACK);
		steinW = new Stein(Stein.WHITE);
		
		try{
			steinX = new Stein(-1);
		}catch (IllegalArgumentException e){
		}
		
		steinX = new Stein(Stein.BLACK);
		
	}
	
	@Test
	public void getColorTest(){
		assertSame(Stein.BLACK,steinB.getColor());
		assertSame(Stein.WHITE,steinW.getColor());
	}
	

}
