package comlayer.client;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.net.InetAddress;
import java.net.Socket;

public abstract class AbstractClient {
    Socket clientSocket;
    ObjectOutputStream output;
    ObjectInputStream input;

    Thread clientReader;
    boolean readyToStop = false;

    private String host;
    private int port;

    public AbstractClient(String host, int port){}

    public void openConnection(){}
    public void sendToServer(Object msg){}
    public void closeConnection(){}

    public boolean isConnected(){return true;}
    public InetAddress getInetAddress(){return null;}

    public void run(){}

    protected void connectionClosed(){}
    protected void connectionException(Exception exception){}
    protected void connectionEstablished(){}
    protected void handleMessageFromServer(Object msg){}

    private void closeAll(){}

    protected String getHost(){
        return this.host;
    }

    protected int getPort() {
        return this.port;
    }
}
