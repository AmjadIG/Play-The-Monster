package uinterface.controller;

import businesslogic.client.Facade;

import java.lang.reflect.InvocationTargetException;

public class DungeonController {
    Facade facade;

    public void changeDungeonName(String dungeonName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#changeDungeonName/"+dungeonName);
    }

    public void upgradeDungeon() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#upgradeDungeon/");
    }

    public void setColorDungeon(String color) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#setColorDungeon/"+color);
    }

    public void interactWithMinion(int idMinion) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#interactWithMinion/"+idMinion);
    }

    //TODO
    public void getDungeonResponse(){}

}
