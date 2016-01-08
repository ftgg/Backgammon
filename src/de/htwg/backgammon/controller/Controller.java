package de.htwg.backgammon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.Iterator;

import javax.swing.Timer;

import com.google.inject.Inject;

import de.htwg.backgammon.model.IDice;
import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.IPlayer;
import de.htwg.backgammon.model.TokenColor;
import de.htwg.backgammon.model.implementation.Pitch;
import de.htwg.backgammon.model.implementation.Player;
import de.htwg.backgammon.model.implementation.Dice;
import de.htwg.backgammon.model.implementation.GameState;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	public static final int NEXT = -4;
	private IPlayer s1;
	private IPlayer s2;
	private IPlayer current;
	private IPitch sf;
	private IDice w;
	private int[] zuege = { 0, 0, 0, 0 };
	private MoveVerifier moveVerifier;
	private ActionParser actionparser;
	private Caretaker states;
	private int lastclick = -1;
	
	@Inject
	public Controller(IPitch p, IDice d, IPlayer s1, IPlayer s2) {
		sf = p;
		w = d;
		this.s1 = s1;
		this.s2 = s2;

		createMoveVerifier();
		CreateMemento();
		actionparser = new ActionParser();
		wuerfeln();
		current = s1;
	}
	
	
	Controller(int i) {
		sf = new Pitch(GameState.getTestGameState(i));
		w = new Dice();
		s1 = new Player("Frau Weiss", TokenColor.WHITE);
		s2 = new Player("Herr Schwarz", TokenColor.BLACK);
		createMoveVerifier();
		actionparser = new ActionParser();
		wuerfeln();
		current = s1;
		CreateMemento();
	}


	
	public void start(){
		GameState gs = new GameState(sf, zuege, "Spiel Beginnt", current, false, s1, s2);
		SetMemento(gs);
		notifyObs(gs);
	}

	public void doAction(String s) {
		int[] act = actionparser.parse(s);
		GameState gs;
		if (act[0] == -3 || act[1] == -3) {
			gs = new GameState(sf, zuege, "Fehlerhafte Eingabe!", current, false, s1, s2);
			notifyObs(gs);
			return;
		} else if (act[0] == NEXT) {
			spielerwechsel();
			gs = new GameState(sf, zuege, "SpielerWechsel", current, false, s1, s2);
			SetMemento(gs);
			notifyObs(gs);
			return;
		}
		spielZug(act[0], act[1]);
	}

	public void wuerfeln() {
		w.rollTheDice();
		if (w.isDoublets()) {
			zuege[0] = w.getCurrentCubeNumbers()[0];
			zuege[1] = w.getCurrentCubeNumbers()[1];
			zuege[2] = w.getCurrentCubeNumbers()[0];
			zuege[3] = w.getCurrentCubeNumbers()[1];
		} else {
			zuege[0] = w.getCurrentCubeNumbers()[0];
			zuege[1] = w.getCurrentCubeNumbers()[1];
			zuege[2] = 0;
			zuege[3] = 0;
		}
	}

	public int spielZug(int a, int b) {
		boolean win = false;
		GameState gs;
		String msg = "";
		if (!moveVerifier.checkMove(a, b, zuege, sf, current, s1, s2)) {
			gs = new GameState(sf, zuege, "Nicht möglicher Zug!", current, false, s1, s2);
			notifyObs(gs);
			return -3;
		}
		int result = sf.move(a, b, current);
		if (result == 111) {
			msg = current.getName() + " hat gewonnen!";
			win = true;
		}

		if (zuegeEmpty())
			spielerwechsel();
		gs = new GameState(sf, zuege, msg, current, win, s1, s2);
		SetMemento(gs);
		notifyObs(gs);
		return result;
	}

	public boolean zuegeEmpty() {
		for (int c : zuege) {
			if (c != 0)
				return false;
		}
		return true;
	}

	public void spielerwechsel() {
		if (current == s1)
			current = s2;
		else
			current = s1;
		wuerfeln();
	}

	private void createMoveVerifier() {
		BarVerifier bv = new BarVerifier();
		;
		DiceResultVerifier drv = new DiceResultVerifier();
		ExitMoveVerifier emv = new ExitMoveVerifier();
		TargetColorVerifier tcv = new TargetColorVerifier();
		DirectionVerifier dv = new DirectionVerifier();
		moveVerifier = drv;
		drv.successor = bv;
		bv.successor = emv;
		emv.successor = tcv;
		tcv.successor = dv;
	}

	/**
	 * NUR für tests, nicht zum gebrauch gedacht =)
	 * 
	 * @return Wuerfelergebnis
	 */
	public int[] getWuerfelC() {
		return w.getCurrentCubeNumbers();
	}

	public int[] getZuege() {
		return zuege;
	}

	public IPlayer[] getSpieler() {
		return new IPlayer[] { s1, s2 };
	}

	public IPlayer getCurrent() {
		return current;
	}

	private void CreateMemento() {
		states = new Caretaker();
	}

	private void SetMemento(GameState gs) {
		states.addState(new Memento(gs));
	}

	public void undo() {
		GameState gs = states.getLastState().getGameState();
		loadGameState(gs);
	}

	private void loadGameState(GameState gs) {
		this.sf = new Pitch(gs);
		this.zuege = gs.getZuege();
		this.current = gs.getCurrent();
		s1 = gs.getPlayer()[0];
		s2 = gs.getPlayer()[1];
		System.out.println(s1);
		System.out.println(s1.getName());
		System.out.println(s2.getName());
		System.out.println(zuege.toString());
		notifyObs(gs);
	}

	public void saveGame(File f) {
		try {
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(states.getStack());
			out.close();
			fout.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public void loadGame(File f) {
		try {
			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			states = new Caretaker((ArrayDeque<Memento>) in.readObject());
			in.close();
		} catch (IOException e) {
		   // e.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not Found!");
			c.printStackTrace();
			return;
		}
		GameState g = states.readLastState().getGameState();
		loadGameState(g);
	}

	Timer t;

	public void replayGame() {
		Iterator<Memento> iterator = states.iterator();

		t = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!iterator.hasNext()){
					t.stop();
					return;
				}
				Memento m = iterator.next();
				loadGameState(m.getGameState());
			}
		});
		t.start();
	}

	public void setclick(int id) {
		if (lastclick == -1) {
			lastclick = id;
			notifyObs(new SelectState(id, id <= 12 || id == 25, 1));
		} else {
			doAction(toStr(lastclick, id));
			notifyObs(new SelectState(lastclick, lastclick <= 12 || lastclick == 25, 0));
			lastclick = -1;
		}
	}

	// 25 is bar or home
	public String toStr(int a, int b) {
		String first;
		if (a == 25 || a == 26)
			first = "b ";
		else
			first = a + " ";
		if (b == 25 || b == 26)
			return first + "h";
		return first + b;
	}
}
