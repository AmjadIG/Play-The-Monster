package uinterface.controller;

import businesslogic.server.FacadeServer;

import java.lang.reflect.InvocationTargetException;

public class SkillController {

    FacadeServer facadeServer;

    public void addSkillPoints(int idStat,int nbPoints) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facadeServer.interpreteAction("#addSkillPoints/"+idStat+","+nbPoints);
    }

    public void castAbility(String spell) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facadeServer.interpreteAction("#@castAbility/"+spell);
    }
}
