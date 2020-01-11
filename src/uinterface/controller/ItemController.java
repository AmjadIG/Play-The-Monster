package uinterface.controller;

import businesslogic.client.Facade;

import java.lang.reflect.InvocationTargetException;

public class ItemController {

    Facade facade;

    public ItemController(){}

    public void buyItem(int idItem) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#buyItem/"+idItem);
    }

    public void sellItem(int idItem) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#sellItem/"+idItem);
    }

    public void craftItem(int idItem) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#craftItem/"+idItem);
    }

    public void upgradeItem(int idItem) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        facade.interpreteAction("#class#craftItem/"+idItem);
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
