package de.htwg.backgammon.controller;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Caretaker implements Iterable<Memento>{
	private ArrayDeque<Memento> queue;
	public Caretaker(){
		queue = new ArrayDeque<Memento>();
	}
	
	public Caretaker(ArrayDeque<Memento> queue){
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
	
	public Memento readLastState(){
		return queue.pollLast();
	}
	
	public ArrayDeque<Memento> getStack(){
		return queue;
	}

	@Override
	public Iterator<Memento> iterator() {
		return queue.iterator();
	}
}
