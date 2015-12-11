package de.htwg.backgammon.model.implementation;

import java.util.ArrayDeque;
import java.util.Deque;

import de.htwg.backgammon.model.IToken;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.ITriangle;

public class Triangle implements ITriangle {

	private Deque<IToken> content;
	private int size;
	private TokenColor color;
	private boolean unsecure = true;

	public Triangle() {
		content = new ArrayDeque<IToken>();
		size = 0;
		color = TokenColor.NONE;
	}

	@Override
	public IToken add(IToken s) {
		// Unsicherers Feld und anderer Stein.
		IToken old;
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
	public IToken remove() {
		if (content.isEmpty())
			return null;

		IToken r = content.pop();
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
