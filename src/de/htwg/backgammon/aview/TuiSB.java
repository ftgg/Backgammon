package de.htwg.backgammon.aview;

import de.htwg.backgammon.model.implementation.GameState;

public interface TuiSB {

	/**
	 * returns a object, wich contains dice results and tokens on pitch
	 * 
	 * @param gs
	 *            current GameState
	 * @return StringBuilder object with game informations
	 */
	public StringBuilder getInformations(GameState gs);

	/**
	 * returns a object, wich contains the current pitch with all tokens
	 * 
	 * @param gs
	 *            current GameState
	 * @return StringBuilder object with a created Backgammon field
	 */
	public StringBuilder getStringBuilder(GameState gs);

}
