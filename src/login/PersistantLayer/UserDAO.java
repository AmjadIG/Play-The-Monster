package login.PersistantLayer;

import java.util.List;
import java.util.Map;
import DAO.DAO;
import login.BusinessLogic.*;

public class UserDAO implements DAO<User> {

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User t, String[] params) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User getBy(Map<String, String> keyVal) {
		return null;
	}

	@Override
	public boolean existUser(Map<String, String> userInfo) {
		MysqlUserCon userDAOCon = new MysqlUserCon();
		return userDAOCon.userExist(userInfo);
	}
}
