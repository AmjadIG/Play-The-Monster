package businesslogic.client.domain.unit;

import businesslogic.client.domain.AbstractUnit;

public abstract class ActiveUnit extends AbstractUnit {
    private int idType;
    private AbstractRole role;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public AbstractRole getRole() {
        return role;
    }

    public void setRole(AbstractRole role) {
        this.role = role;
    }
}
