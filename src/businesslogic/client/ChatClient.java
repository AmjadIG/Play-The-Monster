package businesslogic.client;

import comlayer.client.AbstractClient;
import uinterface.ChatIF;

public class ChatClient extends AbstractClient {
    private ChatIF clientUI;

    public ChatClient(ChatIF clientUI) {
        super("root",5555);
        this.clientUI = clientUI;
    }


    public void handleMessageFromServer(Object msg){}
    public void handleMessageFromFacade(Object msg){}
    public void quit(){}
}
