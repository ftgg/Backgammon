package de.htwg.backgammon.model.implementation;

import java.util.ArrayDeque;
import java.util.Deque;


import de.htwg.backgammon.model.TokenColor;


public class Triangle {

	private Deque<Token> content;
	private int size;
	private TokenColor color;
	private boolean unsecure = true;

	public Triangle() {
		content = new ArrayDeque<Token>();
		size = 0;
		color = TokenColor.NONE;
	}

	
	public Token add(Token s) {
		// Unsicherers Feld und anderer Stein.
		Token old;
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

	
	public Token remove() {
		if (content.isEmpty())
			return null;

		Token r = content.pop();
		size--;
		if (size < 2)
			unsecure = true;
		if (content.isEmpty())
			color = TokenColor.NONE;
		return r;

	}

	
	public int count() {
		return size;
	}

	
	public TokenColor getColor() {
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
