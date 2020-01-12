package comlayer.server;

public class ServerMain {

	public static void main(String[] args) {
		EchoServer es = new EchoServer(5555);
		try
	    {
	      es.listen(); //Start listening for connections
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
