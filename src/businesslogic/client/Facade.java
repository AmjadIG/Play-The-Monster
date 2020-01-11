package businesslogic.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import comlayer.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Facade {

	//tous les users connectés
	// les gameStates

	public Serializer serializer = new Serializer();
	private String lastStringAction;


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
		}else {
			return null;
		}
	}
}