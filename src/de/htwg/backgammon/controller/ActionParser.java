package de.htwg.backgammon.controller;

public class ActionParser {
	String ActionString[];
	int res[];
	ParseUnit parseunit;
	ActionParser(){
		res = new int[] {-3,-3};
		createParseChain();
	}
	
	public int[] parse(String s){
		ActionString = s.split(" ");
		
		if ("n".equals(ActionString[0]))
			return new int[] { Controller.NEXT, Controller.NEXT };
		
		if (ActionString.length != 2)
			return new int[] { -3, -3 };
		parseunit.parse(ActionString);
		return res;
	}
	
	private void createParseChain(){
		ParseUnitBar pb = new ParseUnitBar(this);
		ParseUnitHome ph =new ParseUnitHome(this);
		
		parseunit = pb;
		pb.successor = ph;
	}
	
}
