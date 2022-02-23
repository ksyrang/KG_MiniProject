package trn.EXProgramMgt;

import java.util.ArrayList;
import java.util.Optional;

import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
	private Label CrtMemsDis;
	private CmnPrmScheDAO PRMSCHEDAO;
	private TextField PriceField;
	
	public void setTrnExPMgtController(TrnExPMgtController trnExPMgtController) {
		this.TrnExPMgtController = trnExPMgtController;
	}
	
	//삭제 버튼 클릭시
	public void ExPDltProc(Parent MyForm ,Parent WelcomeForm) {
		boolean Checkresult = CommonService.CheckMsg("삭제 하시겠습니까?");
		if(!Checkresult) {
			return;			
		}
		PRMSCHEDAO = new CmnPrmScheDAO();
		int CheckCurrentMem = PRMSCHEDAO.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_CurrentP();
		if(CheckCurrentMem > 0) {
			CommonService.Msg("수강 인원이 존재하여 삭제 할 수 없습니다.");
			return;
		}
		int result = 0; 
		result = PRMSCHEDAO.DltPrmSche(PRMSCHEDAO.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		if(result == 1) {
			CommonService.WindowClose(MyForm);
			CommonService.Msg("강의 스케쥴 삭제 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		//웰컴 페이지 테이블 뷰 업데이트
		WlcTableRefresh(WelcomeForm);		
	}
	
	//수정버튼 클릭시
	public void ExPMdyProc(Parent MyForm, Parent WelcomeForm) {
		try {
			int tmpMB = Integer.parseInt(LimitMemsField.getText());
		} catch (NumberFormatException e) {
			CommonService.Msg("숫자만 입력 해주세요.");
			return;
		}
		
		boolean Checkresult = CommonService.CheckMsg("수정 하시겠습니까?");
		if(!Checkresult) {
			return;			
		}
		
		if(CommonService.CompareDate(SrtDate.getValue(), EndDate.getValue())) {
			CommonService.Msg("종료일을 시작일 뒤의 날짜로 입력해주십시오.");
			return;
		}else if(Integer.parseInt(CrtMemsDis.getText().substring(0, 1)) > Integer.parseInt(LimitMemsField.getText())){
			CommonService.Msg("현재 인원보다 정원을 낮출 수 없습니다.");
			return;
		}
		PRMSCHEDAO = new CmnPrmScheDAO();
		int result = 0; 
		CmnPrmScheDTO DTO = new CmnPrmScheDAO().SltPrmScheOne(PRMSCHEDAO.SltPrmScheOne(PrmScheCodeLabel.getText()).getPRMSCHE_Code());
		DTO.setPRMSCHE_Name(ExPNameFeild.getText());
		DTO.setPRMSCHE_Strdate(CommonService.LocalDateCnvt(SrtDate.getValue()));
		DTO.setPRMSCHE_Enddate(CommonService.LocalDateCnvt(EndDate.getValue()));		
		if(AMRBtn.isSelected()) {
			DTO.setPRMSCHE_Time("오전");
		}
		else if(PMRBtn.isSelected()) {
			DTO.setPRMSCHE_Time("오후");
		}
		else { 
			CommonService.Msg("시간을 선택하여 주세요");
			return;
		}
		DTO.setPRMSCHE_LimitP(Integer.parseInt(LimitMemsField.getText()));//Integer.parseInt(String data);//int형 변환
		
		DTO.setPRMSCHE_Price(Integer.parseInt(PriceField.getText()));
		
		result = PRMSCHEDAO.UptPrmSche(DTO);
		if(result == 1) {
			CommonService.WindowClose(MyForm);
			CommonService.Msg("강의 스케쥴 수정 완료");
		}else {
			CommonService.Msg("이상 발생 확인 요망");
		}
		//웰컴 페이지 테이블 뷰 업데이트
		WlcTableRefresh(WelcomeForm);
	
	}
		
	public void backClose(Parent MyForm) {
		CommonService.WindowClose(MyForm);
		
	}
	private void WlcTableRefresh(Parent WelcomeForm) {
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
		CrtMemsDis = (Label)MyForm.lookup("#ExPMgtCrtMemDisLabel");
		PriceField = (TextField)MyForm.lookup("#PriceField");
	}
	
	
}

