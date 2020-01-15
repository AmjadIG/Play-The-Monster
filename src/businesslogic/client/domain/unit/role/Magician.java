package businesslogic.client.domain.unit.role;

import businesslogic.client.domain.unit.AbstractRole;

public class Magician extends AbstractRole {
    private int mana = 20;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
