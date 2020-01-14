package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businesslogic.client.StateGame;
import businesslogic.client.domain.unit.Monster;

import java.awt.Toolkit;

public class GameInterface extends JPanel implements Runnable{

	private static final long serialVersionUID = 5247029752758712281L;
	public static GameInterface board = null;
	
	private final int B_WIDTH = 800;
	private final int B_HEIGHT = 600;
	private final int DELAY = 25;
	private Thread animator;
	private StateGame stateGame;
	
	private GameInterface() {
		stateGame = new StateGame();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setBackground(Color.BLACK);	
		stateGame.getMonsters().stream().forEach(m->addKeyListener(m));
	}
	
	static public GameInterface createBoard() {
		if(board == null) {
			return new GameInterface();
		} else {
			return board;
		}
	}

	private void update() {
		stateGame.updateUnitsPosition();
	}
	
	private void drawObjects(Graphics g) {
		stateGame.draw(g);
		//Sync the painting on system that buffer graphics events
		Toolkit.getDefaultToolkit().sync();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		animator = new Thread(this);
		animator.start();
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		drawObjects(arg0);
	}
	
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		while(true) {
			update();
			repaint();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			if(sleep < 0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				String msg = String.format("Thread interrupted: %s", e.getMessage());
				JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
			}
			beforeTime = System.currentTimeMillis();
		}
	}	
}
