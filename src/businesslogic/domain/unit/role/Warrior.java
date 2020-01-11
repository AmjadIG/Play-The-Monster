package businesslogic.domain.unit.role;

import businesslogic.domain.unit.AbstractRole;

public class Warrior extends AbstractRole {
    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
