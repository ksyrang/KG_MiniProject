package trn.ExprogramEnroll;

import java.time.LocalDate;
import java.util.ArrayList;

import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import trn.Welcome.TrnTbVDTO;
import trn.Welcome.TrnWelcomeController;

public class TrnExPEnrollService {
	
	private TrnExpEnrollController TrnExpEnrollController;
	private Label TitleUserNameLabel;
	private ComboBox<String> ExPTypeBox;
	private TextField ExPNameFeild;
	private DatePicker SrtDate;
	private DatePicker EndDate;
	private RadioButton AMRBtn;
	private RadioButton PMRBtn;
	private TextField LimitMemField;
	private TextField ExPPriceField;
	
	public void setTrnExpEnrollController(TrnExpEnrollController trnExpEnrollController) {
		this.TrnExpEnrollController = trnExpEnrollController;
	}
	
	public void CheckSrtDate() {
		if(CommonService.CompareDate(LocalDate.now(),SrtDate.getValue())) {
			CommonService.Msg("현재 일짜 이후로 입력해주십시오.");
			SrtDate.getEditor().clear();
			return;
		}
	}
	public void CheckEndDate() {
		if(CommonService.CompareDate(SrtDate.getValue(), EndDate.getValue())) {
			CommonService.Msg("종료일을 시작일 뒤의 날짜로 입력해주십시오.");
			return;
		}
	}
	

	public void ExPErllProc(Parent myForm) {//개설 버튼 클릭 시
		//입력 형식 확인
		try {
			int tmpMB = Integer.parseInt(LimitMemField.getText());
			int tmpBD = Integer.parseInt(ExPPriceField.getText());
		} catch (NumberFormatException e) {
			CommonService.Msg("숫자만 입력 해주세요.");
			return;
		}
		//기간 순서 확인
		if(CommonService.CompareDate(SrtDate.getValue(), EndDate.getValue())) {
			CommonService.Msg("종료일을 시작일 뒤의 날짜로 입력해주십시오.");
			return;
		}

		//강의 일정용 코드 생성
		String PrmScheCodegeneration = null;//Rule : PrmSche+강의종류 + 오전/오후 + 강사 + num
		int InitCrtMems = 0;
		//강의 종류 입력
		String PrmType = ExPTypeBox.getSelectionModel().getSelectedItem();//강의종류
		//시간 입력
		String Time = null;//시간
		if(AMRBtn.isSelected()) Time = "오전";
		else if(PMRBtn.isSelected()){
			Time = "오후";
		}else {
			Time = "오전";
		}
		String getPrmCode = null;
		ArrayList<CmnPrmDTO> PrmList = new CmnPrmDAO().SltPrmAll();//DB에 있는 모든 ExP리스트 가져오기
//		System.out.println("선택한 종류코드: "+ExPTypeBox.getSelectionModel().getSelectedItem());
		for(CmnPrmDTO DTO : PrmList) {
			if(ExPTypeBox.getSelectionModel().getSelectedItem().equals(DTO.getPRM_Name())) {
				getPrmCode = DTO.getPRM_Code();
				break;
			}
		}
		//강사 정보 -> 이름찾기용
		CmnTrainerDTO TrnInfo = new CmnTrainerDAO().SltTrnOne(TrnExpEnrollController.getTrnCode()); 
		//비교해야될것 강의종류 && 오전/오후
		//선택한 프로그램을 가지고 있는 스케쥴 리스트들의 최대코드 번호
		int latestNum = new CmnPrmScheDAO().SltPrmScheCodeMaxNumbyPrmandTime(getPrmCode,Time);
		//코드 생성
		System.out.println("최신번호 결과: " + latestNum);
		PrmScheCodegeneration ="PrmSche"+"_"+PrmType+"_"+Time+"_"+TrnInfo.getTRAINER_Name()+"_"+Integer.toString(latestNum+1);
		System.out.println("생성된 코드 : "+PrmScheCodegeneration);
		CmnPrmScheDTO tmpDTO = new CmnPrmScheDTO(
				PrmScheCodegeneration,
				latestNum+1,
				CommonService.LocalDateCnvt(SrtDate.getValue()),
				CommonService.LocalDateCnvt(EndDate.getValue()),
				Time,
				Integer.parseInt(LimitMemField.getText()),
				InitCrtMems,
				Integer.parseInt(ExPPriceField.getText()),
				getPrmCode,
				TrnInfo.getTRAINER_Code(),
				ExPNameFeild.getText()
				);
		int result = new CmnPrmScheDAO().IstPrmSche(tmpDTO); 
		if(result == 1) {
			CommonService.Msg("개설완료");
			CommonService.WindowClose(myForm);
		}else {
			CommonService.Msg("이상 발생!");
		}
		
		//Table View Refresh 
		Parent wlcForm = TrnExpEnrollController.getWlcForm();
		TableView<TrnTbVDTO> refreshTable = (TableView<TrnTbVDTO>)wlcForm.lookup("#CurrentProgramTableList");
		refreshTable.getItems().clear();
		ArrayList<CmnPrmScheDTO> tmplist = new CmnPrmScheDAO().SltPrmScheAllbyTrn(TrnExpEnrollController.getTrnCode());
		ObservableList<TrnTbVDTO> TBVwlist = FXCollections.observableArrayList();
		for(CmnPrmScheDTO tmpdto: tmplist) {//PCodeColumn, PNameColumn, MembersColumn
			TBVwlist.add(new TrnTbVDTO(tmpdto.getPRMSCHE_Code(), tmpdto.getPRMSCHE_Name(), 
					Integer.toString(tmpdto.getPRMSCHE_CurrentP())));
		}			
		refreshTable.setItems(TBVwlist);		
	}
	
	public void BackProc(Parent myForm) {
		CommonService.WindowClose(myForm);
	}

	private String getlatestNumToString(String[] PrmScheCodeListbyPrmCode) {//substring(0, 2);
		/*
		 * 해당 강의 종류만 포함한 스케쥴 리스트 = PrmScheListbyPrmCode ->
		 * 해당 리스트의 최대 숫자 찾기 ->
		 * 4번째"_" 이후의 데이터(5번째 데이터)를 int형으로 변환하여 +1 -> 
		 * => 최신화된 숫자!
		 */	
		String LastestNum = null;
		String[] extractionNum = new String[PrmScheCodeListbyPrmCode.length];
//			System.out.println("입력받은 문자열개수"+PrmScheCodeListbyPrmCode.length);
		for(int i = 0; i<PrmScheCodeListbyPrmCode.length;i++) {
		String tmpdata[] =  PrmScheCodeListbyPrmCode[i].split("_");
//			String tmpdata =  PrmScheCodeListbyPrmCode[i];
		System.out.println("추출한 번호 : "+tmpdata[tmpdata.length-1]);
		extractionNum[i] = tmpdata[tmpdata.length-1];
		System.out.println("추출번호 대입 결과 : "+extractionNum[i]);
		}
		
		if(extractionNum.length > 1) {
			for(int i = 1; i<extractionNum.length;i++) {
				if(Integer.parseInt(extractionNum[i-1]) < Integer.parseInt(extractionNum[i])) {
					LastestNum = Integer.toString(Integer.parseInt(extractionNum[i])+1);
				}
			}	
		}else {
			LastestNum = Integer.toString(Integer.parseInt(extractionNum[0])+1);
		}
		System.out.println("연산 결과 : "+LastestNum);
		return LastestNum;
	}
	
	public void SetFxId(Parent myForm) {
		TitleUserNameLabel = (Label)myForm.lookup("#TitleUserNameLabel");
		ExPTypeBox = (ComboBox<String>)myForm.lookup("#ExPTypeBox");
		ExPNameFeild = (TextField)myForm.lookup("#ExPNameFeild");
		SrtDate = (DatePicker)myForm.lookup("#SrtDate");
		EndDate = (DatePicker)myForm.lookup("#EndDate");
		AMRBtn = (RadioButton)myForm.lookup("#AMRBtn");
		PMRBtn = (RadioButton)myForm.lookup("#PMRBtn");
		LimitMemField = (TextField)myForm.lookup("#LimitMemField");
		ExPPriceField = (TextField)myForm.lookup("#ExPPriceField");
			
	}

	
	
	
}
