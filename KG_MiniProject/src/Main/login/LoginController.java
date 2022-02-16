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
	private String UserCode;
	
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}


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
		String job = loginSvc.getjob();
		if(loginDto != null) {
			CommonService.WindowClose(mainForm);
			if(job.equals("회원")) {
				controller.setUserCode(loginSvc.getUserCode());
				controller.open(loginSvc.getpage());
				CommonService.Msg("회원 계정 로그인 성공");
			}
			else if(job.equals("강사")) {
				controller.setUserCode(loginSvc.getUserCode());
				controller.open(loginSvc.getpage());
				CommonService.Msg("강사 계정 로그인 성공");
			}
			else if(job.equals("관리자")) {
				controller.open(loginSvc.getpage());
				CommonService.Msg("관리자 계정 로그인 성공");
			}
		}
	}
	
	
	//회원가입 버튼 클릭 시 호출
	public void joinProc() {		
	//		CommonService.WindowClose(mainForm);
			controller.open("memberJoin");
		}
	//아이디찾기 클릭 시 호출
	public void findIDProc() {
	//		CommonService.WindowClose(mainForm);
			controller.open("memberFindID");
	}
	//비밀번호찾기 클릭 시 호출
	public void findPWProc() {
	//		CommonService.WindowClose(mainForm);
			controller.open("memberFindPW");
	}
}