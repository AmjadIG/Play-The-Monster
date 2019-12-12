package login.BusinessLogic;

import DAO.DAOFactory;

import java.util.HashMap;
import java.util.Map;

public class Facade {

	public boolean login(Map<String, String> userInfo) {
		return DAOFactory.getUserDAO().existUser(userInfo);
	}
	private void askCreation(String uid, String pwd) {
		
	}
}
