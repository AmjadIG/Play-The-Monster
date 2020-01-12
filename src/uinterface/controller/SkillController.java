package uinterface.controller;

import businesslogic.client.Facade;

import java.lang.reflect.InvocationTargetException;

public class SkillController {

    Facade facade;

    public void addSkillPoints(int idStat,int nbPoints) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#addSkillPoints/"+idStat+","+nbPoints);
    }

    public void castAbility(String spell) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#@castAbility/"+spell);
    }
}
