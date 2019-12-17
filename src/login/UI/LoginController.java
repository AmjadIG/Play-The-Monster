package login.UI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.BusinessLogic.Facade;

import java.io.IOException;
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
		scene = new Scene(root, 600, 330);

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
		return pwd.getText();
	}
	@Override
	public String readUserLogin() {
		return login.getText();
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
		return facade.login(readUserLogin(), readUserPassword());
	}

	public static void main(String[] args) {
		LoginController lc = new LoginController();
		lc.display();
	}
}
