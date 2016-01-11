package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import de.htwg.backgammon.controller.Controller;

public class Gui extends JFrame {

	private MainPanel mainPanel;
	private JLayeredPane pitch;
	protected Controller c;
	public static final int WINX = 1300;
	public static final int WINY = 850;

	public Gui(Controller c) {
		super("Backgammon");
		this.c = c;
		mainPanel = new MainPanel(c);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pitch = new JLayeredPane();

		pitch.setPreferredSize(new Dimension(WINX, WINY));
		pitch.setLayout(null);
		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(0, 0, 0, 0));
		pitch.add(mainPanel, new Integer(2), 0);
		ImageIcon ico = new ImageIcon("images/Background.png");
		JLabel bg = new JLabel(ico);
		bg.setBounds(0, 0, 2500, 1700);
		pitch.add(bg, new Integer(1), 0);
		pitch.moveToBack(bg);

		this.setPreferredSize(new Dimension(WINX, WINY));
		this.getContentPane().setLayout(new BorderLayout());

		this.setJMenuBar(new MyMenuBar(this));
		this.add(pitch, BorderLayout.CENTER);

		this.setVisible(true);
		this.pack();
		this.repaint();
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				//Do nothing because not needed
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				//Do nothing because not needed
			}

			@Override
			public void componentResized(ComponentEvent e) {
				mainPanel.setSize(e.getComponent().getWidth(), e.getComponent().getHeight() - 60);
				mainPanel.resize();
				mainPanel.repaint();
			}

			@Override
			public void componentShown(ComponentEvent e) {
				//Do nothing because not needed
			}
		});
		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent arg0) {
				// minimized
				if ((arg0.getNewState() & JFrame.NORMAL) == JFrame.NORMAL) {
					mainPanel.setSize(arg0.getComponent().getWidth(), arg0.getComponent().getHeight() - 60);
					mainPanel.resize();
				}
				// maximized
				else if ((arg0.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
					mainPanel.setSize(arg0.getComponent().getWidth(), arg0.getComponent().getHeight() - 60);
					mainPanel.resize();
				}
			}

		});

	}

}
