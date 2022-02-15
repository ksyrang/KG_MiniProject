package Main.main;

import Main.login.LoginController;
import javafx.scene.Parent;
import mem.Enroll.EnrollController;
import mem.FindID.FindIDController;
import trn.Welcome.TrnWelcomeController;

//컨트롤러들의 참조 값을 저장하는 기능.
public class Controller {
	private LoginController loginController;
	private EnrollController enrollController;
	private FindIDController findIDController;
	private MainService mainService;
	private Parent adminWelcomeForm;
	private Parent memberWelcomeForm;
	private Parent trainerWelcomeForm;
	private Parent memberJoinForm;
	private String UserCode;
	private Parent memberFindIDForm;
	
	/**Trainer package controller**/
	private TrnWelcomeController trnWelcomeController;
	/******************************/
	
	public Controller() {
		mainService = new MainService();
		mainService.setController(this);
	}
	
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	   
	   public void setFindIDController(FindIDController findIDController) {
	      this.findIDController = findIDController;
	   }

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
		this.loginController.setController(this);
	}
	
	public LoginController getLoginController() {
		return this.loginController;
	}
	
	public void setTrnWelcomeController(TrnWelcomeController trnWelcomeController) {
		this.trnWelcomeController = trnWelcomeController;
	}
	public TrnWelcomeController getTrnWelcomeController() {
		return trnWelcomeController;
	}
	
	public void setEnrollController(EnrollController enrollController) {
		this.enrollController = enrollController;
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
	public void setMemberJoinForm(Parent memberJoinForm) {
		this.memberJoinForm = memberJoinForm;
	}
	
	public void settingEnroll() {
		this.enrollController.setEnrollForm(this.memberJoinForm);
	}
	
	public void setMemberFindIDForm(Parent memberFindIDForm) {
		this.memberFindIDForm = memberFindIDForm;
	}
	
	public void open(String division) {
		if("adminWelcome".equals(division)) {
			mainService.adminWelcomeOpen();
		}else if("memberWelcome".equals(division)) {
			mainService.memberWelcomeOpen(UserCode);
		}else if("trainerWelcome".equals(division)) {
			mainService.trainerWelcomeOpen(UserCode);
		}else if("memberJoin".equals(division)) {
			mainService.memberJoinOpen();
		}else if("memberFindID".equals(division)) {
			mainService.memberFindIDOpen();
		}
	}


}
