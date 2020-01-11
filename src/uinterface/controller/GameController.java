package uinterface.controller;

import businesslogic.client.Facade;

import java.lang.reflect.InvocationTargetException;

public class GameController {

    Facade facade;

    public GameController(){}

    public void createGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#createGame/");
    }

    public void joinGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#joinGame/");
    }

    public void loadGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#loadGame/");
    }

    public void saveGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#saveGame/");
    }

    //TODO
    public void getGameResponse(){}

}
