package businesslogic.client.management;

import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;

import java.util.HashMap;
import java.util.Map;

public class GameManagement {
    private DAO userDAO = DAOFactory.getUserDAO();

    //TODO
    public void signIn(String name, String password) {
        Map map = new HashMap(); map.put("name",name); map.put("password",password);

        if (userDAO.getBy(map).equals(null)){

        }
    }
}
