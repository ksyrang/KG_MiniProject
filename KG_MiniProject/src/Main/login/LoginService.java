package Main.login;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class LoginService {

	private String job = "";
	// 로그인 버튼 클릭 시 호출
	public LoginDTO loginProc(Parent mainForm) {
		TextField idText = (TextField) mainForm.lookup("#idText");
		PasswordField pwText = (PasswordField) mainForm.lookup("#pwText");
		RadioButton memberRadio = (RadioButton)mainForm.lookup("#memberRadio");
		RadioButton trainerRadio = (RadioButton)mainForm.lookup("#trainerRadio");
		RadioButton adminRadio = (RadioButton)mainForm.lookup("#adminRadio");
		
		
		if(memberRadio.isSelected())
			job += "회원";
		else if(trainerRadio.isSelected())
			job += "강사";
		else if(adminRadio.isSelected())
			job += "관리자";
		
		LoginDAO loginDao = new LoginDAO();
		LoginDTO loginDto =null;
		
		if(job.equals("관리자") || job.equals("회원")) {
			loginDto = loginDao.SelectMemberId(idText.getText());
		}else if(job.equals("강사")) {
			loginDto = loginDao.SelectTrainerId(idText.getText());
		}
		
		if(loginDto != null && loginDto.getMEM_PW().equals(pwText.getText())) {
			if(job.equals("관리자"))
				CommonService.Msg("관리자 계정 로그인 성공");
			else if(job.equals("회원"))
				CommonService.Msg("회원 계정 로그인 성공");
			else if(job.equals("강사"))
				CommonService.Msg("강사 계정 로그인 성공");
		}else {
			CommonService.Msg("로그인 실패");
		}
		return loginDto;
	}
	
	public String job() {
		return job;
	}
	
	
}
