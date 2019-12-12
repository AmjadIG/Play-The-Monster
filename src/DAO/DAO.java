package DAO;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
	T get(long id);
	T getBy(Map<String, String> keyVal);
	boolean existUser(Map<String, String> userInfo);
	List<T> getAll();
	void save(T t);
	void delete(T t);
	void update(T t, String[] params);
	
}
