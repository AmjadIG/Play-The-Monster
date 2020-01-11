package uinterface.controller;

import businesslogic.client.Facade;

import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class GameController {
    Facade facade;

    public GameController(){}

    public void signIn(String name, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#signIn/"+name+","+hash(password));
    }

    public void signUp(String name, String password) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#signUp/"+name+","+hash(password));
    }

    //TODO (Should use bcrypt or whatever but don't had the time
    public String hash(String password){
        return "Bfrjvn4543Fdf"+password+"jvn4B656vfdER";
    }


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
