package comlayer.server;

import businesslogic.client.Facade;
import businesslogic.client.domain.User;
import businesslogic.client.domain.unit.Monster;

import java.io.IOException;

public class Test {

    public static void main() throws IOException {
        Facade f = new Facade();
        int port = 5555;
        EchoServer e = new EchoServer(port,f);
        f.setEchoServer(e);
        Monster m = new Monster();
        ConnectionToClient connectionToClient = new ConnectionToClient("");
        User user1 = new User(m,1,);

    }
}
