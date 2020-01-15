package uinterface.controller;

import businesslogic.client.FacadeClient;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DungeonController extends Application {
    @FXML
    TextField dungeonName = null;
    @FXML
    ColorPicker dungeonColor = null;


    FacadeClient facade;

    Parent parent = null;
    Scene scene = null;

    @Override
    public void start(Stage arg0) throws Exception {

        try {
            parent = FXMLLoader.load(DungeonController.class.getResource("../views/dungeon/dungeonMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(parent, 600, 330);
        dungeonName = (TextField) scene.lookup("#dungeonName");
        dungeonColor = (ColorPicker) scene.lookup("#dungeonColor");
        arg0.setTitle("Dungeon");
        arg0.setScene(this.scene);
        arg0.show();
    }

    public static void main(String args[]){
        Application.launch(args);
    }

    public void changeNameDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/changeNameDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        dungeonName = (TextField) scene.lookup("#dungeonName");
        newStage.show();
        facade.sendToServer("#@changeDungeonName/"+dungeonName.getText());
    }

    public void setColorDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/setColorDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        dungeonColor = (ColorPicker) scene.lookup("#dungeonColor");
        newStage.show();
        facade.sendToServer("#@changeDungeonColor/"+dungeonColor.toString());
    }

    public void upgradeDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/upgradeDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void tryUpgrade(MouseEvent mouseEvent) throws IOException {
        facade.sendToServer("#upgradeDungeon/");
    }

    public void chooseMinionSection(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/chooseMinion.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/dungeonMenu.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }
}
