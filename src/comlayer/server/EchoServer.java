package comlayer.server;// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import businesslogic.domain.unit.Monster;
import businesslogic.server.FacadeServer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EchoServer extends AbstractServer
{
    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    public FacadeServer facadeServer;
    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port)
    {
    	super(port);
    	this.facadeServer = new FacadeServer(this);
    }
    /**
     * This method handles any messages received from the client.
     *
     * @param msg The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, IOException {
    	Object o = facadeServer.interpreteAction((String)msg);
        String command = facadeServer.serializer.extractCommand((String)msg);
      
        if (command.equals("login") ||command.equals("register")){
            client.sendToClient(o);
            if(o.equals(true)) {
            	client.setInfo("user", facadeServer.connectedUsers.get(facadeServer.connectedUsers.size()-1));
            }
        }
        if(command.equals("joingame")) {
        	
        	String id = facadeServer.serializer.extractParams((String)msg)[0];
    		facadeServer.stateGame.addMonster(new Monster(Integer.valueOf(id),0,0));
        	sendToOthers("#createmonster/"+id+",0,0",client);
        	for(Monster m : facadeServer.stateGame.getMonsters()) {
        		if (!String.valueOf(m.getIdUnit()).equals(id)) 
        			client.sendToClient("#createmonster/"+m.getIdUnit()+","+m.getPosX()+","+m.getPosY());
        		}
        	}
        if(command.equals("move")) {
        	System.out.println("recu");
        	sendToOthers((String)msg, client);
        }
     	
    }
    
    
    public void sendToOthers(Object msg, ConnectionToClient client) {
    	Thread[] clientThreadList = getClientConnections();
        for (int i=0; i<clientThreadList.length; i++)
        {
          try
          {
        	  if(!((ConnectionToClient)clientThreadList[i]).equals(client)) {
        		  ((ConnectionToClient)clientThreadList[i]).sendToClient(msg);
        	  }
          }
          catch (Exception ex) {
        	  //ex.printStackTrace();
          }
        }
    }
}

