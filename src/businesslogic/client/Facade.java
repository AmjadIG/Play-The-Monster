package businesslogic.client;

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
	public boolean signup(String username, String pwd) {
		System.out.println("test");
		User u = new User(10, username, pwd);
		DAO userDAO = DAOFactory.getUserDAO();
		//TODO login();
		return userDAO.save(u);
	}
	public boolean login(String username, String pwd) {
		DAO userDAO = DAOFactory.getUserDAO();
		Map<String, String> attr = new HashMap<>();
		attr.put("name", username);
		attr.put("password", pwd);
		List<User> res = userDAO.getBy(attr);
		if(res.size()!=0) {
			this.connectedUsers.add(res.get(0));

			return true;
		}
		return false;
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
	public boolean changeDungeonColor(String Color){ return false;}
	public boolean changeDungeonName(String name){
		return stateGame.changeDungeonName(name);
	}
	public boolean upgradeDungeon(String monsterid){
		return stateGame.upgradeDungeon(monsterid);
	}

	public boolean selectMinion(){return false;}
	public boolean unSelectMinion(){return false;}
	public boolean openDialogue(){ return false;}
	public boolean quitDialogue(){ return false;}

	// Christophe
	public boolean craftItem(){ return false;}
	public boolean upgradeItem(){ return false;}
	public boolean buyItem(){ return false;}
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
