package businesslogic.client;

import businesslogic.client.domain.User;
import comlayer.Serializer;
import comlayer.server.*;
import persistlayer.DAO.DAOFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FacadeClient {


	public ChatClient chatClient;



	public Serializer serializer = new Serializer();
	private String lastStringAction;

	public FacadeClient() {}



	public Object interpreteAction(String action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(serializer.isGameStateModification(action)) {
			serializer.formating(action);
			return delegateTo(action, serializer.getCommand(),serializer.getParams());
		}else if(serializer.isDatabaseModification(action)) {
			serializer.formating(action);
			return delegateTo(action, serializer.getCommand(),serializer.getParams());
		}else {
			System.out.println("commande incorrecte");
			return null;
		}
	}

	public void Delegate2(String action, String command, String[] params){

		if(command.equals("signUp")){
//			DAOFactory df = getDaoFactory();
//			UserDAO userDAO = (UserDAO) df.getUserDAO();
//			userDAO.save();
		}else if(command.equals("signIn")){

		}
	}
	public Object delegateTo(String action, String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String className = findClassName(command);
		System.out.println(className);
		if(className != null ) {
			Class<?> classe = Class.forName(className);
			Object o = classe.getDeclaredConstructor().newInstance(null);
			Class[] typeParametres = null;
			if (params != null) {
				typeParametres = new Class[params.length];
				for (int i = 0; i < params.length; ++i) {
					typeParametres[i] = params[i].getClass();
				}
			}
			Method m = classe.getMethod(command, typeParametres);
			Object res = m.invoke(o,params);
			return action + "=" + res;
		}else{
			System.out.println("j'suis une façade pas Dieu ! la commande : "+command+" n'est pas dans une classe");
			return null;
		}
	}

	public String findClassName(String command) {
		if (command.equals("deleteFirstLetter")) {
			return "vendredi.Serializer";
		} else {
			return null;
		}
	}

	
	public void sendToServer(String action) throws IOException {
		chatClient.handleMessageFromFacade(action);
	}

	/*
	public ConnectionToClient getClientTemp() {
		return clientTemp;
	}

	public void setClientTemp(ConnectionToClient clientTemp) {
		this.clientTemp = clientTemp;
	}*/

}
