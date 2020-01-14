package comlayer.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import businesslogic.client.FacadeClient;

public class CommunicationClient extends AbstractClient {
	FacadeClient facadeClient;
	
    public CommunicationClient(String host, int port, FacadeClient facade) throws IOException {
        super(host,port);
        this.facadeClient = facade;
        openConnection();
    }

    public void handleMessageFromServer(Object msg) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	facadeClient.interpreteAction((String) msg);
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
