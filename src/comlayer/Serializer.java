package comlayer;

public class Serializer {
	private String command;
	private String[] params;

	// #command/param1,param2
	public void formating(String str) {
		str = deleteFirstLetter(str);
		if (str.startsWith("@")) {// modif game state
			str = deleteFirstLetter(str);
			this.command = extractCommand(str);
			this.params = extractParams(str);
		}
		else {// modif bd
			this.command = extractCommand(str);
			this.params = extractParams(str);
		}
	}

	public String deleteFirstLetter(String str) {
		return str.substring(1);
	}

	public String extractCommand(String str) {
		String[] parts = str.split("/");
		return parts[0];
	}

	public String[] extractParams(String str) {
		String[] parts = str.split("/");
		if (parts.length > 1) {
			if(parts[1].contains(",")) {
				return parts[1].split(",");
			}
			else {
				return new String[]{parts[1]};
			}
		}
		else {
			return null;
		}
	}

	// verify if string looks like #b/
	public boolean isDatabaseModification(String str) {
		String[] parts = str.split("#");
		return (str.startsWith("#") && parts.length == 2 && str.contains("/"));
	}

	public boolean isGameStateModification(String str) {
		if (isDatabaseModification(str)) {
			return str.startsWith("#@") && str.contains("/");
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
}