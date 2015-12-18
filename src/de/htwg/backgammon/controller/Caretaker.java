package de.htwg.backgammon.controller;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Caretaker implements Iterable<Memento>{
	private Deque<Memento> queue;
	public Caretaker(){
		queue = new ArrayDeque<Memento>();
	}
	
	public void addState(Memento m){
		queue.addLast(m);
	}
	
	public Memento getState(){
		return queue.removeLast();
	}

	@Override
	public Iterator<Memento> iterator() {
		return queue.iterator();
	}
}
