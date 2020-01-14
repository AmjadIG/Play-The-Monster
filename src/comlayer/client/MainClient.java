package comlayer.client;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) throws IOException {
		CommunicationClient c = new CommunicationClient("localhost", 5555);
		c.handleMessageFromFacade("#login/rayan,rayanmdp");
	}

}
