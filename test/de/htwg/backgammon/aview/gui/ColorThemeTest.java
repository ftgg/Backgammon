package de.htwg.backgammon.aview.gui;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

public class ColorThemeTest {

	ColorThemeStandard ct;

	@Before
	public void setUp() throws Exception {
		ct = new ColorThemeStandard();
	}

	@Test
	public void test() {
		//assertSame(ct.get(2),new ImageIcon("images/d_hell.png"));
		assertNotNull(ct.get(0));
		assertSame(ct.getDice(1).getIconHeight(),75);
	}

}
