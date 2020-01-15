package businesslogic.domain.entity;

public class SingleItem extends Item {
    private String effect;

    public SingleItem(String name, int attack, int defense) {
        super(name, attack, defense);
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
