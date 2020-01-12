package persistlayer.DAO;

import login.PersistantLayer.*;

public class DAOFactory {
	public static DAO getUserDAO() {
		return new UserDAO(); 
	}
}
