package comlayer.server;// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import businesslogic.client.Facade;
import businesslogic.client.StateGame;
import businesslogic.client.domain.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer
{
    //Class variables *************************************************

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    public Facade facade;

    //Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port, Facade facade)
    {
        super(port, facade);
    }

    public StateGame gameUser(String username){
        for (StateGame game : facade.stateGames){
            for (User user : game.getConnectedUsers()){
                if (username.equals(user.getName())){
                    return game;
                }
            }
        }
        return null;
    }


    //Instance methods ************************************************

    /**
     * This method handles any messages received from the client.
     *
     * @param msg The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, IOException {
        Object o = facade.interpreteAction((String) msg);
        String command = facade.serializer.extractCommand((String)msg);
        String username = facade.serializer.extractParams((String) msg)[0];
        if (command.equals("login") ||command.equals("register")){
            for (User u : facade.connectedUsers){
                if (u.getName().equals(username)){
                    u.setConnectionToClient(client);
                }
            }
            client.sendToClient(msg+"="+o);
        }
        else {
            StateGame game = gameUser(username);
            for (User u : game.getConnectedUsers()){
                u.getConnectionToClient().sendToClient(msg);
            }
        }
    }

    //Class methods ***************************************************

  /*
  public static void main(String[] args)
  {
    int port = DEFAULT_PORT; //Port to listen on

    EchoServer sv = new EchoServer(port);

    try
    {
      sv.listen(); //Start listening for connections
    }
    catch (Exception ex)
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
  */
}
//End of EchoServer class
