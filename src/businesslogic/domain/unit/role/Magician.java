package businesslogic.domain.unit.role;

import businesslogic.domain.unit.AbstractRole;

public class Magician extends AbstractRole {
    private int mana;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
