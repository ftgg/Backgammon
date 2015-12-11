package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Stein;

public class SteinTest {

	Stein steinB;
	Stein steinW;
	Stein steinX;
	
	@Before
	public void setUp() throws Exception{
		steinB = new Stein(TokenColor.BLACK);
		steinW = new Stein(TokenColor.WHITE);
		
		try{
			steinX = new Stein(TokenColor.NONE);
		}catch (IllegalArgumentException e){
		}
		
		steinX = new Stein(TokenColor.BLACK);
		
	}
	
	@Test
	public void getColorTest(){
		assertSame(TokenColor.BLACK,steinB.getColor());
		assertSame(TokenColor.WHITE,steinW.getColor());
	}
	

}
