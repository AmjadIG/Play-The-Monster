package businesslogic.client;

import comlayer.Deserializer;
import comlayer.client.CommunicationClient;
import uinterface.controller.GameController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FacadeClient {
	public CommunicationClient chatClient;
	public GameController gameController;
	public StateGame stateGame;
	public Deserializer deserializer = new Deserializer();
	private int userID;

	public FacadeClient(GameController gameController) throws IOException {
		this.chatClient = new CommunicationClient("localhost", 5555, this);
		this.gameController = gameController;
	}

	public void login(String result) {
		if(Integer.valueOf(result) > 0) {
			userID = Integer.valueOf(result);
			gameController.launchGame();
		}
	}
	public void interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String command = deserializer.extractCommand(action);
		String[] params = deserializer.extractParams(action);
		System.out.println(action);
		if(deserializer.extractResult(action).equals(null)) {
			delegateTo(command, params);
		} else {
			login(deserializer.extractResult(action));
		}
	}
	public Object delegateTo(String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?>[] typeParametres = null;
		if (params != null) {
			typeParametres = new Class[params.length];
			for (int i = 0; i < params.length; ++i) {
				typeParametres[i] = params[i].getClass();
			}
		}
		Method m = getClass().getMethod(command, typeParametres);
		Object res = m.invoke(this, params);
		return res;
	}
	public void sendToServer(String action) throws IOException {
		chatClient.handleMessageFromFacade(action);
	}
	public void setStateGame(StateGame sg) {
		this.stateGame = sg;
	}
}
