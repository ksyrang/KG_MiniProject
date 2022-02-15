package trn.TrnMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import trn.DTO.TrnDTO;

public class TrnMgtController implements Initializable {

	private TrnMgtService TrnMgtSvc;
	private String trnCode;
	private Parent trnMgtForm;
	
	public TrnMgtController() {
		TrnMgtSvc = new TrnMgtService();
		
	}
	
	public void setTrnMgtForm(Parent trnMgtForm) {
		this.trnMgtForm = trnMgtForm;
	}
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void TnrMgtProc() {
		TrnDTO tmpTrnDto = TrnMgtSvc.getTrnInfo(trnCode);
		PasswordField CPwField = (PasswordField)trnMgtForm.lookup("#TrnPWField");
		PasswordField CPwCField = (PasswordField)trnMgtForm.lookup("#TrnPWCField");
		
		if(CPwField.getText().equals(CPwCField.getText())){
			//변경사항 업데이트
			TextField NameField = (TextField)trnMgtForm.lookup("#TrnNameField");//변경 이름
			tmpTrnDto.setTRAINER_Name(NameField.getText());
			PasswordField PwField = (PasswordField)trnMgtForm.lookup("#TrnPWField");
			tmpTrnDto.setTRAINER_PW(PwField.getText());
			TextField BirthField = (TextField)trnMgtForm.lookup("#TrnBirthField");//변경 생일
			tmpTrnDto.setTRAINER_Birth(Integer.parseInt(BirthField.getText()));
			TextField MobileField = (TextField)trnMgtForm.lookup("#TrnMobileField");//변경 전번
			tmpTrnDto.setTRAINER_Mobile(Integer.parseInt(MobileField.getText()));
			TextField AddrField = (TextField)trnMgtForm.lookup("#TrnAddr1");//변경 주소
			tmpTrnDto.setTRAINER_Addr(AddrField.getText());
			TextField CareerField = (TextField)trnMgtForm.lookup("#TrnCareer");//변경 커리어
			tmpTrnDto.setTRAINER_Career(Integer.parseInt(CareerField.getText()));

			int result = TrnMgtSvc.TrnMgtUpdate(tmpTrnDto);
			if(result == 1) {
				CommonService.Msg("수정완료");
				CommonService.WindowClose(trnMgtForm);
			}
			else CommonService.Msg("이상 발생");
		}else {
			CommonService.Msg("비밀번호를 확인해주세요");
			CPwField.clear();
			CPwCField.clear();
			CPwField.requestFocus();
		}
	}
	
	public void BackProc() {
		TrnMgtSvc.BackProc(trnMgtForm);
	}
	
	
	
}
