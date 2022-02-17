package trn.ExprogramEnroll;

import java.util.ArrayList;

import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
	

	public void ExPErllProc(Parent myForm, String TrnCode) {
		int InitCrtMems = 0;
		String Time = null;
		String PrmType = ExPTypeBox.getSelectionModel().getSelectedItem();
		if(AMRBtn.isSelected()) Time = "오전";
		else {
			Time = "오후";
		}
		String getPrmCode = null;
		ArrayList<CmnPrmDTO> PrmList = new CmnPrmDAO().SltPrmAll();
//		System.out.println("선택한 종류코드: "+ExPTypeBox.getSelectionModel().getSelectedItem());
		for(CmnPrmDTO DTO : PrmList) {
			if(ExPTypeBox.getSelectionModel().getSelectedItem().equals(DTO.getPRM_Name())) {
				getPrmCode = DTO.getPRM_Code();
				break;
//				System.out.println("코드로 찾은 종류: "+getPrmCode);
			}
		}
		//강사 정보 -> 이름찾기용
		CmnTrainerDTO TrnInfo = new CmnTrainerDAO().SltTrnOne(TrnCode); 
		//강의 일정용 코드 생성
		String PrmScheCodegeneration = null;//Rule : PrmSche+강의종류 + 오전/오후 + 강사 + num

		ArrayList<CmnPrmScheDTO> PrmScheListbyPrmCode = new CmnPrmScheDAO().SltPrmScheAllbyPrm(getPrmCode);
//		for(CmnPrmScheDTO e: PrmScheListbyPrmCode) System.out.println("선택한 강의가 포함된 EXPSCHE 코드 : " + e.getPRMSCHE_Code());

		String[] PrmScheListbyPrmCodeList = new String[PrmScheListbyPrmCode.size()];
		int i=0;
		for(CmnPrmScheDTO DTO: PrmScheListbyPrmCode) {
			PrmScheListbyPrmCodeList[i] = DTO.getPRMSCHE_Code();
			i++;
		}
		String PrmScheNumber = getlatestNumToString(PrmScheListbyPrmCodeList);
		
		//코드 생성
		PrmScheCodegeneration ="PrmSche"+"_"+PrmType+"_"+Time+"_"+TrnInfo.getTRAINER_Name()+"_"+PrmScheNumber;
//		PrmScheCodegeneration ="PrmSche"+"_"+PrmType+"_"+Time+"_"+TrnInfo.getTRAINER_Name();
		System.out.println("생성된 코드 : "+PrmScheCodegeneration);
		
		CmnPrmScheDTO tmpDTO = new CmnPrmScheDTO(
				PrmScheCodegeneration,
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
		
	}
	
	public void BackProc(Parent myForm) {
		CommonService.WindowClose(myForm);
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
//			System.out.println("추출한 번호 : "+tmpdata[tmpdata.length-1]);
			extractionNum[i] = tmpdata[tmpdata.length-1];
		}
//			LastestNum =
		for(int i = 1; i<extractionNum.length;i++) {
			if(Integer.parseInt(extractionNum[i-1]) < Integer.parseInt(extractionNum[i])) {
				LastestNum = Integer.toString(Integer.parseInt(extractionNum[i])+1);
				System.out.println(LastestNum);
			}
		}
		return LastestNum;
	}
	
	
	
	
}
