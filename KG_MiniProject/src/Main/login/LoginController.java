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
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void setmainForm(Parent mainForm) {
		this.mainForm = mainForm;
	}

	//로그인 버튼 클릭 시 호출
	public void loginProc() {
		LoginDTO loginDto = loginSvc.loginProc(mainForm);
		String job = loginSvc.job();
		if(loginDto != null) {
			CommonService.WindowClose(mainForm);
			if(job.equals("회원"))
				controller.open("memberWelcome");
			else if(job.equals("강사"))
				controller.open("trainerWelcome");
			else if(job.equals("관리자"))
				controller.open("adminWelcome");
		}
	}
	
	
	//회원가입 버튼 클릭 시 호출
	public void joinProc() {		
	//		CommonService.WindowClose(mainForm);
			controller.open("memberJoin");
		}
	

}