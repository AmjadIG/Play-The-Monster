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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    /**
     * upgrade the dungeon if the monster has enough money and return if it made the upgrade
     * @param monsterID
     * @return
     */
    public boolean upgradeDungeon(String monsterID) {
        Monster monster = getMonsterByID(monsterID);
        if(tryUpgradeDungeon(monster)){
            monster.setMoney( monster.getMoney() - getMap().getDungeon().getPriceToUpgrade() );
            getMap().getDungeon().applyUpgrade();
            return true;
        }else{
            return false;
        }
    }

    /**
     * verify if the monster had enough money to upgrade his dungeon
     * @param monster
     * @return boolean
     */
    public boolean tryUpgradeDungeon(Monster monster){
        return (monster.getMoney() >= getMap().getDungeon().getPriceToUpgrade());
    }

    /**
     * change the name of the dungeon if the string in parameter is good
     * @param name
     * @return boolean
     */
    public boolean changeDungeonName(String name){
        if(name.length()>0){
            getMap().getDungeon().setName(name);
            return true;
        }else{
            return false;
        }

    }

	public boolean move(String monsterID, String direction) {
		Monster m = getMonsterByID(monsterID);
		return false;
	}

    /**
     * return the monster with this monsterID , return null if there is no Monster associate to this monsterID
     * @param monsterID
     * @return
     */
	private Monster getMonsterByID(String monsterID) {
		return monsters.stream().filter(m-> String.valueOf(m.getIdUnit()) == monsterID).findFirst().get();
	}

}
