package businesslogic.client;

import comlayer.client.AbstractClient;
import uinterface.ChatIF;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ChatClient extends AbstractClient {
	FacadeClient facadeClient;

    public ChatClient(String host, int port) throws IOException {
        super(host,port);
        openConnection();
    }


    public void handleMessageFromServer(Object msg) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	facadeClient.interpreteAction( (String) msg);
    }

    public void handleMessageFromFacade(String msg) {
        try{
            sendToServer(msg);
        } catch (IOException e){
            quit();
        }
    }

    public void quit(){
        try {
            closeConnection();
        } catch (IOException e){

        }
        System.exit(0);
    }
}
