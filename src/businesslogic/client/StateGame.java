package businesslogic.client;

import businesslogic.client.domain.*;
import businesslogic.client.domain.map.Map;
import businesslogic.client.domain.unit.Monster;

import java.util.ArrayList;
import java.util.List;


public class StateGame {
    private List<String> rollBack;
    private List<User> connectedUsers;
    private Map map;
    private List<Monster> monsters;

    public StateGame() {
    	this.map = new Map();
    	this.monsters = new ArrayList<Monster>();
    	for(User user : connectedUsers) {
    		monsters.add(user.getMonster());
    	}
    }
    public void update(String command){
        rollBack.add(command);
    }

    public List<String> getRollBack() {
        return rollBack;
    }
    public void setRollBack(List<String> rollBack) {
        this.rollBack = rollBack;
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }
    public void setConnectedUsers(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    //TODO
    public void updateBuffer(){ //update the state of ObjectBuffer

    }
}
