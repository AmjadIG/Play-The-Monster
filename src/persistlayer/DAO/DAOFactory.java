package persistlayer.DAO;
import businesslogic.client.domain.User;

public class DAOFactory {
	public static DAO<User> getUserDAO() {
		return new UserDAO(); 
	}
}
