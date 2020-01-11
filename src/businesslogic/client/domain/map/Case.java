package businesslogic.client.domain.map;

import businesslogic.client.domain.AbstractUnit;

public class Case {
    private AbstractUnit occupiedBy;

    public AbstractUnit getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(AbstractUnit occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
