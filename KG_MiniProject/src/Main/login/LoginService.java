package Main.login;

import Main.main.Controller;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class LoginService {
	
	private String job;
	private String welcomepage;
	private String UserCode;
	// 로그인 버튼 클릭 시 호출
	public LoginDTO loginProc(Parent mainForm) {
		TextField idText = (TextField) mainForm.lookup("#idText");
		PasswordField pwText = (PasswordField) mainForm.lookup("#pwText");
		RadioButton memberRadio = (RadioButton)mainForm.lookup("#memberRadio");
		RadioButton trainerRadio = (RadioButton)mainForm.lookup("#trainerRadio");
		RadioButton adminRadio = (RadioButton)mainForm.lookup("#adminRadio");
		
		
		if(memberRadio.isSelected())
			job = "회원";
		else if(trainerRadio.isSelected())
			job = "강사";
		else if(adminRadio.isSelected())
			job = "관리자";
		
		LoginDAO loginDao = new LoginDAO();
		LoginDTO loginDto = null;
		
		if(job.equals("관리자") || job.equals("회원")) {
			loginDto = loginDao.SelectMemberId(idText.getText());
//			System.out.println("Mcode: "+loginDto.getMEM_Code());
			if(loginDto != null && loginDto.getMEM_PW().equals(pwText.getText())) {
				if(job.equals("관리자"))
					welcomepage = "adminWelcome"; //관리자
				else if(job.equals("회원"))	{
					UserCode =loginDto.getMEM_Code();
					welcomepage = "memberWelcome";	//회원
				}
					
			}else {
				CommonService.Msg("로그인 실패");
			}
		}else if(job.equals("강사")) {
			loginDto = loginDao.SelectTrainerId(idText.getText());
//			System.out.println("Tcode: "+loginDto.getTRAINER_Code());
			if(loginDto != null && loginDto.getTRAINER_PW().equals(pwText.getText())) {
				UserCode = loginDto.getTRAINER_Code();
				welcomepage = "trainerWelcome";//강사
			}else {
				CommonService.Msg("로그인 실패");
			}
		}else {
			CommonService.Msg("로그인 실패 : 체크박스 미선택");
		}
		return loginDto;
	}
	
	public String getjob() {
		return job;
	}
	public String getpage() {
		return welcomepage;
	}
	public String getUserCode() {
		return UserCode;
	}
}
