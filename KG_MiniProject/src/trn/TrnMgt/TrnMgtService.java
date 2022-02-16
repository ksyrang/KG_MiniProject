package trn.TrnMgt;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TrnMgtService {

	public void TnrModifyProc(Parent Form, String TrnCode) {
		CmnTrainerDAO tmpDAO = new CmnTrainerDAO();
		CmnTrainerDTO tmpDto = new CmnTrainerDTO(tmpDAO.SltTrnOne(TrnCode));
		
		PasswordField PWField = (PasswordField)Form.lookup("#TrnPWField");
		PasswordField PWCField = (PasswordField)Form.lookup("#TrnPWCField");
		TextField NameField = (TextField)Form.lookup("#TrnNameField");//변경 이름
		TextField BirthField = (TextField)Form.lookup("#TrnBirthField");//변경 생일
		TextField MobileField = (TextField)Form.lookup("#TrnMobileField");//변경 전번
		RadioButton MaleRtn = (RadioButton)Form.lookup("#MaleRbtn");//남자버튼
		RadioButton FeMaleRtn = (RadioButton)Form.lookup("#FeMaleRbtn");//여자버튼
		TextField Addr1Field = (TextField)Form.lookup("#TrnAddr1");//변경 주소
		TextField Addr2Field = (TextField)Form.lookup("#TrnAddr2");//변경 주소
		TextField CareerField = (TextField)Form.lookup("#TrnCareer");//변경 커리어
		//변경사항 업데이트
		if(!PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {//정상 입력 조건
			if(PWField.getText().equals(PWCField.getText())){//입력 값이 동일하다는 조건
				tmpDto.setTRAINER_Name(NameField.getText());
				tmpDto.setTRAINER_PW(PWField.getText());
				if(MaleRtn.isSelected()) tmpDto.setTRAINER_Gender("남성");
				else tmpDto.setTRAINER_Gender("여성");
				tmpDto.setTRAINER_Birth(Integer.parseInt(BirthField.getText()));
				tmpDto.setTRAINER_Mobile(Integer.parseInt(MobileField.getText()));
				tmpDto.setTRAINER_Addr(Addr1Field.getText()+"/"+Addr2Field.getText());
				tmpDto.setTRAINER_Career(Integer.parseInt(CareerField.getText()));
				int result = tmpDAO.UptTrn(tmpDto);
				if(result == 1) {
					CommonService.Msg("수정완료");
					CommonService.WindowClose(Form);
				}
				else CommonService.Msg("이상 발생");
			}else {
				CommonService.Msg("비밀번호가 서로 다릅니다");
				PWField.clear();
				PWCField.clear();
				PWField.requestFocus();
			}
		}
		else if(PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {
			CommonService.Msg("비밀번호칸을 채워주세요");
		}
		else if(!PWField.getText().isEmpty() && PWCField.getText().isEmpty()) {
			CommonService.Msg("비밀번호비교칸을 채워주세요");
		}
		else {
			CommonService.Msg("비밀번호칸들을 채워 주세요");
		}
	}
	
	
	public void BackProc(Parent Form) {
		CommonService.WindowClose(Form);
	}
	
	
}
