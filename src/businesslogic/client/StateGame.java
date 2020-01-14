package businesslogic.client;

import businesslogic.client.domain.*;
import businesslogic.client.domain.entity.Item;
import businesslogic.client.domain.map.Map;
import businesslogic.client.domain.unit.Minion;
import businesslogic.client.domain.unit.Monster;

import java.util.ArrayList;
import java.util.List;


public class StateGame {
    private List<String> rollBack;
    private List<User> connectedUsers = new ArrayList<>();
    private Map map;
    private List<Monster> monsters;


    public StateGame() {
    	this.map = new Map();
    	this.monsters = new ArrayList<Monster>();
    	for(User user : connectedUsers) {
    		monsters.add(user.getMonster());
    	}
    }

    /**
     * add String in the List<String> rollback
     * @param command
     */
    public void update(String command){
        rollBack.add(command);
    }

    /**
     * List<String> getter
     * @return List<String>
     */
    public List<String> getRollBack() {
        return rollBack;
    }

    /**
     *
     * @param rollBack
     */
    public void setRollBack(List<String> rollBack) {
        this.rollBack = rollBack;
    }

    /**
     * ConnectedUsers getter
     * @return List<User>
     */
    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    /**
     * ConnectedUsers setter
     * @param connectedUsers
     */
    public void setConnectedUsers(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    //TODO
    public void updateBuffer(){ //update the state of ObjectBuffer

    }

    /**
     * Map getter
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Map setter
     * @param map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Monsters getter
     * @return Monster
     */
    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Monsters setter
     * @param monsters
     */
    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    /**
     * upgrade the dungeon if the monster has enough money and return if it made the upgrade
     * @param monsterID
     * @return boolean
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
		return m.move(direction);
	}

    /**
     * return the monster with this monsterID , return null if there is no Monster associate to this monsterID
     * @param monsterID
     * @return boolean
     */
	private Monster getMonsterByID(String monsterID) {
		return monsters.stream().filter(m-> String.valueOf(m.getIdUnit()) == monsterID).findFirst().get();
	}

    /**
     * change the dungeon color if the color in param is good
     * @param color
     * @return boolean
     */
    public boolean changeDungeonColor(String color) {
	    if(isDungeonColorAvailable(color)){
	        getMap().getDungeon().setColor(color);
	        return true;
        }else return false;
    }

    /**
     * return true or false if this color is available
     * @param color
     * @return boolean
     */
    private boolean isDungeonColorAvailable(String color) {
	    return true;
    }

    /**
     * put this minion in attribute of this monsterID
     * @param unitID
     * @return boolean
     */
    public boolean selectMinionByID(String monsterID , String unitID) {
        Monster monster = getMonsterByID(monsterID);
        Minion minionSelected = getMap().getDungeon().selectMinionByID(unitID);
        if (minionSelected != null){
            monster.setMinion(minionSelected);
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param monsterID
     * @return boolean
     */
    public boolean unSelectMinion(String monsterID) {
        Monster monster = getMonsterByID(monsterID);
        monster.unSelectMinion();
        return true;
    }

    /**
     * sysout the minion dialogue
     * @param unitID
     * @return
     */
    public boolean returnDialogue(String unitID) {
        Minion minionSelected = getMap().getDungeon().selectMinionByID(unitID);
        if (minionSelected != null){
            String sentence = minionSelected.getSentence();
            System.out.println(sentence);
            return true;
        }else{
            return false;
        }
    }

    /**
     * create an item and add it to the monster's list of items
     * @param monsterID
     * @return
     */
    public boolean createItem(String monsterID) {
        Item sword = new Item("Sword",5,1);
        Monster monster = getMonsterByID(monsterID);
        monster.addItem(sword);
        return true;
    }

    /**
     * upgrade an item and add it to the monster's list of items
     * @param monsterID
     * @return
     */
    public boolean upgradeItem(String monsterID, String itemId ) {
        Monster monster = getMonsterByID(monsterID);
        Item myItem = monster.getItemById(itemId);
        if (myItem != null){
            monster.upgrade(myItem);
            return true;
        }
        return false;

    }

}
