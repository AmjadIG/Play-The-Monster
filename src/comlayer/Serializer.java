package comlayer;

public class Serializer {
	private String className;
	private String command;
	private String[] params;
	
	// #className#command/param1,param2
	public void formating(String str) {
		this.setClassName(extractClassName(str));
		str = epureStringWithoutClassName(str);
		str = deleteFirstLetter(str);
		if (str.startsWith("@")) {
			// modif game state
			str = deleteFirstLetter(str);
			this.command = extractCommand(str);
			this.params = extractParams(str);
		}
		else {
			// modif bd
			this.command = extractCommand(str);
			this.params = extractParams(str);
		}
	}
	
	private String extractClassName(String str) {
		str = deleteFirstLetter(str);
		String[] parts = str.split("#");
		return parts[0];
	}
	
	private String epureStringWithoutClassName(String str) {
		str = deleteFirstLetter(str);
		String[] parts = str.split("#");
		return parts[1];
	}

	public String deleteFirstLetter(String str) {
		System.out.println("YAY");
		return str.substring(1);
		
	}
	
	public String extractCommand(String str) {
		String[] parts = str.split("/");
		String command = parts[0];
		return command; 
	}
	
	public String[] extractParams(String str) {
		String[] parts = str.split("/");
		if (parts.length > 1) { 
			if(parts[1].contains(",")) {
				String[] params = parts[1].split(",");
				return params; 
			}
			else {
				String[] res = {parts[1]};
				return res;
			}
			
		}else {
			return null;
		}
		
	}
	
	public boolean containsParams(String str) {
		String[] parts = str.split("/");
		return parts[1].length() > 1;
	}
	
	// verify if string looks like #a#b
	public boolean isDatabaseModification(String str) {
		String[] parts = str.split("#");
		return (str.startsWith("#") && parts.length == 3);
	}
	
	public boolean isGameStateModification(String str) {
		if (isDatabaseModification(str)) {
			String[] parts = str.split("#");
			return (str.startsWith("#") && parts[2].startsWith("@"));
		}else {
			return false;
		}
		
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}