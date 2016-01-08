package de.htwg.backgammon;

import com.google.inject.AbstractModule;

import de.htwg.backgammon.model.IDice;
import de.htwg.backgammon.model.IPitch;
import de.htwg.backgammon.model.implementation.Dice;
import de.htwg.backgammon.model.implementation.Pitch;

public class BackgammonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDice.class).to(Dice.class);
		bind(IPitch.class).to(Pitch.class);
	}

}
