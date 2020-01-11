package businesslogic.client;

import comlayer.Serializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Facade {
	public Serializer serializer = new Serializer();
	
	public void interpreteAction(String action) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
		if(serializer.isGameStateModification(action)) {
			serializer.formating(action);
			delegateTo(serializer.getClassName(),serializer.getCommand(),serializer.getParams());
		}else if(serializer.isDatabaseModification(action)) {
			serializer.formating(action);
			delegateTo(serializer.getClassName(),serializer.getCommand(),serializer.getParams());
		}else {
			System.out.println("commande incorrecte");
		}
	}
	
	public Object delegateTo(String className ,String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> classe = Class.forName(className);
		Object o = classe.getDeclaredConstructor().newInstance(null);
		System.out.println(o);
		System.out.println(Arrays.toString(params));
		
		Class[] typeParametres = null;
	    if (params != null) {
	      typeParametres = new Class[params.length];
	      for (int i = 0; i < params.length; ++i) {
	        typeParametres[i] = params[i].getClass();
	      }
	    }
	    Method m = classe.getMethod(command, typeParametres);
		return m.invoke(o,params);
	}
}
