package businesslogic.client.domain.unit;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import businesslogic.client.domain.entity.Item;
import businesslogic.client.domain.map.Case;
import uinterface.views.GameFrame;
import uinterface.views.GameInterface;


public class Monster extends ActiveUnit implements KeyListener{
    private Monster player = null;
    private List<String> spell = new ArrayList<String>();;
    private List<Item> items = new ArrayList<Item>();
    private int money = 1000;
	private Minion minion;
	

    public Monster() {
    	super();
    	setIdUnit(new Random().nextInt(10000));
    }

    /**
     * 
     * @param value
     */
    public void addMoney(int value){
    	money = money + value;
    	if (money + value < 0){
    		money = 0;
		}else{
			money = money + value;
		}
	}

    /**
     * 
     * @return
     */
    public List<String> getSpell() {
        return spell;
    }
    
    /**
     * 
     * @param spell
     */
    public void setSpell(List<String> spell) {
        this.spell = spell;
    }
    
    /**
     * 
     * @return
     */
	public int getMoney() { return money; }
	
	/**
	 * 
	 * @param money
	 */
	public void setMoney(int money) { this.money = money; }
	
	/**
	 * 
	 */
	public void unSelectMinion(){
		this.minion = null;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addItem(Item item){
		items.add(item);
	}
	
	/**
	 * 
	 * @param item
	 */
	public void upgrade(Item item){
		for (int i = 0; i < items.size(); i++) {
			if(item.getIdItem() == items.get(i).getIdItem()){
				items.get(i).setAttack(items.get(i).getAttack()*2);
				items.get(i).setDefense(items.get(i).getDefense()*2);
			}
		}
	}

	/**
	 * 
	 * 
	 * @param itemID
	 * @return
	 */
	public Item getItemById(String itemID){
		for (int i = 0; i < items.size(); i++) {
			if(Integer.parseInt(itemID) == items.get(i).getIdItem()){
				return items.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param itemID
	 */
	public void removeItemByID(String itemID) {
		for (int i = 0; i < items.size(); i++) {
			if(Integer.parseInt(itemID) == items.get(i).getIdItem()){
				items.remove(i);
			}
		}
	}
	/**
	 * 
	 */
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

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	/**
	 * 
	 * @return
	 */
	public Minion getMinion() {
		return minion;
	}

	/**
	 * 
	 * @param minion
	 */
	public void setMinion(Minion minion) {
		this.minion = minion;
	}

	/**
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * 
	 * @param items
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * 
	 * @param itemID
	 */
	public void sellItem(String itemID) {
		removeItemByID(itemID);
		addMoney(50);
	}
}
