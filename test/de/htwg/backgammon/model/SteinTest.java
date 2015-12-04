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
		steinB = new Stein(Stein.getBlack());
		steinW = new Stein(Stein.getWhite());
		
		try{
			steinX = new Stein(-1);
		}catch (IllegalArgumentException e){
		}
		
		steinX = new Stein(Stein.getBlack());
		
	}
	
	@Test
	public void getColorTest(){
		assertSame(Stein.getBlack(),steinB.getColor());
		assertSame(Stein.getWhite(),steinW.getColor());
	}
	

}
