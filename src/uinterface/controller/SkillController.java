package uinterface.controller;

import businesslogic.client.FacadeClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SkillController extends Application {

    FacadeClient facade;

    Stage stage = null;
    Parent parent = null;
    Scene scene = null;

    public static void main(String args[]){
        Application.launch(args);
    }


    public void seeCharacteristics() throws IOException {
        facade.sendToServer("#@seeCharacteristics/");
    }

    public void editCharacteristics(int idStat,int nbPoints) throws IOException {
        facade.sendToServer("#@editCharacteristics/"+idStat+","+nbPoints);
    }

    public void selectAbility(String ability) throws IOException {
        facade.sendToServer("#@selectAbility/"+ability);
    }

    public void lockAbility(String ability) throws IOException {
        facade.sendToServer("#@lockAbility/"+ability);
    }

    public void castAbility(String ability) throws IOException {
        facade.sendToServer("#@castAbility/"+ability);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void abilitySelection(MouseEvent mouseEvent) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("../views/skills/abilitySelection.fxml"));
        scene = new Scene(parent, 600, 330);
        stage.setScene(scene);
        stage.show();
    }

    public void characteristicsSelection(MouseEvent mouseEvent) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("../views/dungeon/chooseMinion.fxml"));
        scene = new Scene(parent, 600, 330);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(MouseEvent mouseEvent) {
        stage.close();
    }

    public void goBack(MouseEvent mouseEvent){
        this.exit(mouseEvent);

    }
}
