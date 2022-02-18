package mem.BuyingType;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPayDAO;
import common.CmnPayDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class MEM_BuyingTypeService {

	private MEM_BuyingTypeController MyController;
	private Parent MyForm;
	private Label TitleMemNameLabel;
	private Button PayBtn;
	private Button Back;
	private RadioButton CardBtn;
	private RadioButton AccountBtn;
	private RadioButton KakaoPayBtn;
	private RadioButton NaverPayBtn;
//	private Text PayCodeLabel;
	private Text ScheNameLabel;
	private Text SchePriceLabel;
	private Text PayDateLabel;

	public void setBuyingTypeController(MEM_BuyingTypeController MyController) {
		this.MyController = MyController;
	}

	public void PaymentProc() {
//		buyingTypeController.getBuyingTypeForm()
		CmnPayDTO PayDTO = new CmnPayDTO();
		String PayType;
		int result = 0;
		//결제 타입에 따른 결제 코드 생성. 	
		if(CardBtn.isSelected()) {
			PayType = "카드";
		}else if(AccountBtn.isSelected()) {
			PayType = "무통장";
		}else if(KakaoPayBtn.isSelected()) {
			PayType = "카카오";
		}else if(NaverPayBtn.isSelected()) {
			PayType = "네이버";
		}else {
			CommonService.Msg("결제타입 선택 오류");
			return;
		}
		PayDTO.setPAY_Type(PayType);
		PayDTO.setMEM_Code(MyController.getUserCode());
		PayDTO.setMEMSHIPSCHE_Code(MyController.getMEMSHIPSCHE_Code());
		PayDTO.setPRMSCHE_Code(MyController.getPRMSCHE_Code());
		PayDTO.setPAY_Date(CommonService.CnvtsqlDate(new Date()));
		PayDTO.setPAY_Code(MyController.getUserCode()+PayDTO.getPAY_Date()+"코드번호");
		
		//입력 결과
		result = new CmnPayDAO().Istpay(PayDTO);
		if(result == 1) {
			CommonService.WindowClose(MyForm);
			CommonService.Msg("결제완료");
		}else {
			CommonService.Msg("결제 이상 발생");
			return;
		}
	}

	public void BackProc(Parent MyForm) {
		CommonService.WindowClose(MyForm);

	}

	public void SetFXId(Parent MyForm) {
		this.MyForm = MyForm;
		TitleMemNameLabel = (Label) MyForm.lookup("#TitleMemNameLabel");
		PayBtn = (Button) MyForm.lookup("#PayBtn");
		Back = (Button) MyForm.lookup("#Back");
		CardBtn = (RadioButton) MyForm.lookup("#CardBtn");
		AccountBtn = (RadioButton) MyForm.lookup("#AccountBtn");
		KakaoPayBtn = (RadioButton) MyForm.lookup("#KakaoPayBtn");
		NaverPayBtn = (RadioButton) MyForm.lookup("#NaverPayBtn");
//		PayCodeLabel = (Text) MyForm.lookup("#PayCodeLabel");
		ScheNameLabel = (Text) MyForm.lookup("#ScheNameLabel");
		SchePriceLabel = (Text) MyForm.lookup("#SchePriceLabel");
		PayDateLabel = (Text) MyForm.lookup("#PayDateLabel");

		ToggleGroup group = new ToggleGroup();
		CardBtn.setToggleGroup(group);
		AccountBtn.setToggleGroup(group);
		KakaoPayBtn.setToggleGroup(group);
		NaverPayBtn.setToggleGroup(group);
	}
}