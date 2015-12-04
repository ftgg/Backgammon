package de.htwg.backgammon.model.implementation;

import java.util.ArrayDeque;
import java.util.Deque;

import de.htwg.backgammon.model.Stein;
import de.htwg.backgammon.model.Triangle;

public class Dreieck implements Triangle{

	private Deque<Stein> content;
	private int size;
	private int color;
	private boolean unsecure = true;

	public Dreieck() {
		content = new ArrayDeque<Stein>();
		size = 0;
		color = NONE;
	}

	public Stein add(Stein s) {
		// Unsicherers Feld und anderer Stein.
		Stein old;
		if (unsecure() && s.getColor() != color && !content.isEmpty()) {
			old = content.pop();
			content.push(s);
			color = s.getColor();
			return old;
		}

		// Sicher und Fremdes Feld
		if (!unsecure() && s.getColor() != color)
			return s;

		// Sicher und Eigenes Feld | freies Feld
		content.push(s);
		color = s.getColor();
		size++;
		if (size >= 2)
			unsecure = false;
		return null;
	}

	public boolean unsecure() {
		return unsecure;
	}

	public Stein remove() {
		if (content.isEmpty())
			return null;

		Stein r = content.pop();
		size--;
		if (size < 2)
			unsecure = true;
		if (content.isEmpty())
			color = NONE;
		return r;

	}

	public int count() {
		return size;
	}

	public int getColor() {
		return color;
	}

	public boolean isEmpty() {
		return content.isEmpty();
	}

	public void clear() {
		size = 0;
		unsecure = true;
		content.clear();
	}

}
