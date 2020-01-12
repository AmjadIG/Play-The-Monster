package businesslogic.client;

import businesslogic.client.domain.User;
import comlayer.Serializer;
import comlayer.server.*;
import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
	public ArrayList<User> connectedUsers = new ArrayList();
	public ArrayList<StateGame> stateGames = new ArrayList();
	public Serializer serializer = new Serializer();
	private String lastStringAction;

	public Facade() {}

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
