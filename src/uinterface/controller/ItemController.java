package uinterface.controller;

import businesslogic.client.FacadeClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ItemController {
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

    public ItemController() throws IOException {
        this.facade = new FacadeClient();
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
        this.craftSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }

    public void upgradeSection(MouseEvent mouseEvent) throws IOException {
        this.upgradeSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }

    public void buySection(MouseEvent mouseEvent) throws IOException {
        this.buySection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
    }

    public void sellSection(MouseEvent mouseEvent) throws IOException {
        this.sellSection = true;
        Stage newStage = new Stage();
        parent = FXMLLoader.load(getClass().getResource("../views/item/itemSelection.fxml"));
        scene = new Scene(parent, 600, 330);
        newStage.setScene(scene);
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
    }

    public ArrayList<String> itemAvailable(){
        if (this.craftSection){
            return facade.getCraftItem();
        }
        if (this.upgradeSection){
            return facade.getUpgradeItem();
        }
        if (this.buySection){
            return facade.getBuyItem();
        }
        if (this.sellSection){
            return facade.getSellItem();
        }
        return null;
    }
}
