package de.htwg.backgammon.aview.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.backgammon.aview.Tui;
import de.htwg.backgammon.controller.Controller;

public class GuiTest {

	Gui gui;
	Controller c;

	@Before
	public void setUp() throws Exception {
		c = new Controller();
		gui = new Gui(c);
	}

	@Test
	public void testGUI() {
		assertNotNull(gui.mainPanel);
	}
}
