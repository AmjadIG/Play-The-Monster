package businesslogic.client.domain.map;

public class Dungeon extends Zone {
    private int level;
    private String color;
    private Dungeon dungeon = null;

    public Dungeon getDungeon(){
        if (dungeon.equals(null)){
            dungeon = new Dungeon();
            level = 1;
        }
        return dungeon;
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
