package businesslogic.client.domain.unit;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import businesslogic.client.domain.entity.Item;
import businesslogic.client.domain.map.Case;
import uinterface.views.GameFrame;
import uinterface.views.GameInterface;


public class Monster extends ActiveUnit implements KeyListener{
    private Monster player = null;
    private List<String> spell = null;
    private List<Item> items;
    private int money = 1000;
	private Minion minion;
	

    public Monster() {
    	super();

    }

    public void addMoney(int value){
    	money = money + value;
    	if (money + value < 0){
    		money = 0;
		}else{
			money = money + value;
		}
	}

    public List<String> getSpell() {
        return spell;
    }
    public void setSpell(List<String> spell) {
        this.spell = spell;
    }
	public int getMoney() { return money; }
	public void setMoney(int money) { this.money = money; }
	public void unSelectMinion(){
		this.minion = null;
	}
	public void addItem(Item item){
		items.add(item);
	}
	public void upgrade(Item item){
		for (int i = 0; i < items.size(); i++) {
			if(item.getIdItem() == items.get(i).getIdItem()){
				items.get(i).setAttack(items.get(i).getAttack()*2);
				items.get(i).setDefense(items.get(i).getDefense()*2);
			}
		}
	}
	// TODO
	// TODO
	public Item getItemById(String itemID){
		return null;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (KeyEvent.VK_Q == arg0.getKeyCode()) {
			moveLeft();
		}
		if (KeyEvent.VK_D == arg0.getKeyCode()) {
			moveRight();
		}
		if (KeyEvent.VK_S == arg0.getKeyCode()) {
			moveDown();
		}
		if (KeyEvent.VK_Z == arg0.getKeyCode()) {
			moveUp();
		}
		if (KeyEvent.VK_N == arg0.getKeyCode()) {
			speedUp();
		}
		if (KeyEvent.VK_B == arg0.getKeyCode()) {
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

	public Minion getMinion() {
		return minion;
	}

	public void setMinion(Minion minion) {
		this.minion = minion;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void sellItem(String itemID) {
		for (int i = 0; i < items.size(); i++) {
			if(Integer.parseInt(itemID) == items.get(i).getIdItem()){
				items.remove(i);
				addMoney(50);
			}
		}
	}
}
