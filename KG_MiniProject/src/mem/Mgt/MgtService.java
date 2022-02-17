package mem.Mgt;


import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;




public class MgtService {
	
	public void MemModifyProc(Parent memMgtForm, String MemCode) {
		MgtDAO tmpDAO = new MgtDAO();
		MgtDTO tmpDto = new MgtDTO(tmpDAO.selectCode(MemCode));
		
		PasswordField PWField = (PasswordField)memMgtForm.lookup("#MemPWField");
		PasswordField PWCField = (PasswordField)memMgtForm.lookup("#MemPWCField");
		TextField NameField = (TextField)memMgtForm.lookup("#MemNameField");//변경 이름
		TextField BirthField = (TextField)memMgtForm.lookup("#MemBirthField");//변경 생일
		TextField MobileField = (TextField)memMgtForm.lookup("#MemMobileField");//변경 전번
		RadioButton MaleRtn = (RadioButton)memMgtForm.lookup("#MaleRabtn");//남자버튼
		RadioButton FeMaleRtn = (RadioButton)memMgtForm.lookup("#FeMaleRabtn");//여자버튼
		TextField Addr1Field = (TextField)memMgtForm.lookup("#MemAddr1");//변경 주소
		TextField Addr2Field = (TextField)memMgtForm.lookup("#MemAddr2");//변경 주소

		//변경사항 업데이트
		if(!PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {//정상 입력 조건
			if(PWField.getText().equals(PWCField.getText())){//입력 값이 동일하다는 조건
				tmpDto.setMEM_Name(NameField.getText());
				tmpDto.setMEM_PW(PWField.getText());
				if(MaleRtn.isSelected()) tmpDto.setMEM_Gender("남성");
				else tmpDto.setMEM_Gender("여성");
				tmpDto.setMEM_Birth(Integer.parseInt(BirthField.getText()));
				tmpDto.setMEM_Mobile(Integer.parseInt(MobileField.getText()));
				tmpDto.setMEM_Addr(Addr1Field.getText()+"/"+Addr2Field.getText());
				int result = tmpDAO.UptMem(tmpDto);
				if(result == 1) {
					CommonService.Msg("수정완료");
					CommonService.WindowClose(memMgtForm);
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
	
	
	public void BackMgtProc(Parent memMgtForm) {
		CommonService.WindowClose(memMgtForm);
	}
	


}