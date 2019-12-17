package DAO;
import login.PersistantLayer.*;
import login.BusinessLogic.*;
public class DAOFactory {
	public static DAO getUserDAO() {
		return new UserDAO(); 
	}
}
