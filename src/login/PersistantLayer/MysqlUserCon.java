package login.PersistantLayer;


import login.BusinessLogic.User;

import java.sql.*;
import java.util.Map;


class MysqlUserCon {


    public User getBy(Map<String, String> userInfo) {

        String uid = userInfo.get("login");
        String pwd = userInfo.get("password");
        System.out.println(uid);
        System.out.println(pwd);
        Connection conn = null;
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PTM", "root", "root");
            Statement stmt = con.createStatement();
            String query = "select * from users where name = '" + uid + "' and password = '" + pwd + "';";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);


            if (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2));
            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        }
        return user;
    }
}