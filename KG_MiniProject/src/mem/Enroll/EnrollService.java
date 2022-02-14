package mem.Enroll;

import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import common.CommonService;

public class EnrollService {
	
	public void insert(Parent enrollForm) {
		// 화면에 입력한 데이터 출력하기.
		// System.out.println();
		TextField nameTxt = (TextField) enrollForm.lookup("#nameTxt");
		TextField idTxt = (TextField) enrollForm.lookup("#idTxt");
		PasswordField pwTxt = (PasswordField) enrollForm.lookup("#pwTxt");
		PasswordField confirmTxt = (PasswordField) enrollForm.lookup("#confirmTxt");
		TextField birthTxt = (TextField) enrollForm.lookup("#birthTxt");
		TextField mobileTxt = (TextField) enrollForm.lookup("#mobileTxt");
		TextField addrTxt = (TextField) enrollForm.lookup("#addrTxt");
		
		
		
		String name = nameTxt.getText();
		String id = idTxt.getText();
		String pw = pwTxt.getText();
		String confirm = confirmTxt.getText();
		String birth = birthTxt.getText();
		String mobile = mobileTxt.getText();
		String addr = addrTxt.getText();
		
		RadioButton manRadio = (RadioButton) enrollForm.lookup("#manRadio");
		RadioButton womanRadio = (RadioButton) enrollForm.lookup("#womanRadio");

		String gender = "";
		if (manRadio.isSelected())
			gender += "남";
		else if (womanRadio.isSelected())
			gender += "여";

		if(name.isEmpty() || id.isEmpty() || pw.isEmpty() || birth.isEmpty() || mobile.isEmpty() || addr.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}
		
		if(pw.equals(confirm)) {
			EnrollDAO enrollDAO = new EnrollDAO();
			EnrollDTO enrollDTO = enrollDAO.SelectId(id);
			if(enrollDTO == null) {
				enrollDTO = new EnrollDTO();
				enrollDTO.setID(id);
				enrollDTO.setPW(pw);
				enrollDTO.setName(name);
				enrollDTO.setGender(gender);
				enrollDTO.setBirth(birth);
				enrollDTO.setMobile(mobile);
				enrollDTO.setAddr(addr);
				
				enrollDAO.insert(enrollDTO);
				CommonService.Msg(id+" 계정이 등록되었습니다.");
			}else {
				CommonService.Msg(id+"는/은 등록된 계정입니다.");
			}
		}else {
			CommonService.Msg("입력한 두 패스워드가 틀립니다.");
		}
		
	}
}