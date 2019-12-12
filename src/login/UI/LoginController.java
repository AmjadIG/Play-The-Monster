package login.UI;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import DAO.DAOFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import login.BusinessLogic.Facade;
import login.PersistantLayer.UserDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
public class LoginController extends Application implements LoginUI  {
	Facade facade = new Facade();
	Scene scene = null;
	Button submit = null;
	TextField login = null;
	TextField pwd = null;
	public LoginController() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root, 400, 300);
		login = (TextField) scene.lookup("#loginTextField");
		pwd = (TextField) scene.lookup("#pwdTextField");
		submit = (Button) scene.lookup("#loginBtn");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				login();
			}
		});
	}
	@Override
	public void display() {
		Application.launch();
	}
	@Override
	public String readUserPassword() {
		return login.getText();
	}
	@Override
	public String readUserLogin() {
		return pwd.getText();
	}
	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Login");
		arg0.setScene(this.scene);
		arg0.show();
	}
	
	public void reactToClick() {
		System.out.print("clic!");
	}
	
	
	public boolean login() {
		String login = readUserLogin();
		String pwd = readUserPassword();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("login", login);
		userInfo.put("password", pwd);
		System.out.println(login);
		System.out.println(pwd);
		boolean bool = facade.login(userInfo);
		if (bool){
			System.out.println("Client connecté");
		}
		return bool;

		/*UserDAO udao = (UserDAO) DAOFactory.getUserDAO();
		String login = readUserLogin();
		String pwd = readUserPassword();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("login", login);
		userInfo.put("password", pwd);
		if (udao.getBy(userInfo) != null) {
			return true;
		}
		return false;*/
	}

	public static void main(String[] args) {
		LoginController lc = new LoginController();
		lc.display();
	}
}
