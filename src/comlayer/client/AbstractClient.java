package comlayer.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public abstract class AbstractClient implements Runnable {
    Socket clientSocket;
    ObjectOutputStream output;
    ObjectInputStream input;

    Thread clientReader;
    boolean readyToStop = false;

    private String host;
    private int port;

    public AbstractClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void openConnection() throws IOException {
        if(isConnected()){
            return;
        }

        try {
            clientSocket = new Socket(host,port);
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            try
            {
                closeAll();
            }
            catch (Exception exc) { }

            throw ex;
        }
        clientReader = new Thread(this);
        readyToStop = false;
        clientReader.start();
    }

    public final void sendToServer(Object msg) throws IOException {
        if (clientSocket == null || output == null)
            throw new SocketException("socket does not exist");

        output.writeObject(msg);
    }

    public final void closeConnection() throws IOException {
        readyToStop= true;

        try {
            closeAll();
        } finally {
            // Call the hook method
            connectionClosed();
        }
    }

    public final boolean isConnected(){
        return clientReader!=null && clientReader.isAlive();
    }

    public final InetAddress getInetAddress(){
        return clientSocket.getInetAddress();
    }

    public final void run(){
        connectionEstablished();
        // The message from the server
        Object msg;
        // Loop waiting for data
        try {
            while(!readyToStop) {
                // Get data from Server and send it to the handler
                // The thread waits indefinitely at the following
                // statement until something is received from the server
                msg = input.readObject();

                // Concrete subclasses do what they want with the
                // msg by implementing the following method
                handleMessageFromServer(msg);
            }
        } catch (Exception exception) {
            if(!readyToStop) {
                try {
                    closeAll();
                } catch (Exception ex) { }
                connectionException(exception);
            }
        } finally {
            clientReader = null;
        }
    }

    protected void connectionClosed(){}
    protected void connectionException(Exception exception){}
    protected void connectionEstablished(){}
    protected abstract void handleMessageFromServer(Object msg) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

    private void closeAll() throws IOException {
        try {
            //Close the socket
            if (clientSocket != null) {clientSocket.close();}

            //Close the output stream
            if (output != null) {output.close();}

            //Close the input stream
            if (input != null) {input.close();}
        } finally {
            // Set the streams and the sockets to NULL no matter what
            // Doing so allows, but does not require, any finalizers
            // of these objects to reclaim system resources if and
            // when they are garbage collected.
            output = null;
            input = null;
            clientSocket = null;
        }
    }

    public final String getHost(){
        return this.host;
    }
    public final int getPort() {
        return this.port;
    }

    final public void setHost(String host) {
        this.host = host;
    }
    final public void setPort(int port) {
        this.port = port;
    }
}
