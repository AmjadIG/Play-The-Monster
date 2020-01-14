package persistlayer.DAO;

import java.util.HashMap;
import java.util.Map;

import businesslogic.client.domain.User;

public class Test {

	public static void main(String[] args) {
		getNextAutoIncrementTest();
	}
	public static void getNextAutoIncrementTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		System.out.println(uDAO.getNextAutoIncrement());
	}
	public static void getTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		User user = uDAO.get(1);
		System.out.println(user.getName());
	}
	public static void getByTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		Map<String, String> attr = new HashMap<>();
		attr.put("name", "rayan");
		for(User user : uDAO.getBy(attr)) {
			System.out.println(user.getName());
		}
	}
	public static void getAllTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		for(User user : uDAO.getAll()) {
			System.out.println(user.getName());
		}
	}
	public static void saveTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		System.out.println(uDAO.save(new User(uDAO.getNextAutoIncrement(),"christophe", "christophemdp2")));
	}
	
	public static void deleteTest() {
		DAO<User> uDAO = DAOFactory.getUserDAO();
		System.out.println(uDAO.delete(new User(5,"christophe", "christophemdp2")));
	}
}
