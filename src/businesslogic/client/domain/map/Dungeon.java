package businesslogic.client.domain.map;

public class Dungeon extends Zone {
    private String name;
	private int level;
    private String color;
    private int priceToUpgrade = 1000;

    public Dungeon(int x, int y, int level, String color) {
		super(x, y);
		this.level = level;
		this.color = color;
	}
    public void applyUpgrade(){
        setLevel(level++);
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
}
