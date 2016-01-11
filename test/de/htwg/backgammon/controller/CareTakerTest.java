package de.htwg.backgammon.controller;

import static org.junit.Assert.*;

import java.util.ArrayDeque;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.model.implementation.GameState;

public class CareTakerTest {
	Caretaker ct;
	@Before
	public void setUp() throws Exception {
		ct = new Caretaker();
	}
	
	@Test
	public void readLastStateTest(){
		GameState gs = GameState.getDefaultGameState();
		ct.addState(new Memento(gs));
		ct.addState(new Memento(gs));
		Memento m = ct.getLastState();
		assertSame(gs, m.getGameState());
		
		m = ct.getLastState();
		assertSame(gs, m.getGameState());
	}
	
	@Test
	public void getStackTest(){
		GameState gs = GameState.getDefaultGameState();
		ct.addState(new Memento(gs));
		ArrayDeque<Memento> m = ct.getStack();
		assertSame(gs, m.getLast().getGameState());
	}

	@Test
	public void getIterator(){
		GameState gs = GameState.getDefaultGameState();
		ct.addState(new Memento(gs));
		assertSame(ct.getStack().iterator().next(),ct.iterator().next());
	}
	
	@Test
	public void ConstructorTest(){
		GameState gs = GameState.getDefaultGameState();
		ct.addState(new Memento(gs));
		Caretaker c = new Caretaker(ct.getStack());
		assertSame(c.getStack(),ct.getStack());
	}


}
