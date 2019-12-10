package login.UI;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
public class LoginController extends Application implements LoginUI  {
	Scene scene = null;
	public LoginController() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			scene = new Scene(root, 400, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void display() {
		Application.launch();
	}
	@Override
	public String readUserPassword() {
		return null;
	}
	@Override
	public String readUserLogin() {
		return null;
	}
	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Login");
		arg0.setScene(this.scene);
		arg0.show();
	}

	public static void main(String[] args) {
		LoginController lc = new LoginController();
		lc.display();
	}
}
