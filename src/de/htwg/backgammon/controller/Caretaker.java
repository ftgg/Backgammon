package de.htwg.backgammon.controller;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Deque;

public class Caretaker implements Iterable<Memento>{
	private Deque<Memento> queue;
	public Caretaker(){
		queue = new ArrayDeque<Memento>();
	}
	
	public Caretaker(Deque<Memento> queue){
		this.queue = queue;
	}
	
	public void addState(Memento m){
		queue.addLast(m);
	}
	
	public Memento getLastState(){
		if(queue.size() == 1){
			return queue.peekLast();
		}
		return queue.removeLast();
	}
	
	
	public Deque<Memento> getStack(){
		return queue;
	}

	@Override
	public Iterator<Memento> iterator() {
		return queue.iterator();
	}
}
