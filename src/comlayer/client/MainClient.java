package comlayer.client;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) throws IOException {
		ChatClient c = new ChatClient("localhost", 5555);
		c.handleMessageFromFacade("#login/rayan,rayanmdp");
	}

}
