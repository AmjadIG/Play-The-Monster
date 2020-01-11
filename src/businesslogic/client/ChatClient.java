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
    public void handleMessageFromFacade(Object msg){}
    public void quit(){}
}
