package businesslogic.client;

import comlayer.client.AbstractClient;
import uinterface.ChatIF;

import java.io.IOException;

public class ChatClient extends AbstractClient {
    private ChatIF clientUI;

    public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
        super(host,port);
        this.clientUI = clientUI;
        openConnection();
    }


    public void handleMessageFromServer(Object msg){
        clientUI.display(msg.toString());
    }

    public void handleMessageFromFacade(String msg) {
        try{
            sendToServer(msg);
        } catch (IOException e){
            clientUI.display("Could not send message to server. Terminating client.");
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
