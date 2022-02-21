package Main.main;

import Main.login.LoginController;
import admin.welcome.WelcomeController;
import common.CommonService;
import common.LogOut;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mem.Enroll.EnrollController;
import mem.FindID.FindIDController;
import mem.FindPW.FindPWController;
import mem.Mgt.MgtController;
import mem.Welcome.MEM_WelcomeController;
import trn.Welcome.TrnWelcomeController;

//컨트롤러들의 참조 값을 저장하는 기능.
public class Controller {
	private LoginController loginController;
	private EnrollController enrollController;
	private FindIDController findIDController;
	private FindPWController findPWController;
	private TrnWelcomeController trnWelcomeController;
	private MEM_WelcomeController memWelcomeController;
	private WelcomeController adminWelcomeController;
	private LogOut LogOut;
	private CommonService commonService;
	private MainService mainService;
	private Parent adminWelcomeForm;
	private Parent memberWelcomeForm;
	private Parent trainerWelcomeForm;
	private Parent memberJoinForm;
	private String UserCode;
	private Parent memberFindIDForm;
	private Parent memberFindPWForm;
	private Parent memberMgtForm;
	private String id;
	
	
	
	public LogOut getLogOut() {
		return LogOut;
	}

	public void setLogOut(LogOut logOut) {
		LogOut = logOut;
	}


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
	
	public MEM_WelcomeController getMEM_WelcomeController() {
		return memWelcomeController;
	}
	
	public void setMEM_WelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}
	
	public void setEnrollController(EnrollController enrollController) {
		this.enrollController = enrollController;
	}
	
	public void setAdminWelcomeController(WelcomeController adminWelcomeController) {
		this.adminWelcomeController = adminWelcomeController;
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
	
	public void settingAdmin() {
		this.adminWelcomeController.setWelcomeForm(this.adminWelcomeForm);
	}
	
	public void setMemberFindIDForm(Parent memberFindIDForm) {
		this.memberFindIDForm = memberFindIDForm;
	}
	
	public void setFindIDController(FindIDController findIDController) {
		this.findIDController = findIDController;
	}
	
	public void settingFindID() {
		this.findIDController.setFindIDForm(this.memberFindIDForm);
	}
	
	public void setMemberFindPWForm(Parent memberFindPWForm) {
		this.memberFindPWForm = memberFindPWForm;
	}
	
	public void setFindPWController(FindPWController findPWController) {
		this.findPWController = findPWController;
	}
	
	public void settingFindPW() {
		this.findPWController.setFindPWForm(this.memberFindPWForm);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getid() {
		return this.id;
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
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
		}else if("memberFindPW".equals(division)) {
			mainService.memberFindPWOpen();
		}
	}


}
