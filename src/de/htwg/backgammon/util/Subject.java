package de.htwg.backgammon.util;
import java.util.Set;
import java.util.TreeSet;

public abstract class Subject {
	
	Set<Observer> observers;
	
	Subject(){
		observers = new TreeSet<Observer>();
	}
	
	public void notifyObs(Event e){
		for(Observer o: observers)
			o.update(e);
	}
	
	public void add(Observer o){
		observers.add(o);
	}
	
	public void remove(Observer o){
		observers.remove(o);
	}
}
