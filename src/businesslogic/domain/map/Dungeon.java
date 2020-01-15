package businesslogic.domain.map;

import businesslogic.domain.entity.Item;
import businesslogic.domain.unit.Minion;

import java.util.ArrayList;

public class Dungeon extends Zone {
    private String name = "dungeonName";
	private int level = 1;
    private String color;
    private int priceToUpgrade = 1000;
    private ArrayList<Minion> minions;
    private ArrayList<Item> chest = new ArrayList();

	public Dungeon(int x, int y, int level, String color) {
		super(x, y);
		this.level = level;
		this.color = color;
		this.minions = createMinions();
        setGridCase(placeTheMinions());
	}
    
   public void store(Item item) {
	  chest.add(item);
   }
    /**
     * create an example with some Minions
     * @return ArrayList<Minion>
     */
	public ArrayList<Minion> createMinions(){
        ArrayList<Minion> minions = new ArrayList<>();
	    Minion m1 = new Minion();
        m1.setPosition(new Case(1,2,m1));
       
        Minion m2 = new Minion();
        m2.setPosition(new Case(20,20,m2));
        Minion m3 = new Minion();
        m3.setPosition(new Case(8,8,m3));
        minions.add(m1);
        minions.add(m2);
        minions.add(m3);
        return minions;
    }
	
	public void addMinion(Minion minion) {
		minions.add(minion);
		placeTheMinions();
	}
	
	/**
	 * return the gridCase with the minions placed
	 * @return case[][]
	 */
    public Case[][] placeTheMinions(){
    	Case[][] modifGridCase = getGridCase();
    	for (int i = 0; i < minions.size()-1; i++) {
    		modifGridCase[minions.get(i).getPosition().getX()][minions.get(i).getPosition().getY()] = minions.get(i).getPosition();
		}
    	return modifGridCase;
    }
    
    /**
     * return the minion in the dungeon with this id , else return null
     * @param unitID
     * @return
     */
    public Minion selectMinionByID(String unitID) {
        int idUnit = Integer.parseInt(unitID);
        for ( Minion m : minions ) {
            if (m.getIdUnit() == idUnit){
                    return m;
            }
        }
        return null;
    }

	public void applyUpgrade(){
        setLevel(level = level + 1);
    }

	public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPriceToUpgrade() { return priceToUpgrade; }

    public void setPriceToUpgrade(int priceToUpgrade) { this.priceToUpgrade = priceToUpgrade; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getChest() {
		return chest;
	}

	public void setChest(ArrayList<Item> chest) {
		this.chest = chest;
	}
}
