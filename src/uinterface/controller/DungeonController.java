package uinterface.controller;

import businesslogic.client.FacadeClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DungeonController {
    @FXML
    TextField dungeonName = null;
    @FXML
    ColorPicker dungeonColor = null;

    FacadeClient facade;

    Parent parent = null;
    Scene scene = null;


    //TODO
    public void getDungeonResponse(){}

    public void changeNameDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/changeNameDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        dungeonName = (TextField) scene.lookup("#dungeonName");
    }

    public void setColorDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/setColorDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        dungeonColor = (ColorPicker) scene.lookup("#dungeonColor");
    }

    public void upgradeDungeon(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/upgradeDungeon.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }

    public void tryUpgrade(MouseEvent mouseEvent) throws IOException {
        facade.sendToServer("#upgradeDungeon/");
    }

    public void chooseMinionSection(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/chooseMinion.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/dungeonMenu.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }
}
