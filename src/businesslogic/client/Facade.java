package businesslogic.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import comlayer.*;

public class Facade {
	public Serializer serializer = new Serializer();
	
	public void interpreteAction(String action) {
		if(serializer.isGameStateModification(action)) {
			serializer.formating(action);
			//delegateTo.. avec le .invoke
		}else if(serializer.isDatabaseModification(action)) {
			serializer.formating(action);
			//delegateTo.. avec le .invoke
		}else {
			System.out.println("commande incorrecte");
		}
	}
	
	public Object DelegateTo(String className ,String command, Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
