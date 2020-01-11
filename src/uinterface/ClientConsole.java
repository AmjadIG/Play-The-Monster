package uinterface;

import businesslogic.client.ChatClient;

public class ClientConsole implements ChatIF {
    private int DEFAULT_PORT = 5555;
    private ChatClient client;

    ClientConsole(String host, int port){}

    public void accept(){}

    @Override
    public void display(String message) {

    }

    public static void main(String args[]){}
}
