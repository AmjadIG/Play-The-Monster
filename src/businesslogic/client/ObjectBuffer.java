package businesslogic.client;

import businesslogic.client.domain.User;
import businesslogic.client.domain.entity.Item;
import businesslogic.client.domain.entity.Obstacle;
import businesslogic.client.domain.map.Map;
import businesslogic.client.domain.unit.Monster;
import businesslogic.client.domain.unit.NonPlayerCharacter;

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
