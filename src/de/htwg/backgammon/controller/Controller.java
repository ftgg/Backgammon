package de.htwg.backgammon.controller;


import de.htwg.backgammon.model.Pitch;
import de.htwg.backgammon.model.Player;
import de.htwg.backgammon.model.Token;
import de.htwg.backgammon.model.implementation.SpielFeld;
import de.htwg.backgammon.model.implementation.Spieler;
import de.htwg.backgammon.model.implementation.Stein;
import de.htwg.backgammon.model.implementation.Wuerfel;
import de.htwg.backgammon.util.Subject;

public class Controller extends Subject {
	public static final int NEXT = -4;
	private Player s1;
	private Player s2;
	private Player current;
	private Pitch sf;
	private Wuerfel w;
	private int[] zuege = { 0, 0, 0, 0 };
	private MoveVerifier moveVerifier;
	private ActionParser actionparser;
	
	public Controller() {
		sf = new SpielFeld();// Standartgr��e = original gr��e
		w = new Wuerfel();
		createMoveVerifier();
		actionparser = new ActionParser();
		wuerfeln();
	}

	/**
	 * Controller f�r Test umgebung
	 * 
	 * @param a
	 */
	public Controller(int i) {
		sf = new SpielFeld(i);
		w = new Wuerfel();
		createMoveVerifier();
		actionparser = new ActionParser();
		wuerfeln();
		setSpieler("Weiss", "Schwarz");
	}

	public void setSpieler(String n1, String n2) {
		s1 = new Spieler(n1, Stein.WHITE);
		s2 = new Spieler(n2, Stein.BLACK);
		current = s1;
		notifyObs(new GameState(sf, zuege, "Spiel Beginnt", current, false));
	}

	public void doAction(String s) {
		int[] act = actionparser.parse(s);
		if (act[0] == -3 || act[1] == -3) {
			notifyObs(new GameState(sf, zuege, "Fehlerhafte Eingabe!", current, false));
			return;
		} else if (act[0] == NEXT) {
			spielerwechsel();
			notifyObs(new GameState(sf, zuege, "SpielerWechsel", current, false));
			return;
		}
		spielZug(act[0], act[1]);
	}

<<<<<<< HEAD
	public int[] parseAction(String act) {
		String[] s = act.split(" ");

		if ("n".equals(s[0]))
			return new int[] { NEXT, NEXT };

		if (s.length != 2)
			return new int[] { -3, -3 };
		int[] res = { 0, 0 };

		if ("b".equals(s[0])) {
			res[0] = Pitch.BAR;
		} else {
			res[0] = parseInt(s[0]);
		}

		if ("h".equals(s[1])) {
			res[1] = Pitch.EXIT;
		} else {
			res[1] = parseInt(s[1]);
		}
		return res;
	}

	int parseInt(String s) {
		int a;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			System.err.println(e);
			return -3;
		}
		if (a < 1)
			return -3;
		return a - 1;
	}

=======
	
>>>>>>> branch 'Controller' of https://github.com/ftgg/Backgammon.git
	public void wuerfeln() {
		w.wuerfeln();
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
		String msg = "";
		if (!moveVerifier.checkMove(a, b, zuege, sf, current, s1,s2)) {
			notifyObs(new GameState(sf, zuege, "Nicht m�glicher Zug!", current, false));
			return -3;
		}
		int result = sf.move(a, b, current);
		if (result == 111) {
			msg = current.getName() + " hat gewonnen!";
			win = true;
		}

		removeThrow(a, b);
		if (zuegeEmpty())
			spielerwechsel();

		notifyObs(new GameState(sf, zuege, msg, current, win));
		return result;
	}

	/**
	 * deleates the current move from zuege
	 */
	protected void removeThrow(int a, int b) {
//		if(a < 0 && current.getColor() == Token.WHITE)
//			a = -1;
//		if(a < 0 && current.getColor() == Token.BLACK)
//			a = sf.getSize();
		int digit = Math.abs(a - b);
		//8 nach 6 heisst 7 - 5
		//b ist -2, muss aber -1 bzw. size+1 sein
		System.out.printf("ZUG: %d = %d - %d", digit, a, b);
		for (int i = 0; i < 4; i++)
			if (zuege[i] == digit) {
				zuege[i] = 0;
				break;
			}
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

	private void createMoveVerifier(){
		BarVerifier bv = new BarVerifier();;
		DiceResultVerifier drv = new DiceResultVerifier();
		ExitMoveVerifier emv = new ExitMoveVerifier();
		TargetColorVerifier tcv = new TargetColorVerifier();
		DirectionVerifier dv = new DirectionVerifier();
		bv.successor = drv;
		drv.successor = emv;
		emv.successor = tcv;
		tcv.successor = dv;
		moveVerifier = bv;
	}
	
	
	
	/**
	 * NUR EINE TESTMETHODE nicht zum gebrauch gedacht =)
	 * 
	 * @return Wuerfelergebnis
	 */
	public int[] getWuerfelC() {
		return w.getCurrentCubeNumbers();
	}

	public int[] getZuege() {
		return zuege;
	}

	public Player[] getSpieler() {
		return new Player[] { s1, s2 };
	}

	public Player getCurrent() {
		return current;
	}

}
