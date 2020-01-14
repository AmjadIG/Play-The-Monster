package persistlayer.DAO;
import businesslogic.client.domain.User;

public class DAOFactory {
	private static DAO<User> userDAO = null;
	public static DAO<User> getUserDAO() {
		if (userDAO == null){
			userDAO = new UserDAO();
		}
		return userDAO;
	}
}
