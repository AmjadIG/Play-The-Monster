package persistlayer.DAO;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
	/**
	 * Get the object corresponding to the provided id.
	 * @param id of the object in the database
	 * @return the object corresponding to the table row 
	 */
	T get(long id);
	/**
	 * Get one or more row satisfying all the conditions in the provided Map.
	 * @param <A> is the type of the value
	 * @param keyVal is a Map object. The key is the column name and the value is the value of the column
	 * @return a List of T, with T being the object corresponding to the table row
	 */
	<A> List<T> getBy(Map<String, ? extends A> keyVal);
	/**
	 * Get all the rows of the table
	 * @return a List of T, with T being the object corresponding to the table row
	 */
	List<T> getAll();
	
	/**
	 * Get the next auto increment of the table
	 * @return integer representing the next auto increment of the table
	 */
	int getNextAutoIncrement();
	/**
	 * Insert or update an object in the table.
	 * @param t : the object to save in the table
	 * @return a boolean value : true if the update count is > 0, false otherwise.
	 */
	boolean save(T t);
	
	/**
	 * Delete an object from the table
	 * @param t : the object to delete from the table
	 * @return a boolean value : true if the update count is > 0, false otherwise.
	 */
	boolean delete(T t);	
}
