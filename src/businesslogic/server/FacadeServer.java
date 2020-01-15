package businesslogic.server;

import businesslogic.StateGame;
import businesslogic.domain.*;
import businesslogic.domain.entity.Item;
import businesslogic.domain.unit.Minion;
import businesslogic.domain.unit.Monster;
import comlayer.Deserializer;
import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeServer {
	public ArrayList<User> connectedUsers = new ArrayList();
	public StateGame stateGame;
	public Deserializer serializer = new Deserializer();
	public Object interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(serializer.isGameStateModification(action)) {
			
			return delegateTo(action, serializer.extractCommand(action),serializer.extractParams(action));
		}else if(serializer.isDatabaseModification(action)) {
			
			return delegateTo(action, serializer.extractCommand(action),serializer.extractParams(action));
		}else {
			System.out.println("commande incorrecte");
			return null;
		}
	}
	/**
	 * Sign-up the user (account creation)
	 * @param username 
	 * @param pwd : password
	 * @return true if the account has been created
	 */
	public int signup(String username, String pwd) {
		DAO<User> userDAO = DAOFactory.getUserDAO();
		User u = new User(userDAO.getNextAutoIncrement(), username, pwd);
		userDAO.save(u);
		login(u.getName(),u.getPassword());
		return login(u.getName(),u.getPassword());
	}

	public int login(String username, String pwd) {
		DAO<User> userDAO = DAOFactory.getUserDAO();
		Map<String, String> attr = new HashMap<>();
		attr.put("name", username);
		attr.put("password", pwd);
		List<User> res = userDAO.getBy(attr);
		if(res.size()!=0) {
			this.connectedUsers.add(res.get(0));
			return res.get(0).getId();
		}
		return -1;
	}
	// Lucas
	public boolean move(String monsterID, String direction){ return stateGame.move(monsterID, direction); }
	public boolean attack(){ return false; }
	public boolean counterAttack(){ return false; }
	public boolean useWeapon(){ return false; }
	public boolean useSkill(){ return false; }

	public void createGame(){
		stateGame = new StateGame();
		joinGame();
	}
	public boolean joinGame(){ return false; }
	public boolean loadGame(){ return false; }
	public boolean saveGame(){ return false; }
	public boolean quitGame(){ return false; }

	// Rayan
	public boolean upgradeDungeon(String monsterID){
		Monster monster = stateGame.getMonsterByID(monsterID);
		if (monster.tryUpgradeDungeon()){
			monster.setMoney( monster.getMoney() - monster.getDungeon().getPriceToUpgrade());
			monster.getDungeon().applyUpgrade();
			return true;
		}
		return false;
	}

	/**
	 * change the name of the dungeon if the string in parameter is good
	 * @param monsterID, name
	 * @return boolean
	 */
	public boolean changeDungeonName(String monsterID, String name){
		if(name.length()>0){
			stateGame.getMonsterByID(monsterID).getDungeon().setName(name);
			return true;
		}else{
			return false;
		}

	}
	/**
	 * change the dungeon color if the color in param is good
	 * @param monsterID, color
	 * @return boolean
	 */
	public boolean changeDungeonColor(String monsterID, String color) {
		Monster monster = stateGame.getMonsterByID(monsterID);
		if(monster.getDungeon().isDungeonColorAvailable(color)){
			monster.getDungeon().setColor(color);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * put this minion in attribute of this monsterID
	 * @param unitID
	 * @return boolean
	 */
	public boolean selectMinionByID(String monsterID , String unitID) {
		Monster monster = stateGame.getMonsterByID(monsterID);
		Minion minionSelected = monster.getDungeon().selectMinionByID(unitID);
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
		stateGame.getMonsterByID(monsterID).unSelectMinion();
		return true;
	}
	/**
	 * sysout the minion dialogue
	 * @param monsterID, unitID
	 * @return
	 */
	public boolean returnDialogue(String monsterID, String unitID) {
		Minion minionSelected = stateGame.getMonsterByID(monsterID).getDungeon().selectMinionByID(unitID);
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
		stateGame.getMonsterByID(monsterID).addItem(sword);
		return true;
	}
	/**
	 * upgrade an item and add it to the monster's list of items
	 * @param monsterID
	 * @return
	 */
	public boolean upgradeItem(String monsterID, String itemId ) {
		Monster monster = stateGame.getMonsterByID(monsterID);
		Item myItem = monster.getItemById(itemId);
		if (myItem != null){
			monster.upgrade(myItem);
			return true;
		}
		return false;

	}
	public boolean buyItem(String monsterID, String nameItem) {
		if (stateGame.isNameItemAvailable(nameItem)){
			createItem(monsterID);
			return true;
		}else{
			return false;
		}
	}

	public boolean openDialogue(){ return false;}
	public boolean quitDialogue(){ return false;}

	// Christophe

	public boolean sellItem(){ return false;}
	public boolean storeItem(){ return false;}
	public boolean pickUpItem(){ return false;}

	// Amjad
	public boolean addCharacter(){return false;}
	public boolean deleteCharacter(){return false;}

	public boolean seeCharacteristics(){return false;}
	public boolean editCharacteristics(){return false;}



	public Object delegateTo(String action, String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?>[] typeParametres = null;
		if (params != null) {
			typeParametres = new Class[params.length];
			for (int i = 0; i < params.length; ++i) {
				typeParametres[i] = params[i].getClass();
			}
		}
		Method m = getClass().getMethod(command, typeParametres);
		Object res = m.invoke(this, params);
		return action + "=" + res;
	}
}
