package businesslogic.client.domain.unit;

import businesslogic.client.domain.AbstractUnit;

public abstract class ActiveUnit extends AbstractUnit {
    private int idType;
    private AbstractRole role;
    private int attack;
    private int defense;
    private int health;

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

    public int getAttack() { return attack; }

    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }

    public void setDefense(int defense) { this.defense = defense; }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }
}
