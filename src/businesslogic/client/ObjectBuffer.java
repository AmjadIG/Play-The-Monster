package businesslogic.client;

import businesslogic.domain.User;
import businesslogic.domain.entity.Item;
import businesslogic.domain.entity.Obstacle;
import businesslogic.domain.map.Map;
import businesslogic.domain.unit.Monster;
import businesslogic.domain.unit.NonPlayerCharacter;

import java.util.List;

public class ObjectBuffer {
    List<NonPlayerCharacter> npcBuffer;
    List<Item> itemsBuffer;
    List<Obstacle> obstaclesBuffer;

    List<User> userBuffer;
    Monster monsterBuffer;
    Map mapBuffer;

    public Monster getMonsterBuffer() {
        return monsterBuffer;
    }

    public void setMonsterBuffer(Monster monsterBuffer) {
        this.monsterBuffer = monsterBuffer;
    }

    public List<User> getUserBuffer() {
        return userBuffer;
    }

    public void setUserBuffer(List<User> userBuffer) {
        this.userBuffer = userBuffer;
    }

    public List<Obstacle> getObstaclesBuffer() {
        return obstaclesBuffer;
    }

    public void setObstaclesBuffer(List<Obstacle> obstaclesBuffer) {
        this.obstaclesBuffer = obstaclesBuffer;
    }

    public Map getMapBuffer() {
        return mapBuffer;
    }

    public void setMapBuffer(Map mapBuffer) {
        this.mapBuffer = mapBuffer;
    }

    public List<Item> getItemsBuffer() {
        return itemsBuffer;
    }

    public void setItemsBuffer(List<Item> itemsBuffer) {
        this.itemsBuffer = itemsBuffer;
    }

    public List<NonPlayerCharacter> getNpcBuffer() {
        return npcBuffer;
    }

    public void setNpcBuffer(List<NonPlayerCharacter> npcBuffer) {
        this.npcBuffer = npcBuffer;
    }

    public void save(){ //save the buffer so the game is upToDate

    }

    public void update(){ //call the "getNewInstance" method of Object Factory

    }
}
