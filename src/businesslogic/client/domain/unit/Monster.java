package businesslogic.client.domain.unit;

import java.util.List;

public class Monster extends ActiveUnit {
    private Monster player = null;
    private List<Integer> stats; //3 stats attack, defense, speed
    private List<String> spell = null;



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

    public Monster getPlayer() {
        if (player.equals(null)){
            player = new Monster();
            stats.add(1,10); // attack
            stats.add(2,10); // defense
            stats.add(3,10); // speed
        }
        return player;
    }
}
