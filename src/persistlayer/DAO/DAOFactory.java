package persistlayer.DAO;


public class DAOFactory {
	public static DAO getUserDAO() {
		return new UserDAO(); 
	}
}
