package businesslogic.client.management;

import persistlayer.DAO.DAO;
import persistlayer.DAO.DAOFactory;

public class GameManagement {
    private DAO userDAO = DAOFactory.getUserDAO();

    //TODO
    public void signIn(String name, String password) {}
    //TODO
    public void signUp(String name, String password) {}
}
