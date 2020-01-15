package businesslogic.client.domain.entity;

import java.util.Random;

public class Item extends Entity {
    private int idItem;
    private int attack;
    private int defense;

    public Item(String name, int attack , int defense) {
        this.setName(name);
        this.setAttack(attack);
        this.setDefense(defense);
        this.idItem = new Random().nextInt(10000);

    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}
