package uinterface.controller;

import businesslogic.client.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class GameController extends Application {
    Facade facade;
    Stage stage = null;
    Scene scene = null;
    Button submitUp = null;
    Button submitIn = null;
    TextField name = null;
    TextField password = null;

    public GameController(){

        Parent root = null;
        try {
            root = FXMLLoader.load(GameController.class.getResource("/uinterface/views/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(root, 600, 330);
        name = (TextField) scene.lookup("#loginTextField");
        password = (TextField) scene.lookup("#pwdTextField");
        submitIn = (Button) scene.lookup("#loginBtn");
        /*
        submitIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    signIn(name.getText(), password.getText());
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    @Override
    public void start(Stage arg0) throws Exception {
        arg0.setTitle("Login");
        arg0.setScene(this.scene);
        arg0.show();
    }

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
        facade.interpreteAction("#@createGame/");
    }

    public void joinGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#@joinGame/");
    }

    public void loadGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#@loadGame/");
    }

    public void saveGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#saveGame/");
    }

    //TODO
    public void getGameResponse(){}

    public static void main(String args[]){
        GameController displayControl = new GameController();
        Application.launch(args);
    }

}
