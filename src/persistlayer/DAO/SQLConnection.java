package persistlayer.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	
	private String URL;
	
	private static Connection con;
	private static SQLConnection scon = new SQLConnection();
	private SQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		new SQLConnection(SQLInfo.TYPE_BASE, SQLInfo.HOST, SQLInfo.NAME_BASE, SQLInfo.PORT, SQLInfo.USER, SQLInfo.PASSWORD);
	}
	private SQLConnection(String typeBase ,String host, String nameBase, String port, String user, String password){

		this.URL = "jdbc:" + typeBase + "://" + host + ":" + port + "/" + nameBase;
		try {
			con = DriverManager.getConnection(URL, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance() {
		if(con == null) {
			new SQLConnection();
		}
		return con;
	}
	
}
