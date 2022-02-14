package Main.main;

import Main.login.LoginController;
import javafx.scene.Parent;

//컨트롤러들의 참조 값을 저장하는 기능.
public class Controller {
	private LoginController loginController;
	private MainService mainService;
	private Parent adminWelcomeForm;
	
	public Controller() {
		mainService = new MainService();
		mainService.setController(this);
	}
	
	public void setAdminWelcomeForm(Parent adminWelcomeForm) {
		this.adminWelcomeForm = adminWelcomeForm;
	}
	
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
		this.loginController.setController(this);
	}
	
	public LoginController getLoginController() {
		return this.loginController;
	}
	
	public void open(String division) {
		if("adminWelcome".equals(division)) {
			mainService.adminWelcomeOpen();
		}else if("memberWelcome".equals(division)) {
			mainService.memberWelcomeOpen();
		}else if("trainerWelcome".equals(division)) {
			mainService.trainerWelcomeOpen();
		}else if("memberJoin".equals(division)) {
			mainService.memberJoinOpen();
		}	
	}


}
