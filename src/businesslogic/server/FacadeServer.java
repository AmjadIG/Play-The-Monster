package businesslogic.server;

import businesslogic.client.StateGame;
import businesslogic.client.domain.User;
import businesslogic.client.domain.unit.Monster;
import comlayer.Deserializer;
import comlayer.server.EchoServer;
import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeServer {
	public ArrayList<User> connectedUsers = new ArrayList();
	public StateGame stateGame;
	public EchoServer echoServer;
	public Deserializer serializer = new Deserializer();
	
	public FacadeServer(EchoServer es) {
		this.echoServer = es;
		createGame();
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
	}
	public boolean joingame(String id){
		return true;
	}
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
	public boolean buyItem(String monsterID, String nameItem){return stateGame.buyItem(monsterID , nameItem);}
	public boolean sellItem(){ return false;}
	public boolean storeItem(){ return false;}
	public boolean pickUpItem(){ return false;}

	// Amjad
	public boolean addCharacter(){return false;}
	public boolean deleteCharacter(){return false;}
	public boolean seeCharacteristics(){return false;}
	public boolean editCharacteristics(){return false;}
	
	public Object interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return delegateTo(action, serializer.extractCommand(action),serializer.extractParams(action));
	}
	public Object delegateTo(String action, String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?>[] typeParametres = new Class<?>[params.length];
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
