package login.BusinessLogic;


import persistlayer.DAO.DAOFactory;
import login.PersistantLayer.UserDAO;

import java.util.HashMap;
import java.util.Map;


public class Facade {

	User userConnecte;


	public boolean login(String uid, String pwd) {
		UserDAO udao = (UserDAO) DAOFactory.getUserDAO();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("login", uid);
		userInfo.put("password", pwd);
		userConnecte = udao.getBy(userInfo);
		System.out.println(userConnecte);
		return userConnecte != null;
	}

	private void askCreation(String uid, String pwd) {
		
	}
}
