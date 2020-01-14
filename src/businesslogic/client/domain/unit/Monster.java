package businesslogic.client.domain.unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import businesslogic.client.domain.map.Case;
import gui.GameInterface;

public class Monster extends ActiveUnit implements KeyListener{
    private Monster player = null;
    private int money;
    private List<Integer> stats; //3 stats attack, defense, speed
    private List<String> spell = null;
    public Monster(GameInterface board, Case position) {
    	super(board, position);
    	
    	stats.add(1,10); // attack
        stats.add(2,10); // defense
        stats.add(3,10); // speed
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
	public int getMoney() { return money; }
	public void setMoney(int money) { this.money = money; }
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
			setStaticOnX();
		}
		if(keycode == KeyEvent.VK_D) {
			setStaticOnX();
		}
		if(keycode == KeyEvent.VK_S) {
			setStaticOnY();
		}
		if(keycode == KeyEvent.VK_Z ) {
			setStaticOnY();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
}
