package trn.EXProgramMgt;

import java.util.ArrayList;

import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.Welcome.TrnTbVDTO;

public class TrnExPMgtService {
	
	
	private TrnExPMgtController TrnExPMgtController;
	private Label TitleUserNameLabel;
	private Label PrmScheCodeLabel;
	private Label ExPTypeLabel;
	private TextField ExPNameFeild;
	private DatePicker SrtDate;
	private DatePicker EndDate;
	private RadioButton AMRBtn;
	private RadioButton PMRBtn;
	private TextField LimitMemsField;
	private CmnPrmScheDAO PRMSCHEDAO;
	
	public void setTrnExPMgtController(TrnExPMgtController trnExPMgtController) {
		this.TrnExPMgtController = trnExPMgtController;
	}
	
	
	public void ExPDltProc(Parent MyForm ,Parent WelcomeForm) {//삭제
		
		PRMSCHEDAO = new CmnPrmScheDAO();
		int result = 0; 
		result = PRMSCHEDAO.DltPrmSche(PRMSCHEDAO.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		if(result == 1) {
			CommonService.WindowClose(MyForm);
			CommonService.Msg("강의 스케쥴 삭제 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		//Table View Refresh 
		TableView<TrnTbVDTO> refreshTable = (TableView<TrnTbVDTO>)WelcomeForm.lookup("#CurrentProgramTableList");
		refreshTable.getItems().clear();
		ArrayList<CmnPrmScheDTO> tmplist = new CmnPrmScheDAO().SltPrmScheAllbyTrn(TrnExPMgtController.getTrnCode());
		ObservableList<TrnTbVDTO> TBVwlist = FXCollections.observableArrayList();
		for(CmnPrmScheDTO tmpdto: tmplist) {//PCodeColumn, PNameColumn, MembersColumn
			TBVwlist.add(new TrnTbVDTO(tmpdto.getPRMSCHE_Code(), tmpdto.getPRMSCHE_Name(), 
					Integer.toString(tmpdto.getPRMSCHE_CurrentP())));
		}			
		refreshTable.setItems(TBVwlist);		
		
		
	}
	
	public void ExPMdyProc(Parent MyForm, Parent WelcomeForm) {//수정
		
		if(CommonService.CompareDate(SrtDate.getValue(), EndDate.getValue())) {
			CommonService.Msg("종료일을 시작일 뒤의 날짜로 입력해주십시오.");
			return;
		}
		
		PRMSCHEDAO = new CmnPrmScheDAO();
		int result = 0; 
		CmnPrmScheDTO DTO = new CmnPrmScheDAO().SltPrmScheOne(PRMSCHEDAO.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		DTO.setPRMSCHE_Name(ExPNameFeild.getText());
		DTO.setPRMSCHE_Strdate(CommonService.LocalDateCnvt(SrtDate.getValue()));
		DTO.setPRMSCHE_Enddate(CommonService.LocalDateCnvt(EndDate.getValue()));		
		if(AMRBtn.isSelected()) DTO.setPRMSCHE_Time("오전");
		else if(PMRBtn.isSelected()) DTO.setPRMSCHE_Time("오전");
		DTO.setPRMSCHE_LimitP(Integer.parseInt(LimitMemsField.getText()));//Integer.parseInt(String data);//int형 변환

		result = PRMSCHEDAO.UptPrmSche(DTO);
		if(result == 1) {
			CommonService.WindowClose(MyForm);
			CommonService.Msg("강의 스케쥴 수정 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		
		//Table View Refresh 
		TableView<TrnTbVDTO> refreshTable = (TableView<TrnTbVDTO>)WelcomeForm.lookup("#CurrentProgramTableList");
		refreshTable.getItems().clear();
		ArrayList<CmnPrmScheDTO> tmplist = new CmnPrmScheDAO().SltPrmScheAllbyTrn(TrnExPMgtController.getTrnCode());
		ObservableList<TrnTbVDTO> TBVwlist = FXCollections.observableArrayList();
		for(CmnPrmScheDTO tmpdto: tmplist) {//PCodeColumn, PNameColumn, MembersColumn
			TBVwlist.add(new TrnTbVDTO(tmpdto.getPRMSCHE_Code(), tmpdto.getPRMSCHE_Name(), 
					Integer.toString(tmpdto.getPRMSCHE_CurrentP())));
		}			
		refreshTable.setItems(TBVwlist);		
	}
		
	public void backClose(Parent MyForm) {
		CommonService.WindowClose(MyForm);
		
	}
	
	public void SetFxId(Parent MyForm) {
		TitleUserNameLabel = (Label)MyForm.lookup("#TitleUserNameLabel");
		PrmScheCodeLabel = (Label)MyForm.lookup("#PrmScheCodeLabel");
		ExPTypeLabel = (Label)MyForm.lookup("#ExPTypeLabel");
		ExPNameFeild = (TextField)MyForm.lookup("#ExPNameFeild");
		SrtDate = (DatePicker)MyForm.lookup("#SrtDate");
		EndDate = (DatePicker)MyForm.lookup("#EndDate");
		AMRBtn = (RadioButton)MyForm.lookup("#AMRBtn");
		PMRBtn = (RadioButton)MyForm.lookup("#PMRBtn");
		LimitMemsField = (TextField)MyForm.lookup("#LimitMemsField");
	}
}
