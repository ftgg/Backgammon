package de.htwg.backgammon.aView;

import java.util.Scanner;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.controller.GameState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Tui implements Observer {
	//Alle Feldzahlen eingabe  1-24 intern 0 -23
	private GameState gs;
	private Controller contr;
	private Scanner sc = new Scanner(System.in);
	public Tui(Controller c) {
		contr = c;
		contr.add(this);
		initNames();
		running();
	}
	
	private void initNames() {
		String s1;
		String s2;
		System.out.println("Hallo, bitte zwei Spielernamen angeben:");
		System.out.println("Name Spieler Weiss:");
		
		s1 = sc.next();
		System.out.println("Name Spieler Schwarz:");
		s2 = sc.next();
		
		System.out.printf("Viel Spass %s und %s\n", s1, s2);
		contr.setSpieler(s1, s2);
	}

	private void running() {
		while (!gs.getGameFinished()) {
			System.out.println(gs.getMessage());
			//print();
			eingabe();
		}
	}

	private void eingabe(){
		System.out.println(gs.getCurrent().getName() + " ist am Zug:");
		String input;
		input = sc.nextLine();
		contr.doAction(input);
	}
	
	public void print() {
		int feldbreite = gs.getBlackStones().length; //anzahl felder!
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= feldbreite/2 ; i++){
			addWhitespace(i,sb);
		}
		System.out.println(sb.toString());
		printTokens(1,feldbreite/2);
	}

	//TODO gibt noch seltsamme sachen aus
	private void printTokens(int start, int end) {
		//gibt w f�r weisse und s f�r schwarze steine aus
		int[] bs = gs.getBlackStones();
		int[] ws = gs.getWhiteStones();
		StringBuilder sb = new StringBuilder();
		while(start <= end){
			if(bs[start] != 0){
				sb.append(bs[start]).append("S");
			}
			else if(ws[start] != 0){
				sb.append(ws[start]).append("W");
			}else {
				sb.append("  ");
			}
			start++;
		}
		System.out.println(sb.toString());
	}

	private void addWhitespace(int a,StringBuilder sb){
		sb.append(a).append(" ");
		if(a < 10)
			sb.append(" ");
	}
	
	@Override
	public void update(Event e) {
		if (e instanceof GameState) {
			gs = (GameState) e;
		}

	}
}
