package businesslogic.client;

import businesslogic.client.domain.User;
import comlayer.Serializer;
import comlayer.server.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Facade {
	ArrayList<User> connectedUsers = new ArrayList();
	ArrayList<StateGame> stateGames = new ArrayList();
	private EchoServer echoServer;
	public Serializer serializer = new Serializer();
	private String lastStringAction;

	public static void rollBackState(List<String> rollBack) {
	}


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

	public static void main(){
		Facade f = new Facade();
		//f.echoServer = new EchoServer();


	}
}
