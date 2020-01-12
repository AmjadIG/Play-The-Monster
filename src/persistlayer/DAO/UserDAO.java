package persistlayer.DAO;

import businesslogic.client.domain.*;

public class UserDAO extends AbstractDAO<User> {

	public UserDAO() {
		super();
	}
	
	@Override
	protected Class<?>[] getConstructorParams() {
		Class<?>[] res = {int.class, String.class, String.class};
		return res;
	}

	@Override
	protected String getTableName() {
		return "User";
	}
}
