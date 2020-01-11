package businesslogic.domain.unit.role;

import businesslogic.domain.unit.AbstractRole;

public class Archer extends AbstractRole {
    private int vision;

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }
}
