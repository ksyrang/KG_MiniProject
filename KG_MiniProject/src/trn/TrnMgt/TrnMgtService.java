package trn.TrnMgt;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import trn.Welcome.TrnWelcomeController;

public class TrnMgtService {

	private TrnMgtController TrnMgtController;
	
	public void setTrnMgtController(TrnMgtController trnMgtController) {
		this.TrnMgtController = trnMgtController;
	}
	
	public void TnrModifyProc(Parent trnMgtForm, String TrnCode) {
		CmnTrainerDAO tmpDAO = new CmnTrainerDAO();
		CmnTrainerDTO tmpDto = new CmnTrainerDTO(tmpDAO.SltTrnOne(TrnCode));
		
		PasswordField PWField = (PasswordField)trnMgtForm.lookup("#TrnPWField");
		PasswordField PWCField = (PasswordField)trnMgtForm.lookup("#TrnPWCField");
		TextField NameField = (TextField)trnMgtForm.lookup("#TrnNameField");//변경 이름
		TextField BirthField = (TextField)trnMgtForm.lookup("#TrnBirthField");//변경 생일
		TextField MobileField = (TextField)trnMgtForm.lookup("#TrnMobileField");//변경 전번
		RadioButton MaleRtn = (RadioButton)trnMgtForm.lookup("#MaleRbtn");//남자버튼
		RadioButton FeMaleRtn = (RadioButton)trnMgtForm.lookup("#FeMaleRbtn");//여자버튼
		TextField Addr1Field = (TextField)trnMgtForm.lookup("#TrnAddr1");//변경 주소
		TextField Addr2Field = (TextField)trnMgtForm.lookup("#TrnAddr2");//변경 주소
		TextField CareerField = (TextField)trnMgtForm.lookup("#TrnCareer");//변경 커리어
		//입력 사항의 형식 확인
		try {
			int tmpMB = Integer.parseInt(MobileField.getText());
			int tmpBD = Integer.parseInt(BirthField.getText());
		} catch (NumberFormatException e) {
			CommonService.Msg("숫자만 입력 해주세요.");
			return;
		}
		
		//입력 사항 최소 길이 확인
		if(MobileField.getText().length()<11){
			CommonService.Msg("11자리의 전화번호를 입력 해주세요.");
			return;
		}else if(BirthField.getText().length()<8){
			CommonService.Msg("8자리의 생년월일를 입력 해주세요.");
			return;
		}
		
		//변경사항 업데이트
		if(!PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {//정상 입력 조건
			if(PWField.getText().equals(PWCField.getText())){//입력 값이 동일하다는 조건
				tmpDto.setTRAINER_Name(NameField.getText());
				tmpDto.setTRAINER_PW(PWField.getText());
				if(MaleRtn.isSelected()) tmpDto.setTRAINER_Gender("남");
				else tmpDto.setTRAINER_Gender("여");
				tmpDto.setTRAINER_Birth(Integer.parseInt(BirthField.getText()));
				tmpDto.setTRAINER_Mobile(Integer.parseInt(MobileField.getText()));
				
				if(!Addr2Field.getText().isEmpty()) {
					tmpDto.setTRAINER_Addr(Addr1Field.getText()+"/"+Addr2Field.getText());	
				}else {
					tmpDto.setTRAINER_Addr(Addr1Field.getText()+"/");
				}
				tmpDto.setTRAINER_Career(Integer.parseInt(CareerField.getText()));
				int result = tmpDAO.UptTrn(tmpDto);
				if(result == 1) {
					CommonService.Msg("수정완료");
					CommonService.WindowClose(trnMgtForm);
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
