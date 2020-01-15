package comlayer;

public class Deserializer {
	private String command;
	private String[] params;
	private String result;
	// #command/param1,param2=result
	public String formating(String str) {
		str = deleteFirstLetter(str);
		if (str.startsWith("@")) {// modif game state
			str = deleteFirstLetter(str);
		}
		return str;
	}

	public String deleteFirstLetter(String str) {
		return str.substring(1);
	}
	public String extractResult(String str) {
		String[] res = str.split("=");
		if(res.length > 1) {
			return res[1];
		}
		return "";
	}
	public String extractCommand(String str) {
		str = formating(str);
		String[] parts = str.split("/");
		return parts[0];
	}

	public String[] extractParams(String str) {
		if(str.contains("/")) {
			String params = str.substring(str.indexOf("/") + 1);
			if(params.contains("=")){
				params = params.substring(0, params.indexOf("=") -1);
			}
			return params.split(",");
		}
		return new String[0];
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