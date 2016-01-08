package de.htwg.backgammon.aview;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;


public class Tui implements Observer {
	private GameState gs;
	private Controller contr;
	private TuiSB bStringBuilder = new BackgammonStringBuilder();
	private Scanner sc = new Scanner(System.in).useDelimiter("\\s*\n\\s*");
	private static final Logger LOGGER = LogManager.getLogger(Tui.class.getName());

	public Tui(Controller c) {
		System.out.println("Tui startet");
		contr = c;
		contr.add(this);
	}

	protected Tui() {
	}

	public void printField() {
		print(bStringBuilder.getStringBuilder(gs).toString());
		print(bStringBuilder.getInformations(gs).toString());
	}

	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			GameState old = gs;
			gs = (GameState) e;
			printField();
		}
	}

	public void print(String msg) {
		LOGGER.info("\n" + msg);
	}

	public boolean processInputLine(String next) {
		print(gs.getMessage());
		printField();
		contr.doAction(next);
		return !gs.getGameFinished();
	}
}
