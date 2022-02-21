package trn.Welcome;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Main.main.Controller;
import common.LogOut;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TrnWelcomeService {
	
	private TrnWelcomeController trnWelcomeController;
	
//	private TableView<TrnTbVDTO> CurrentProgramTableList;
	
//	private ObservableList<TrnTbVDTO> list = FXCollections.observableArrayList(
//			new TrnTbVDTO("hellocode", "hellocode","hellocode"),
//			new TrnTbVDTO("hellocode2", "helloname2","hellomems2")
//			);
		
	public void setTrnWelcomeController(TrnWelcomeController trnWelcomeController) {
		this.trnWelcomeController = trnWelcomeController;
	}

	//강사정보페이지
	public void TrnMgtOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/TrnMgt/KG_TRN_FX_Mgt.fxml"));
		Parent trnMgtForm;
		try {
			trnMgtForm = loader.load();
			trnWelcomeController.setTrnMgtController(loader.getController());
			trnWelcomeController.getTrnMgtController().setTrnMgtForm(trnMgtForm);
			trnWelcomeController.getTrnMgtController().setTrnCode(trnWelcomeController.getTrnCode());
		
			//강사 정보 get
			//tilte sector set
			Label titleUserName = (Label)trnMgtForm.lookup("#TitleUserNameLabel");
			
			CmnTrainerDTO tmpTrnDto = new CmnTrainerDTO(new CmnTrainerDAO().SltTrnOne(trnWelcomeController.getTrnCode()));
			titleUserName.setText(tmpTrnDto.getTRAINER_Name()+" 강사님");
			//초기 표시 설정
			TextField IDField = (TextField)trnMgtForm.lookup("#TrnIDField");//기존 이름 표시
			TextField NameField = (TextField)trnMgtForm.lookup("#TrnNameField");//기존 이름 표시
			PasswordField PWField = (PasswordField)trnMgtForm.lookup("#TrnPWField");//기존 이름 표시
			PasswordField PWCField = (PasswordField)trnMgtForm.lookup("#TrnPWCField");//기존 이름 표시
			TextField BirthField = (TextField)trnMgtForm.lookup("#TrnBirthField");//기존 생일 표시
			TextField MobileField = (TextField)trnMgtForm.lookup("#TrnMobileField");//기존 전번 표시
			RadioButton maleBtn = (RadioButton)trnMgtForm.lookup("#MaleRbtn");//기존 남성버튼
			RadioButton FeMaleBtn = (RadioButton)trnMgtForm.lookup("#FeMaleRbtn");//기존 여자버튼
			TextField Addr1Field = (TextField)trnMgtForm.lookup("#TrnAddr1");//기존 주소 표시
			TextField Addr2Field = (TextField)trnMgtForm.lookup("#TrnAddr2");//기존 주소 표시
			TextField CareerField = (TextField)trnMgtForm.lookup("#TrnCareer");//기존 커리어 표시
		
			//ID Sector
			IDField.setText(tmpTrnDto.getTRAINER_ID());
			IDField.setEditable(false);//false:입력 불가
			//Name Sector			
			NameField.setText(tmpTrnDto.getTRAINER_Name());
			//PW Sector :초기 미표시
			//Birth Sector
			BirthField.setText(Integer.toString(tmpTrnDto.getTRAINER_Birth()));
			//Mobile Sector
			MobileField.setText(Integer.toString(tmpTrnDto.getTRAINER_Mobile()));
			//Gender Sector
			ToggleGroup group = new ToggleGroup();
			maleBtn.setToggleGroup(group);
			FeMaleBtn.setToggleGroup(group);
			if(tmpTrnDto.getTRAINER_Gender().equals("남")) maleBtn.setSelected(true);
			else if(tmpTrnDto.getTRAINER_Gender().equals("여")) FeMaleBtn.setSelected(true);
			else maleBtn.setSelected(true);
			//Addr Sector
			String[] AddrSplit = tmpTrnDto.getTRAINER_Addr().split("/");
			Addr1Field.setText(AddrSplit[0]);
			Addr2Field.setText(AddrSplit[1]);
			//Career Sector
			CareerField.setText(Integer.toString(tmpTrnDto.getTRAINER_Career()));
		
			
			Stage stage = new Stage();
			stage.setScene(new Scene(trnMgtForm));
			stage.setTitle("강사 정보수정 페이지");
			stage.show();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//프로그램 개설 페이지
	public void ExPEnrollOpen(Parent form) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/ExprogramEnroll/KG_TRN_FX_EXProgramEnroll.fxml"));
		Parent trnExPEnrollFrom;
		try {
			trnExPEnrollFrom = loader.load();
			trnWelcomeController.setTrnExpEnrollController(loader.getController());
			trnWelcomeController.getTrnExpEnrollController().setTrnExpEnrollForm(trnExPEnrollFrom);
			trnWelcomeController.getTrnExpEnrollController().setTrnCode(trnWelcomeController.getTrnCode());
			trnWelcomeController.getTrnExpEnrollController().setTrnWlcForm(trnWelcomeController.getTrnWelcomeForm());
			
			Label titleUserName = (Label)trnExPEnrollFrom.lookup("#TitleUserNameLabel");
			CmnTrainerDTO tmpTrnDto = new CmnTrainerDTO(new CmnTrainerDAO().SltTrnOne(trnWelcomeController.getTrnCode()));
			titleUserName.setText(tmpTrnDto.getTRAINER_Name()+" 강사님");

			ComboBox<String> ExPTypeBox = (ComboBox<String>)trnExPEnrollFrom.lookup("#ExPTypeBox");
			TextField ExPNameFeild = (TextField)trnExPEnrollFrom.lookup("#ExPNameFeild");
			RadioButton AMRBtn = (RadioButton)trnExPEnrollFrom.lookup("#AMRBtn");
			RadioButton PMRBtn = (RadioButton)trnExPEnrollFrom.lookup("#PMRBtn");
			DatePicker SrtDate = (DatePicker)trnExPEnrollFrom.lookup("#SrtDate");
			DatePicker EndDate = (DatePicker)trnExPEnrollFrom.lookup("#EndDate");
			TextField LimitMemField = (TextField)trnExPEnrollFrom.lookup("#LimitMemField");
			
			//set PRMType
			ArrayList<CmnPrmDTO> TypeBoxList = new CmnPrmDAO().SltPrmAll();
			for(CmnPrmDTO DTO : TypeBoxList) {
				ExPTypeBox.getItems().add(DTO.getPRM_Name());
			}
			//Set Time Button
			ToggleGroup group = new ToggleGroup();
			AMRBtn.setToggleGroup(group);
			PMRBtn.setToggleGroup(group);
			AMRBtn.setSelected(true);
			
			
			
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPEnrollFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	//프로그램 관리 페이지
	public void ExPMgtOpen(Parent form) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPMgtFrom;
		try {
			trnExPMgtFrom = loader.load();
			trnWelcomeController.setTrnExpMgtController(loader.getController());
			trnWelcomeController.getTrnExpMgtController().setTrnExProgramMgtForm(trnExPMgtFrom);
			trnWelcomeController.getTrnExpMgtController().setTrnCode(trnWelcomeController.getTrnCode());
			trnWelcomeController.getTrnExpMgtController().setTrnWelcomForm(form);
			Label titleUserName = (Label)trnExPMgtFrom.lookup("#TitleUserNameLabel");
			CmnTrainerDTO tmpTrnDto = new CmnTrainerDTO(new CmnTrainerDAO().SltTrnOne(trnWelcomeController.getTrnCode()));
			titleUserName.setText(tmpTrnDto.getTRAINER_Name()+" 강사님");
			
			//표시용 컨트롤러(오브젝트) 초기 선언
			Label PrmScheCodeLabel = (Label)trnExPMgtFrom.lookup("#PrmScheCodeLabel");
			Label ExPTypeLabel = (Label)trnExPMgtFrom.lookup("#ExPTypeLabel");
			TextField ExPNameFeild = (TextField)trnExPMgtFrom.lookup("#ExPNameFeild");
			DatePicker SrtDate = (DatePicker)trnExPMgtFrom.lookup("#SrtDate");
			DatePicker EndDate = (DatePicker)trnExPMgtFrom.lookup("#EndDate");//seteditalbe 존재
			RadioButton AMRBtn = (RadioButton)trnExPMgtFrom.lookup("#AMRBtn");
			RadioButton PMRBtn = (RadioButton)trnExPMgtFrom.lookup("#PMRBtn");
			Label ExPMgtCrtMemDisLabel = (Label)trnExPMgtFrom.lookup("#ExPMgtCrtMemDisLabel");
			TextField LimitMemsField = (TextField)trnExPMgtFrom.lookup("#LimitMemsField");
			
			//데이터 인풋용 컨트롤러(오브젝트) 초기 선언
			Label ExPCodeDisLabel= (Label)form.lookup("#ExPCodeDisLabel");
			Label ExPTypeDisLabel= (Label)form.lookup("#ExPTypeDisLabel");
		    Label ExPNameDisLabel= (Label)form.lookup("#ExPNameDisLabel");
		    DatePicker ExpSrtDateDisPicker= (DatePicker)form.lookup("#ExpSrtDateDisPicker");
		    DatePicker ExpEndDateDisPicker= (DatePicker)form.lookup("#ExpEndDateDisPicker");
		    Label ExPTimeDisLabel= (Label)form.lookup("#ExPTimeDisLabel");
		    Label ExPCrtMemsDisLabel= (Label)form.lookup("#ExPCrtMemsDisLabel");
		    Label ExPLmtMemsDisLabel= (Label)form.lookup("#ExPLmtMemsDisLabel");
			//Set the get Info data
		    PrmScheCodeLabel.setText(ExPCodeDisLabel.getText());
		    ExPTypeLabel.setText(ExPTypeDisLabel.getText());
		    ExPNameFeild.setText(ExPNameDisLabel.getText());
		    SrtDate.setValue(ExpSrtDateDisPicker.getValue());
		    EndDate.setValue(ExpEndDateDisPicker.getValue());
		    if(ExPTimeDisLabel.equals("오전"))AMRBtn.setSelected(true);
		    else if(ExPTimeDisLabel.equals("오후"))PMRBtn.setSelected(true);
		    else AMRBtn.setSelected(true);		    
		    LimitMemsField.setText(ExPLmtMemsDisLabel.getText());
		    
			//Not Use
//			ListView<String> getPrmScheInfo = (ListView<String>)form.lookup("#Programinfo");
//			ObservableList<String> InfoList = getPrmScheInfo.getItems();
//			String[] WashingData = new String[InfoList.size()];
//			for(int i =0; i < InfoList.size();i++) WashingData[i] = getPrmScheData(InfoList.get(i));
//			//code
//			PrmScheCodeLabel.setText(WashingData[0]);
//			//type
//			ExPTypeLabel.setText(WashingData[1]);
//			//name
//			ExPNameFeild.setText(WashingData[2]);
//			//Date
//			SrtDate.setValue(CommonService.StringtoLocalDate(WashingData[3]));
//			EndDate.setValue(CommonService.StringtoLocalDate(WashingData[4]));
//			//Time
//			ToggleGroup group = new ToggleGroup();
//			AMRBtn.setToggleGroup(group);
//			PMRBtn.setToggleGroup(group);
//			if(WashingData[5].equals("오전")) AMRBtn.setSelected(true);
//			else if(WashingData[6].equals("오후")) PMRBtn.setSelected(true);
//			else AMRBtn.setSelected(true);
//			LimitMemsField.setText(WashingData[7]);
//			CmnPrmScheDTO ScheDto = new CmnPrmScheDAO().SltPrmScheOne(trnCode);
//			ArrayList<CmnPrmScheDTO> test = new CmnPrmScheDAO().SltPrmScheAllbyTrn(trnCode);
//			CmnPrmDTO PrmDto = new CmnPrmDAO().SltPrmOne(ScheDto.getPRM_Code());
//		
//			//Type
//			ExPTypeLabel.setText(PrmDto.getPRM_Name());
//			//Name
//			ExPNameFeild.setText(ScheDto.getPRMSCHE_Name());
//			//Date
//			System.out.println(CommonService.DateCnvt(ScheDto.getPRMSCHE_Strdate()));
//			SrtDate.setValue(CommonService.DateCnvt(ScheDto.getPRMSCHE_Strdate()));
//			EndDate.setValue(CommonService.DateCnvt(ScheDto.getPRMSCHE_Enddate()));
//			//Time
//			ToggleGroup group = new ToggleGroup();
//			AMRBtn.setToggleGroup(group);
//			PMRBtn.setToggleGroup(group);
//			if(ScheDto.getPRMSCHE_Time().equals("오전")) AMRBtn.setSelected(true);
//			else if(ScheDto.getPRMSCHE_Time().equals("오후")) PMRBtn.setSelected(true);
//			else AMRBtn.setSelected(true);
//			LimitMemsField.setText(Integer.toString(ScheDto.getPRMSCHE_LimitP()));

			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPMgtFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
//	public void InitTable(TableView tableView) {
//		CurrentProgramTableList = tableView;
//	}
	//테이블뷰에 표시된 리스트 중 하나의 프로그램 선택 시
	public void programclickProc(Parent form) {
		TableView<TrnTbVDTO> TbVw = (TableView<TrnTbVDTO>)form.lookup("#CurrentProgramTableList");
		TrnTbVDTO tmp = TbVw.getSelectionModel().getSelectedItem();
		CmnPrmScheDTO getPrmSchetmpDto;
		try {//선택 오류로 인한 null값 입력 시 대처
			getPrmSchetmpDto = new CmnPrmScheDAO().SltPrmScheOne(tmp.getPCodeColumn());	
		} catch (NullPointerException e) {
			return;
		}
		
		
		Label ExPCodeDisLabel= (Label)form.lookup("#ExPCodeDisLabel");
		Label ExPTypeDisLabel= (Label)form.lookup("#ExPTypeDisLabel");
	    Label ExPNameDisLabel= (Label)form.lookup("#ExPNameDisLabel");
	    DatePicker ExpSrtDateDisPicker= (DatePicker)form.lookup("#ExpSrtDateDisPicker");
	    DatePicker ExpEndDateDisPicker= (DatePicker)form.lookup("#ExpEndDateDisPicker");
	    Label ExPTimeDisLabel= (Label)form.lookup("#ExPTimeDisLabel");
	    Label ExPCrtMemsDisLabel= (Label)form.lookup("#ExPCrtMemsDisLabel");
	    Label ExPLmtMemsDisLabel= (Label)form.lookup("#ExPLmtMemsDisLabel");
		
	    //Dis PRMSCHE Code
	    ExPCodeDisLabel.setText(getPrmSchetmpDto.getPRMSCHE_Code());
	    //Dis ExPType
	    ExPTypeDisLabel.setText(new CmnPrmDAO().SltPrmOne(getPrmSchetmpDto.getPRM_Code()).getPRM_Name());
	    //Dis ExPName
	    ExPNameDisLabel.setText(getPrmSchetmpDto.getPRMSCHE_Name());
	    //Dis Date
	    ExpSrtDateDisPicker.setEditable(false);//False : 입력 불가 상태
	    ExpSrtDateDisPicker.setValue(CommonService.DateCnvt(getPrmSchetmpDto.getPRMSCHE_Strdate()));
	    ExpEndDateDisPicker.setEditable(false);//False : 입력 불가 상태
	    ExpEndDateDisPicker.setValue(CommonService.DateCnvt(getPrmSchetmpDto.getPRMSCHE_Enddate()));
	    //Dis Time
	    ExPTimeDisLabel.setText(getPrmSchetmpDto.getPRMSCHE_Time());
	    //Dis Mems
	    ExPCrtMemsDisLabel.setText(Integer.toString(getPrmSchetmpDto.getPRMSCHE_CurrentP()));
	    ExPLmtMemsDisLabel.setText(Integer.toString(getPrmSchetmpDto.getPRMSCHE_LimitP()));
	    
	    //Not Use
//		ListView<String> SetPrmScheInfo = (ListView<String>)form.lookup("#Programinfo");
//		System.out.println(CommonService.DateCnvt((getPrmSchetmpDto.getPRMSCHE_Strdate())).toString());
//		String[] Line =  new String[8];
//		Line[0] = "강의 코드 : "+getPrmSchetmpDto.getPRM_Code();
//		Line[1] = "강의 타입 : "+new CmnPrmDAO().SltPrmOne(getPrmSchetmpDto.getPRM_Code()).getPRM_Name();
//		Line[2] = "강의 이름 : "+getPrmSchetmpDto.getPRMSCHE_Name();
//		Line[3] = "시작 일자 : "+CommonService.DateCnvt((getPrmSchetmpDto.getPRMSCHE_Strdate())).toString();
//		Line[4] = "종료 일자 : "+CommonService.DateCnvt(getPrmSchetmpDto.getPRMSCHE_Enddate()).toString();
//		Line[5] = "강의 시간 : "+getPrmSchetmpDto.getPRMSCHE_Time();
//		Line[6] = "인원/정원 : "+getPrmSchetmpDto.getPRMSCHE_CurrentP()+"/"+getPrmSchetmpDto.getPRMSCHE_LimitP();
//		Line[7] = "강의 가격 : "+getPrmSchetmpDto.getPRMSCHE_Price()+"원";		
//		
//		for(int i = 0; i<Line.length; i++) SetPrmScheInfo.getItems().add(Line[i]);
	}

	public void ShutDown(Parent form) {
		CommonService.WindowClose(form);
	}
	
//	public void LogOut() {
//		logOut.LogOut();
//	}
	
	//NotUse
//	private String getPrmScheData(String data) {
//		String Data = null;
//		if(data.startsWith("강의 코드 : ")) {
//			Data = data.replace("강의 코드 : ", null);	
//		}else if(data.startsWith("강의 타입 : ")) {
//			Data = data.replace("강의 타입 : ", null);
//		}else if(data.startsWith("강의 이름 : ")) {
//			Data = data.replace("강의 이름 : ", null);
//		}else if(data.startsWith("시작 일자 : ")) {
//			Data = data.replace("시작 일자 : ", null);
//		}else if(data.startsWith("종료 일자 : ")) {
//			Data = data.replace("종료 일자 : ", null);
//		}else if(data.startsWith("인원/정원 : ")) {
//			Data = data.replace("인원/정원 : ", null);
//		}else if(data.startsWith("강의 가격 : ")) {
//			Data = data.replace("강의 가격 : ", null);
//		}else {
//			Data = null;
//		}
//		return Data;
//	}

}
