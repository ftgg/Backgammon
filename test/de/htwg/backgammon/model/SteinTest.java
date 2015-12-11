package de.htwg.backgammon.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.Token;

public class SteinTest {

	Token steinB;
	Token steinW;
	Token steinX;
	
	@Before
	public void setUp() throws Exception{
		steinB = new Token(TokenColor.BLACK);
		steinW = new Token(TokenColor.WHITE);
		
		try{
			steinX = new Token(TokenColor.NONE);
		}catch (IllegalArgumentException e){
		}
		
		steinX = new Token(TokenColor.BLACK);
		
	}
	
	@Test
	public void getColorTest(){
		assertSame(TokenColor.BLACK,steinB.getColor());
		assertSame(TokenColor.WHITE,steinW.getColor());
	}
	

}
