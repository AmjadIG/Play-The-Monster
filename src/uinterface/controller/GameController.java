package uinterface.controller;

import businesslogic.client.FacadeClient;
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
    FacadeClient facade;
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

    public GameController() throws IOException {
    	this.facade = new FacadeClient();
    }
    //Start called by main
    @Override
    public void start(Stage arg0) throws Exception {
    	
        try {
            parent = FXMLLoader.load(GameController.class.getResource("../views/game/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(parent, 600, 330);
        name = (TextField) scene.lookup("#loginTextField");
        password = (TextField) scene.lookup("#pwdTextField");
        arg0.setTitle("Play");
        arg0.setScene(this.scene);
        arg0.show();
    }

    //Application Launcher
    public static void main(String args[]){
        Application.launch(args);
    }

    //Changing Login View to register view
    public void signUp(MouseEvent mouseEvent) throws IOException {
        changeScene("../views/game/loginRegister.fxml");
    }

    public void register(MouseEvent mouseEvent) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        //CharSequence mailFormat = new StringBuffer("@gmail.com");

        String pn = playerName.getText();
        String p1 = hash(password1.getText());
        String p2 = hash(password2.getText());
        //String mail = email.getText();
        if(!pn.isEmpty() && !p1.isEmpty() && !p2.isEmpty()){
            if(p1.equals(p2)){
            	facade.sendToServer("#signup/"+pn+","+p1);
//                if(mail.contains(mailFormat)){
//                    facade.sendToServer("#signup/"+pn+","+p1);
//                } else {
//                    System.out.println("Please enter an available gmail address.");
//                }
            } else {
                System.out.println("The two passwords should correspond one with another.");
            }
        } else {
            System.out.println("Please fill all the entries");
        }
    }

    //Hash the user password
    //Note : We Should use BCrypt or any encryption script to encrypt our password (but lack of time)
    public String hash(String password){
        return "Bfr"+password+"jvER"; //So we added salt (at the end and at the beginning)
    }

    //Invoke Facade methods
    public void signIn(MouseEvent mouseEvent) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, IOException {
    	
    	String nameS = name.getText();
        String passwordS = password.getText();
        if(nameS.isEmpty() || passwordS.isEmpty()){
            System.out.println("Please fill all the entries.");
        } else {
            facade.sendToServer("#login/"+nameS+","+hash(passwordS));
        }
    }
    public void createGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        facade.sendToServer("#createGame/");
    }
    public void joinGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        facade.sendToServer("#joinGame/");
    }
    public void loadGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        facade.sendToServer("#loadGame/");
    }
    //Invoke Facade method when Client ask to save (From MapUI)
    public void saveGame() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        facade.sendToServer("#saveGame/");
    }

    //Invoked when Facade call it
    public void mainMenu() throws IOException{
        changeScene("../views/game/mainMenu.fxml");
    }

    public void changeScene(String fxml) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource(fxml));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);

        if(fxml.equals("../views/game/loginRegister.fxml")) {
        	playerName = (TextField) scene.lookup("#playerName");
            password1 = (TextField) scene.lookup("#password1");
            password2 = (TextField) scene.lookup("#password2");
            email = (TextField) scene.lookup("#email");
        }

        newStage.show();
    }
}
