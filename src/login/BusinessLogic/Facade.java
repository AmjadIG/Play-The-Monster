package login.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

import DAO.DAOFactory;
import login.PersistantLayer.UserDAO;

public class Facade {
	public boolean login(String uid, String pwd) {
		UserDAO udao = (UserDAO) DAOFactory.getUserDAO();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("login", uid);
		userInfo.put("password", pwd);
		if (udao.getBy(userInfo) != null) {
			return true;
		}
		return false;
	}
	private void askCreation(String uid, String pwd) {
		
	}
}
