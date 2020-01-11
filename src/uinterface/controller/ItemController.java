package uinterface.controller;

import businesslogic.client.Facade;

public class ItemController {

    Facade facade;

    public ItemController(){}

    public void buyItem(int idItem){
        String command = "#class#buyItem/"+idItem; //TODO (Classname ?) Same for the other methods
        facade.serializer.formating(command);
    }

    public void sellItem(int idItem){
        String command = "#class#sellItem/"+idItem;
        facade.serializer.formating(command);
    }

    public void craftItem(int idItem){
        String command = "#class#craftItem/"+idItem;
        facade.serializer.formating(command);
    }

    public void upgradeItem(int idItem){
        String command = "#class#craftItem/"+idItem;
        facade.serializer.formating(command);
    }

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
}
