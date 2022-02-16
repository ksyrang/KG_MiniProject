package admin.trainerEnroll;

import common.CmnTrainerDAO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TrainerEnrollService {
	
		CmnTrainerDAO dao;
	
	// 중복 체크
	public void overlapProc(Parent trainerEnrollForm) {
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		String trnId = trnIdTxt.getText();
		
		if(trnId.isEmpty()) {
			CommonService.Msg("ID를 입력해주세요.");
		} else {
			dao = new CmnTrainerDAO();
			
			if(dao.SltTrnId(trnId) == null) {
				CommonService.Msg(trnId + " 은(는) 사용 가능한 ID입니다.");
			}else {
				CommonService.Msg(trnId + " 은(는) 이미 사용하고 있는 ID입니다.");
				trnIdTxt.setText(null);
			}
		}
	}
	
	// 강사 등록
	public void trnEnrollProc(Parent trainerEnrollForm) {
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		TextField trnPwTxt = (TextField) trainerEnrollForm.lookup("#trnPwTxt");
		TextField trnPwComfrimTxt = (TextField) trainerEnrollForm.lookup("#trnPwComfrimTxt");
		TextField trnNameTxt = (TextField) trainerEnrollForm.lookup("#trnNameTxt");
		TextField trnBirthTxt = (TextField) trainerEnrollForm.lookup("#trnBirthTxt");
		TextField trnMobileTxt = (TextField) trainerEnrollForm.lookup("#trnMobileTxt");
		TextField trnAddrTxt1 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt1");
		TextField trnAddrTxt2 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt2");
		TextField trnCareerTxt = (TextField) trainerEnrollForm.lookup("#trnCareerTxt");
		RadioButton trnMenRadio = (RadioButton) trainerEnrollForm.lookup("#trnMenRadio");
		RadioButton trnWomenRadio = (RadioButton) trainerEnrollForm.lookup("#trnWomenRadio");
		//ToggleGroup group = 
	}

	
}
