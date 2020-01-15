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

	/**
	 *
	 * @param action
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
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

	/**
	 *
	 * @param username
	 * @param pwd
	 * @return
	 */
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

	/**
	 *
	 * @param monsterID
	 * @param direction
	 * @return
	 */
	public boolean move(String monsterID, String direction){ return stateGame.move(monsterID, direction); }

	/**
	 *
	 * @return
	 */
	public boolean attack(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean counterAttack(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean useWeapon(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean useSkill(){ return false; }

	/*
	 *
	 *
	 */
	public void createGame(){
		stateGame = new StateGame();
		joinGame();
	}

	/**
	 *
	 * @return
	 */
	public boolean joinGame(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean loadGame(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean saveGame(){ return false; }

	/**
	 *
	 * @return
	 */
	public boolean quitGame(){ return false; }


	/**
	 *
	 * @param color
	 * @return
	 */
	public boolean changeDungeonColor(String color){ return stateGame.changeDungeonColor(color);}

	/**
	 *
	 * @param name
	 * @return
	 */
	public boolean changeDungeonName(String name){
		return stateGame.changeDungeonName(name);
	}

	/**
	 *
	 * @param monsterid
	 * @return
	 */
	public boolean upgradeDungeon(String monsterid){
		return stateGame.upgradeDungeon(monsterid);
	}


	/**
	 *
	 * @param monsterID
	 * @param unitID
	 * @return
	 */
	public boolean selectMinion(String monsterID, String unitID){ return stateGame.selectMinionByID(monsterID,unitID);}

	/**
	 *
	 * @param monsterID
	 * @return
	 */
	public boolean unSelectMinion(String monsterID){return stateGame.unSelectMinion(monsterID);}

	/**
	 *
	 * @param unitID
	 * @return
	 */
	public boolean returnDialogue(String unitID){return stateGame.returnDialogue(unitID);}

	/**
	 *
	 * @return
	 */
	public boolean openDialogue(){ return false;}

	/**
	 *
	 * @return
	 */
	public boolean quitDialogue(){ return false;}

	/**
	 *
	 * @param monsterID
	 * @return
	 */
	public boolean craftItem(String monsterID){ return stateGame.createItem(monsterID);}

	/**
	 *
	 * @param monsterID
	 * @param itemID
	 * @return
	 */
	public boolean upgradeItem(String monsterID, String itemID){ return stateGame.upgradeItem(monsterID,itemID);}

	/**
	 *
	 * @param monsterID
	 * @param nameItem
	 * @return
	 */
	public boolean buyItem(String monsterID, String nameItem){return stateGame.buyItem(monsterID , nameItem);}

	/**
	 *
	 * @param monsterID
	 * @param itemID
	 * @return
	 */
	public boolean sellItem(String monsterID, String itemID){ return stateGame.sellItem(monsterID,itemID);}

	/**
	 *
	 * @param monsterID
	 * @param itemID
	 * @return
	 */
	public boolean storeItem(String monsterID, String itemID){ return stateGame.storeItem(monsterID,itemID);}

	/**
	 *
	 * @param monsterID
	 * @param itemID
	 * @return
	 */
	public boolean pickUpItem(String monsterID, String itemID){ return stateGame.pickUpItem(monsterID,itemID);}

	/**
	 *
	 * @param monsterID
	 * @return
	 */
	public boolean seeCharacteristics(String monsterID){return stateGame.seeCharacteristics(monsterID);}

	/**
	 *
	 * @param monsterID
	 * @param characteristic
	 * @param value
	 * @return
	 */
	public boolean editCharacteristic(String monsterID, String characteristic, String value ){return stateGame.editCharacteristic(monsterID,characteristic,value);}

	/**
	 *
	 * @param action
	 * @param command
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
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
