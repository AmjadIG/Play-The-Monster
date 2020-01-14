package businesslogic.client;

import businesslogic.client.domain.AbstractUnit;
import businesslogic.client.domain.User;

import comlayer.Serializer;

import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
	public ArrayList<User> connectedUsers = new ArrayList();
	public StateGame stateGame;
	public Serializer serializer = new Serializer();
	public Object interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(serializer.isGameStateModification(action)) {
			serializer.formating(action);
			return delegateTo(action, serializer.getCommand(),serializer.getParams());
		}else if(serializer.isDatabaseModification(action)) {
			serializer.formating(action);
			return delegateTo(action, serializer.getCommand(),serializer.getParams());
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
		DAO userDAO = DAOFactory.getUserDAO();
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
	public boolean changeDungeonColor(String color){ return stateGame.changeDungeonColor(color);}
	public boolean changeDungeonName(String name){
		return stateGame.changeDungeonName(name);
	}
	public boolean upgradeDungeon(String monsterid){
		return stateGame.upgradeDungeon(monsterid);
	}

	public boolean selectMinion(String monsterID, String unitID){ return stateGame.selectMinionByID(monsterID,unitID);}
	public boolean unSelectMinion(String monsterID){return stateGame.unSelectMinion(monsterID);}
	public boolean returnDialogue(String unitID){return stateGame.returnDialogue(unitID);}
	public boolean openDialogue(){ return false;}
	public boolean quitDialogue(){ return false;}

	// Christophe

	public boolean craftItem(String monsterID){ return stateGame.createItem(monsterID);}
	public boolean upgradeItem(String monsterID, String itemId){ return stateGame.upgradeItem(monsterID,itemId);}
	public boolean buyItem(String nameItem){ return false;}
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
