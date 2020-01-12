package businesslogic.client;

import comlayer.client.AbstractClient;

import java.io.IOException;

public class ChatClient extends AbstractClient {
	FacadeClient facadeClient;

    public ChatClient(String host, int port) throws IOException {
        super(host,port);
        openConnection();
    }


    public void handleMessageFromServer(Object msg){


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
