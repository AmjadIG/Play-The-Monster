package uinterface;

import businesslogic.client.ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientConsole implements ChatIF {

    final public static int DEFAULT_PORT = 5555;
    ChatClient client;

    public ClientConsole(String host, int port) {
        try {
            client= new ChatClient(host, port, this);
        } catch(IOException exception) {
            System.out.println("Error: Can't setup connection!"
                    + " Terminating client.");
            System.exit(1);
        }
    }

    public void accept() {
        try {
            BufferedReader fromConsole =
                    new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                message = fromConsole.readLine();
                client.handleMessageFromFacade(message);
            }
        } catch (Exception ex) {
            System.out.println("Unexpected error while reading from console!");
        }
    }
    public void display(String message) {
        System.out.println("> " + message);
    }

    public static void main(String[] args) {
        String host = "";
        int port = 0;  //The port number

        try {
            host = args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            host = "localhost";
        }
        ClientConsole chat= new ClientConsole(host, DEFAULT_PORT);
        chat.accept();  //Wait for console data
    }
}
