package businesslogic.client.domain.unit.role;

import businesslogic.client.domain.unit.AbstractRole;

public class Warrior extends AbstractRole {
    private int power = 50;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
