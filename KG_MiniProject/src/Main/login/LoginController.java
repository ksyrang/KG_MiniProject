package Main.login;

import java.net.URL;
import java.util.ResourceBundle;

import Main.main.Controller;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class LoginController implements Initializable{

	private Parent mainForm;
	private LoginService loginSvc;
	private Controller controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.loginSvc = new LoginService();
	}
	
	public void setmainForm(Parent mainForm) {
		this.mainForm = mainForm;
		System.out.println(this.mainForm);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	//로그인 버튼 클릭 시 호출
	public void loginProc() {
		System.out.println(this.mainForm);
//		LoginDTO loginDto = loginSvc.loginProc(mainForm);
//		
//		if(loginDto != null) {
//			CommonService.WindowClose(mainForm);
//			controller.open("adminWelcome");
//		}
	}
	

}