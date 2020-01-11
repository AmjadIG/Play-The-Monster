package uinterface.controller;

import businesslogic.client.Facade;

import java.lang.reflect.InvocationTargetException;

public class GameController {

    Facade facade;

    public GameController(){}

    public void createGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#createGame/");
    }

    public void joinGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#joinGame/");
    }

    public void loadGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#loadGame/");
    }

    public void saveGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#saveGame/");
    }

    //TODO
    public void getGameResponse(){}

}
