package businesslogic.client.domain.unit.role;

import businesslogic.client.domain.unit.AbstractRole;

public class Archer extends AbstractRole {
    private int vision;

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }
}
