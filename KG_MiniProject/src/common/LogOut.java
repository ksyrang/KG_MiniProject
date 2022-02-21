package common;

import java.io.IOException;

import Main.login.LoginController;
import Main.main.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogOut {
	
	private Parent Form;

	public Parent getLoginForm() {
		return Form;
	}
	public void setLoginForm(Parent loginForm) {
		this.Form = loginForm;
	}
	public void LogOut(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/main/KG_COM_FX_Main.fxml"));
		try {
			Parent mainForm = loader.load();
			Controller controller = new Controller();
			controller.setLoginController(loader.getController());
//			controller.getLoginController().setmainForm(mainForm);
			LoginController loginController = controller.getLoginController();
			loginController.setmainForm(mainForm);
			controller.setLogOut(new LogOut());
			
			Stage stage = new Stage();
			Scene scene = new Scene(mainForm);
			
			stage.setScene(scene);
			stage.setTitle("new login");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
