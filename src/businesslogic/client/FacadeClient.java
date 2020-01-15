package businesslogic.client;

import comlayer.Deserializer;
import comlayer.client.CommunicationClient;
import uinterface.controller.GameFrameController;
import uinterface.controller.LoginController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

import businesslogic.client.domain.unit.Monster;


public class FacadeClient {
	public CommunicationClient chatClient;
	public LoginController loginController;
	public StateGame stateGame = new StateGame();
	public GameFrameController gameFrameController;
	public Deserializer deserializer = new Deserializer();
	private int userID;

	public FacadeClient(LoginController loginController) throws IOException {
		this.chatClient = new CommunicationClient("localhost", 5555, this);
		this.loginController = loginController;
		this.gameFrameController = new GameFrameController(this);
	}

	public void login(String result) {
		if(Integer.valueOf(result) > 0) {
			this.userID = Integer.valueOf(result);
			joinGame();
		}
	}
	public void move(String id, String direction) {
		//try {
			//sendToServer("#move/" + id + "," + direction);	
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	}
	private void launchGame(Monster m, StateGame s) {
		gameFrameController.launchGame(m, s);
	}
	public Monster joinGame() {
		Monster m = null;
		try {
			Random r = new Random();
	    	int res = r.nextInt();
	    	m = new Monster(res, 0,0);
	    	launchGame(m,this.stateGame);
			sendToServer("#joingame/"+String.valueOf(res));
			return m;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return m;
	}
	public void createmonster(String id, String x, String  y) {
		Monster m = new Monster(Integer.valueOf(id), Integer.valueOf(x), Integer.valueOf(y));
		System.out.println(Integer.valueOf(x));
		System.out.println(m);
		System.out.println(stateGame);
		stateGame.addMonster(m);
	}
	public void interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String command = deserializer.extractCommand(action);
		String[] params = deserializer.extractParams(action);
		
		if(deserializer.extractResult(action).equals("")) {
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
