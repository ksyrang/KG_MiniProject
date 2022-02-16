package trn.EXProgramMgt;

import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TrnExPMgtService {
	private Label TitleUserNameLabel;
	private Label PrmScheCodeLabel;
	private Label ExPTypeLabel;
	private TextField ExPNameFeild;
	private DatePicker SrtDate;
	private DatePicker EndDate;
	private RadioButton AMRBtn;
	private RadioButton PMRBtn;
	private TextField LimitMemsField;
	private CmnPrmScheDAO PRMSCHE;
	
	public void ExPDltProc(Parent Form) {//삭제
		PRMSCHE = new CmnPrmScheDAO();
		int result = 0; 
		result = PRMSCHE.DltPrmSche(PRMSCHE.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		if(result == 1) {
			CommonService.WindowClose(Form);
			CommonService.Msg("강의 스케쥴 삭제 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		
	}
	
	public void ExPMdyProc(Parent Form) {//수정
		PRMSCHE = new CmnPrmScheDAO();
		int result = 0; 
		
		CmnPrmScheDTO DTO = new CmnPrmScheDAO().SltPrmScheOne(PRMSCHE.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		
		DTO.setPRMSCHE_Name(ExPNameFeild.getText());
		DTO.setPRMSCHE_Strdate(null);
		DTO.setPRMSCHE_Enddate(null);		
		if(AMRBtn.isSelected()) DTO.setPRMSCHE_Time("오전");
		else if(PMRBtn.isSelected()) DTO.setPRMSCHE_Time("오전");
		DTO.setPRMSCHE_LimitP(result);
		
		
		
		result = PRMSCHE.UptPrmSche(DTO);
		if(result == 1) {
			CommonService.WindowClose(Form);
			CommonService.Msg("강의 스케쥴 수정 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		
	}
	
	
	
	public void backClose(Parent Form) {
		CommonService.WindowClose(Form);
	}
	
	public void SetFxId(Parent Form) {
		TitleUserNameLabel = (Label)Form.lookup("#TitleUserNameLabel");
		PrmScheCodeLabel = (Label)Form.lookup("#PrmScheCodeLabel");
		ExPTypeLabel = (Label)Form.lookup("#ExPTypeLabel");
		ExPNameFeild = (TextField)Form.lookup("#ExPNameFeild");
		SrtDate = (DatePicker)Form.lookup("#SrtDate");
		EndDate = (DatePicker)Form.lookup("#EndDate");
		AMRBtn = (RadioButton)Form.lookup("#AMRBtn");
		PMRBtn = (RadioButton)Form.lookup("#PMRBtn");
		LimitMemsField = (TextField)Form.lookup("#LimitMemsField");
	}
}
