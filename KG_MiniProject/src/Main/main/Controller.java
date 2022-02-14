package Main.main;

import Main.login.LoginController;
import javafx.scene.Parent;

//컨트롤러들의 참조 값을 저장하는 기능.
public class Controller {
	private LoginController loginController;
	private MainService mainService;
	private Parent adminWelcomeForm;
	private Parent memberWelcomeForm;
	private Parent trainerWelcomeForm;
	private Parent memberJoinForm;
	
	
	public Controller() {
		mainService = new MainService();
		mainService.setController(this);
	}
	
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
		this.loginController.setController(this);
	}
	
	
	public void setAdminWelcomeForm(Parent adminWelcomeForm) {
		this.adminWelcomeForm = adminWelcomeForm;
	}
	public void setmemberWelcomeForm(Parent memberWelcomeForm) {
		this.memberWelcomeForm = memberWelcomeForm;
	}
	public void setTrinerWelcomeForm(Parent trainerWelcomeForm) {
		this.trainerWelcomeForm = trainerWelcomeForm;
	}
	public void setmemberJoinForm(Parent memberJoinForm) {
		this.memberJoinForm = memberJoinForm;
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
