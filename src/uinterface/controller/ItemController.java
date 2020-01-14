package uinterface.controller;

import businesslogic.client.FacadeClient;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemController extends Application {
    Parent parent = null;
    Scene scene = null;

    FacadeClient facade;
    @FXML
    private boolean craftSection = false;
    @FXML
    private boolean upgradeSection = false;
    @FXML
    private boolean buySection = false;
    @FXML
    private boolean sellSection = false;
    @FXML
    private ListView<String> listItem = new ListView<String>();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            parent = FXMLLoader.load(DungeonController.class.getResource("../views/item/itemMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(parent, 600, 330);
        stage.setTitle("BlackSmith");
        stage.setScene(this.scene);
        stage.show();
    }


    public static void main(String args){
        Application.launch(args);
    }

    public void buyItem(int idItem) throws IOException {
        facade.sendToServer("#@buyItem/"+idItem);
    }

    public void sellItem(int idItem) throws IOException {
        facade.sendToServer("#@sellItem/"+idItem);
    }

    public void craftItem(int idItem) throws IOException {
        facade.sendToServer("#@craftItem/"+idItem);
    }

    public void upgradeItem(int idItem) throws IOException {
        facade.sendToServer("#@upgradeItem/"+idItem);
    }

    //TODO
    public void getItemResponse(int idCommand, boolean response){
        switch (idCommand){
            case 1 :
                if(response){
                    System.out.println("Item bought !!");
                } else {
                    System.out.println("Item not available... Not enough money T-T");
                }
                break;
            case 2 :
                if(response){
                    System.out.println("Item sold !!");
                } else {
                    System.out.println("Item unsellable ??");
                }
                break;
            case 3 :
                if(response){
                    System.out.println("Item crafted !!");
                } else {
                    System.out.println("You don't have all the components !");
                }
                break;
            case 4 :
                if(response){
                    System.out.println("Item upgraded !!");
                } else {
                    System.out.println("Item not available..");
                }
                break;
        }
    }

    public void craftSection(MouseEvent mouseEvent) throws IOException {
        //listItem.getItems().setAll("Potion","Wooden sword","Iron shield");
        this.craftSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void upgradeSection(MouseEvent mouseEvent) throws IOException {
        //listItem.getItems().setAll("Wooden sword","Iron shield");
        this.upgradeSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void buySection(MouseEvent mouseEvent) throws IOException {
        //listItem.getItems().setAll("Potion","Fire sword","Platinum shield");
        this.buySection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void sellSection(MouseEvent mouseEvent) throws IOException {
        listItem.getItems().setAll("Wooden sword");
        this.sellSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        this.craftSection = false;
        this.upgradeSection = false;
        this.buySection = false;
        this.sellSection = false;

        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemMenu.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
        newStage.show();
    }

    public void itemAvailable() throws IOException {
        if (this.craftSection){
            facade.sendToServer("#getCraftItem/");
        }
        if (this.upgradeSection){
            facade.sendToServer("#getUpgradeItem/");
        }
        if (this.buySection){
            facade.sendToServer("#getBuyItem/");
        }
        if (this.sellSection){
            facade.sendToServer("#getSellItem/");
        }
    }
}
