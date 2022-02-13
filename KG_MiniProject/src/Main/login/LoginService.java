package Main.login;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class LoginService {

	// 로그인 버튼 클릭 시 호출
	public LoginDTO loginProc(Parent mainForm) {
		TextField idText = (TextField) mainForm.lookup("#idText");
		PasswordField pwText = (PasswordField) mainForm.lookup("#pwText");
//		RadioButton memberRadio = (RadioButton)mainForm.lookup("#memberRadio");
//		RadioButton instructorRadio = (RadioButton)mainForm.lookup("#instructorRadio");
//		RadioButton adminRadio = (RadioButton)mainForm.lookup("#adminRadio");
//		
//		System.out.println("여기까진 옴");
//		String job = "";
//		if(memberRadio.isSelected())
//			job += "회원";
//		else if(instructorRadio.isSelected())
//			job += "강사";
//		else if(adminRadio.isSelected())
//			job += "관리자";
		
//		// 데이터베이스에 조회를 하여 정보를 검증함.
		LoginDAO loginDao = new LoginDAO();
		LoginDTO loginDto = loginDao.SelectId(idText.getText());
		
		if(loginDto != null) {
			System.out.println("로그인 성공");
//			if(job.equals("관리자") && loginDto.getMEM_PW().equals(pwText.getText())) {
//				CommonService.Msg("관리자 계정 로그인 성공");
//			}else if(job.equals("강사") && loginDto.getMEM_PW().equals(pwText.getText())) {
//				CommonService.Msg("강사 계정 로그인 성공");
//			}else if(job.equals("회원") && loginDto.getMEM_PW().equals(pwText.getText())) {
//				CommonService.Msg("회원 로그인 성공");
//			}else {
//				CommonService.Msg("로그인 실패 : 비밀번호 불일치");
//			}
		}else {
			CommonService.Msg("로그인 실패 : 존재하지 않는 아이디");
		}
		System.out.println("여기까지옴");
		return loginDto;

	}
	
	
	
	


}
