package businesslogic.client.domain.map;

public class Dungeon extends Zone {
	private int level;
    private String color;

    public Dungeon(int x, int y, int level, String color) {
		super(x, y);
		this.level = level;
		this.color = color;
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
}
