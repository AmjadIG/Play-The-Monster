package businesslogic.domain.unit;

import java.util.List;

public class Monster extends ActiveUnit {
    private int[] stats; //3 stats attack, defense, speed
    private List<String> spell;

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public List<String> getSpell() {
        return spell;
    }

    public void setSpell(List<String> spell) {
        this.spell = spell;
    }
}
