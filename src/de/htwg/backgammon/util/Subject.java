package de.htwg.backgammon.util;

import java.util.HashSet;
import java.util.Set;

public abstract class Subject {

	private Set<Observer> observers;

	public Subject() {
		observers = new HashSet<Observer>();
	}

	public Set<Observer> getObs() {
		return observers;
	}

	public void notifyObs(Event e) {
		for (Observer o : observers)
			o.update(e);
	}

	public void add(Observer o) {
		observers.add(o);
	}

	public void remove(Observer o) {
		observers.remove(o);
	}
}
