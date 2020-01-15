package businesslogic.domain.entity;

import businesslogic.domain.AbstractUnit;

public abstract class Entity extends AbstractUnit {
    private int idEntity;

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }
}
