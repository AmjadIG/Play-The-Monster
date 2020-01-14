package persistlayer.DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAO<T> implements DAO<T> {
	private Connection sql;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMetaData;
	private T currentObject;
	private String tableName, query, genericTypeName;
	private Class<?> constructorParams[]; 
	public AbstractDAO(){
		this.sql =  SQLConnection.getInstance();
		this.genericTypeName = getGenericTypeName();
		this.tableName = getTableName();
		this.constructorParams = getConstructorParams();
	}
	@Override
	public T get(long id) {
		HashMap<String, Long> attr = new HashMap<>();
		attr.put("id", (Long) id);
		
		List<T> res = getBy(attr);
		if(res.size() != 0) {
			return res.get(0);
		}
		return null;
		
	}
	@Override
	public List<T> getAll(){
		HashMap<String, Integer> c = new HashMap<String, Integer>();
		c.put("1",1);
		return getBy(c);
	}
	@Override
	public <A> List<T> getBy(Map<String,? extends A> keyVal) {
		String[] conditions = new String[keyVal.size()];
		List<T> res = new ArrayList<>();
		query = "SELECT * FROM " + tableName + " WHERE ";
		int j = 0;
		for ( Map.Entry<?,?> entry : keyVal.entrySet() ) {
		    Object key = entry.getKey();
		    Object value = entry.getValue();
		    if(value.getClass().getSimpleName().equals("String")) {
		    	conditions[j] = key + " = '" + value + "'";
		    } else {
		    	conditions[j] = key + " = " + value;
		    }
		    
		    j++;
		}
		String fullCondition = String.join(" AND ", conditions);
		query += fullCondition;
		System.out.println(query);
		
		try {
			statement = sql.createStatement();
			resultSet = statement.executeQuery(query);
			resultSetMetaData = resultSet.getMetaData();
			while(resultSet.next()) {
				currentObject = getInstance(constructorParams, getRowFromResultSet(resultSet));
				res.add(currentObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return res;
	}
	@Override
	public int getNextAutoIncrement() {
		int res = 0;
		query = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'PTM' AND TABLE_NAME='"+tableName+"'";
		System.out.println(query);
		try {
			statement = sql.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				res = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return res;
	}
	@Override
	public boolean save(T t) {
		boolean res = false;
		query = "INSERT INTO " + tableName + "(" + generateFieldNameClause(t) +
				") VALUES (" + generateFieldValueClause(t) + ") ON DUPLICATE KEY UPDATE " + generateOnDuplicateUpdateClause(t) ;
		System.out.println(query);
		try {
			statement = sql.createStatement();
			statement.execute(query);
			res = statement.getUpdateCount() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public boolean delete(T t) {
		boolean res = false;
		query = "DELETE FROM " + tableName + " WHERE " + generateWhereEqualsClause(t);
		System.out.println(query);
		try {
			statement = sql.createStatement();
			statement.execute(query);
			res = statement.getUpdateCount() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Generate the WHERE < field1 name = field1 value > [.. AND < field2 name = field2 value > ] clause part
	 * @param t : the object to generate the where from
	 * @return String
	 */
	private String generateWhereEqualsClause(T t) {
		String[] fNames = generateFieldNameClause(t).split(",");
		String[] fValues = generateFieldValueClause(t).split(",");
		List<String> clauses = new ArrayList<String>();
		for(int i = 0; i < fNames.length; i++) {
			clauses.add(fNames[i] + "=" + fValues[i]);
		}
		return String.join(" AND ", clauses);
	}
	
	/**
	 * Generate the ON DUPLICATE KEY UPDATE clause part of an INSERT query (e.g < field1 name = field1 value >,[< ...field2 name = field2 value >]  )
	 * @param t
	 * @return
	 */
	private String generateOnDuplicateUpdateClause(T t) {
		String[] fNames = generateFieldNameClause(t).split(",");
		String[] fValues = generateFieldValueClause(t).split(",");
		List<String> clauses = new ArrayList<String>();
		for(int i = 0; i < fNames.length; i++) {
			clauses.add(fNames[i] + "=" + fValues[i]);
		}
		return String.join(",", clauses);
	}
	
	/**
	 * Get the row on which the ResultSet is currently pointing
	 * @param rs : the ResultSet
	 * @return Object[]
	 * @throws SQLException
	 */
	private Object[] getRowFromResultSet(ResultSet rs) throws SQLException {
		int colNb = resultSetMetaData.getColumnCount();
		Object[] colValues = new Object[colNb];
		for(int i = 1; i < colNb; i++) {	
			colValues[i-1] = resultSet.getObject(i);
		}
		return colValues;
	}
	/**
	 * Generate the VALUES clause part of an INSERT query (e.g : < field1 value >,[< ..field2 value >] )
	 * @param t : the object from which the clause is generated
	 * @return String
	 */
	private String generateFieldValueClause(T t) {
		List<String> vals = new ArrayList<>();
		for(Field f : t.getClass().getFields()) {
			try {
				if(f.getType().getSimpleName().equals("String")) {
					vals.add("'" + f.get(t).toString()+ "'");
				} else {
					vals.add(f.get(t).toString());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return String.join(",", vals);
	}
	/**
	 * Generate the INSERT INTO clause part of an INSERT query (e.g : < field1 name >,[< ..field2 name >] )
	 * @param t : the object from which the clause is generated
	 * @return String
	 */
	private String generateFieldNameClause(T t) {
		List<String> vals = new ArrayList<>();
		for(Field f : t.getClass().getFields()) {
			try {
				vals.add(f.getName());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return String.join(",", vals);
	}
	
	/**
	 * Get the full type name (with package) of the generic class
	 * @return String
	 */
	private String getGenericTypeName() {
		String[] type = getClass().getGenericSuperclass().getTypeName().split("<");
		return type[1].split(">")[0];
	}
	
	/**
	 * Get an instance of T using specified constructor parameters with corresponding values (have to be in same order)
	 * @param constructorParams is an array of Class
	 * @param colValues is an array of Object
	 * @return an instance of T
	 */
	private T getInstance(Class<?>[] constructorParams, Object[] colValues) {
		T res = null;
		try {
			res = (T) Class.forName(genericTypeName).getConstructor(constructorParams).newInstance(colValues);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return res;
	}
	
	/**
	 * Get all the constructor parameters's classes
	 * Must be in the same order as the concrete constructor
	 * @return An array of Class<?> objects corresponding to the underlying object constructor of the DAO.
	 * Must be in the same order as the concrete constructor
	 */
	protected abstract Class<?>[] getConstructorParams();
	
	/**
	 * Get the name of the table corresponding to the object
	 * @return
	 */
	protected abstract String getTableName();
}
