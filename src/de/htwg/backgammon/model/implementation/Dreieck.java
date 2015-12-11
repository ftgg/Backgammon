package de.htwg.backgammon.model.implementation;

import java.util.ArrayDeque;
import java.util.Deque;

import de.htwg.backgammon.model.Token;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.Triangle;

public class Dreieck implements Triangle {

	private Deque<Token> content;
	private int size;
	private TokenColor color;
	private boolean unsecure = true;

	public Dreieck() {
		content = new ArrayDeque<Token>();
		size = 0;
		color = TokenColor.NONE;
	}

	@Override
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

	@Override
	public boolean unsecure() {
		return unsecure;
	}

	@Override
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

	@Override
	public int count() {
		return size;
	}

	@Override
	public TokenColor getColor() {
		return color;
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}

	@Override
	public void clear() {
		size = 0;
		unsecure = true;
		content.clear();
	}

}
