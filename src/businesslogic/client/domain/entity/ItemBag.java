package businesslogic.client.domain.entity;

import java.util.List;

public class ItemBag extends Item {
    private List<Item> bag;

    public List<Item> getBag() {
        return bag;
    }

    public void setBag(List<Item> bag) {
        this.bag = bag;
    }
    
}
