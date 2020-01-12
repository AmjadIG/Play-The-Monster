package uinterface.controller;

import businesslogic.client.Facade;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GameController extends Application {
    Facade facade;
    Parent parent = null;
    Scene scene = null;

    //Sign In Attributes
    @FXML
    TextField name = null;
    @FXML
    TextField password = null;

    //Register Attributes
    @FXML
    TextField playerName = null;
    @FXML
    TextField password1 = null;
    @FXML
    TextField password2 = null;
    @FXML
    TextField email = null;

    @Override
    public void start(Stage arg0) throws Exception {
        try {
            parent = FXMLLoader.load(GameController.class.getResource("../views/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(parent, 600, 330);
        name = (TextField) scene.lookup("#loginTextField");
        password = (TextField) scene.lookup("#pwdTextField");
        arg0.setTitle("Login");
        arg0.setScene(this.scene);
        arg0.show();
    }

    public static void main(String args[]){
        Application.launch(args);
    }

    public void signIn(MouseEvent mouseEvent) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        facade.interpreteAction("#signIn/"+name+","+hash(password.getText()));
    }

    public void signUp(MouseEvent mouseEvent) throws IOException {
        changeScene("../views/loginRegister.fxml");
    }

    public void register(MouseEvent mouseEvent) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        String pn = playerName.getText();
        String p1 = hash(password1.getText());
        String p2 = hash(password2.getText());
        String mail = email.getText();

        facade.interpreteAction("#signup/"+pn+","+p1+","+p2+","+mail);
    }

    //(We Should use BCrypt or any encryption script to encrypt our password (but lack of time)
    public String hash(String password){
        return "Bfrjvn4543Fdf"+password+"jvn4B656vfdER"; //So we added salt (at the end and at the beginning)
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

    public void changeScene(String fxml) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource(fxml));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);

        playerName = (TextField) scene.lookup("#playerName");
        password1 = (TextField) scene.lookup("#password1");
        password2 = (TextField) scene.lookup("#password2");
        email = (TextField) scene.lookup("#email");

        newStage.show();
    }
}
