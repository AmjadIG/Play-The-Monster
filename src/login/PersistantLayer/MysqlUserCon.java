package login.PersistantLayer;

import java.sql.*;
import java.util.Map;


class MysqlUserCon {
    public boolean userExist(Map<String, String> userInfo) {
        boolean uExist = false;
        String uid = userInfo.get("login");
        String pwd = userInfo.get("password");
        System.out.println(uid);
        System.out.println(pwd);
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PTM", "root", "root");
            Statement stmt = con.createStatement();
            String query = "select * from users where name = '" + uid + "' and password = '" + pwd + "';";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                uExist = true;
            }
            con.close();

        } catch (SQLException | ClassNotFoundException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        }
        System.out.println(uExist);
        return uExist;
    }
}