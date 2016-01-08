package de.htwg.backgammon.aview.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.backgammon.controller.Controller;
import de.htwg.backgammon.model.implementation.InitPlayersState;
import de.htwg.backgammon.util.Event;
import de.htwg.backgammon.util.Observer;

public class Gui extends JFrame  {

	public MainPanel mainPanel; // Public just for tests
	private JLayeredPane pitch;
	private InitPlayersState ps;
	protected Controller c;
	public static final int winX = 1300;
	public static final int winY = 800;
	public Gui(Controller c) {
		super("Backgammon");
		this.c = c;
		mainPanel = new MainPanel(c);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pitch = new JLayeredPane();
		
		pitch.setPreferredSize(new Dimension(winX,winY));
		pitch.setLayout(null);
		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(0,0,0,0));
		pitch.add(mainPanel, new Integer(2), 0);
		ImageIcon ico = new ImageIcon("images/Background.png");
		JLabel bg = new JLabel(ico);
		bg.setBounds(0, 0, 2500, 1700);
		pitch.add(bg, new Integer(1), 0);
		pitch.moveToBack(bg);
		
		this.setPreferredSize(new Dimension(winX,winY));
		this.getContentPane().setLayout(new BorderLayout());
		
		this.setJMenuBar(new myMenuBar(this));
		this.add(pitch,BorderLayout.CENTER);
		
		this.setVisible(true);
		this.pack();
		this.repaint();
		// initplayers();
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				mainPanel.setSize(e.getComponent().getWidth(), e.getComponent().getHeight()-60);
				mainPanel.resize();
				mainPanel.repaint();
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent arg0) {
				   // minimized
				   if ((arg0.getNewState() & JFrame.NORMAL) == JFrame.NORMAL){
					   mainPanel.setSize(arg0.getComponent().getWidth(), arg0.getComponent().getHeight()-60);
					   mainPanel.resize();
				   }
				   // maximized
				   else if ((arg0.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH){
					   mainPanel.setSize(arg0.getComponent().getWidth(), arg0.getComponent().getHeight()-60);
					   mainPanel.resize();
				   }
			}
			
		});
		
	}

	// TODO spielernamen eingeben
	private void initplayers() {
		c.setSpieler("White", "Black");
	}

}
