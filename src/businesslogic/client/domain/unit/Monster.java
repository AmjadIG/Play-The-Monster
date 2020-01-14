package businesslogic.client.domain.unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import businesslogic.client.domain.map.Case;
import gui.Board;

public class Monster extends ActiveUnit implements KeyListener{
	private final int M_WIDTH = 10;
	private final int M_HEIGHT = 10;
	private int posX = 0;
	private int posY = 0;
	private int dx = 0;
	private int dy = 0;
	private int speed = 1;
	private Board board;
    private Monster player = null;
    private List<Integer> stats; //3 stats attack, defense, speed
    private List<String> spell = null;
    public Monster(Case position) {
    	setPosition(position);
    	stats.add(1,10); // attack
        stats.add(2,10); // defense
        stats.add(3,10); // speed
    }
    public Monster(Board b) {
		board = b;
	}
    public List<Integer> getStats() {
        return stats;
    }
    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }
    public List<String> getSpell() {
        return spell;
    }
    public void setSpell(List<String> spell) {
        this.spell = spell;
    }
    public void update() {
		int realdx = speed*dx;
		int realdy = speed*dy;
		if (posX + realdx < 0 || posX + realdx > board.getWidth()) {
			return;
		}
		if (posY + realdy < 0 || posY + realdy > board.getHeight()) {
			return;
		}
		posX = posX + realdx;
		posY = posY + realdy;
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(posX, posY, M_WIDTH, M_HEIGHT);
	}
	public void logPos() {
		String msg = String.format("Monster object: x:%s y:%s dx:%s dy:%s", 
				Integer.toString(posX), 
				Integer.toString(posY),
				Integer.toString(dx),
				Integer.toString(dy));
		System.out.println(msg);
	}
	private void speedDown() {
		if(speed>1) {
			speed--;
		}
	}
	private void speedUp() {
		speed++;
	}
	private void moveUp() {
		dy = -1;
	}
	private void moveDown() {
		dy = +1;
	}
	private void moveRight() {
		dx = +1;
	}
	private void moveLeft() {
		dx = -1;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(KeyEvent.VK_Q == arg0.getKeyCode()) {
			moveLeft();
		}
		if(KeyEvent.VK_D == arg0.getKeyCode()) {
			moveRight();
		}
		if(KeyEvent.VK_S == arg0.getKeyCode()) {
			moveDown();
		}
		if(KeyEvent.VK_Z == arg0.getKeyCode()) {
			moveUp();
		}
		if(KeyEvent.VK_N == arg0.getKeyCode()) {
			speedUp();
		}
		if(KeyEvent.VK_B == arg0.getKeyCode()) {
			speedDown();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int keycode = arg0.getKeyCode();
		if(keycode == KeyEvent.VK_Q) {
			dx = 0;
		}
		if(keycode == KeyEvent.VK_D) {
			dx = 0;
		}
		if(keycode == KeyEvent.VK_S) {
			dy = 0;
		}
		if(keycode == KeyEvent.VK_Z ) {
			dy = 0;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
