package mem.BuyingType;

import java.net.URL;
import java.util.ResourceBundle;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;


public class MEM_BuyingTypeController implements Initializable{
	
	//controller(object)Init
	@FXML private Label TitleMemNameLabel;
    @FXML private Button PayBtn;
    @FXML private Button Back;
    @FXML private RadioButton CardBtn;
    @FXML private RadioButton AccountBtn;
    @FXML private RadioButton KakaoPayBtn;
    @FXML private RadioButton NaverPayBtn;
//    @FXML private Text PayCodeLabel;
    @FXML private Text ScheNameLabel;
    @FXML private Text SchePriceLabel;
    @FXML private Text PayDateLabel;
    
	private Parent buyingTypeForm;
	private MEM_BuyingTypeService buyingTypeSvc;;
	
	private String UserCode;
	private String MEMSHIPSCHE_Code;
	private String PRMSCHE_Code;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buyingTypeSvc = new MEM_BuyingTypeService();
		buyingTypeSvc.setBuyingTypeController(this);
	}
	
	public void setBuyingTypeForm(Parent buyingTypeForm) {
		this.buyingTypeForm = buyingTypeForm;
		buyingTypeSvc.SetFXId(buyingTypeForm);
//		System.out.println("BuyingType 1 : "+buyingTypeForm);
//		System.out.println("BuyingType 2 : "+this.buyingTypeForm);
	}
	
	
	public Parent getBuyingTypeForm() {
		return buyingTypeForm;
	}
	
	public String getMEMSHIPSCHE_Code() {
		return MEMSHIPSCHE_Code;
	}

	public void setMEMSHIPSCHE_Code(String mEMSHIPSCHE_Code) {
		MEMSHIPSCHE_Code = mEMSHIPSCHE_Code;
	}

	public String getPRMSCHE_Code() {
		return PRMSCHE_Code;
	}

	public void setPRMSCHE_Code(String pRMSCHE_Code) {
		PRMSCHE_Code = pRMSCHE_Code;
	}

	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}

	public void PaymentProc() {
		buyingTypeSvc.PaymentProc();
		
	}
	
	public void BackProc() {
		buyingTypeSvc.BackProc(buyingTypeForm);
		
	}
	
	public void PayInfoInit() {
		// 스케쥴의 데이터
		// 회원권 스케쥴 데이터
		CmnMemShipScheDTO MsScheDTO = new CmnMemShipScheDAO().SltMemShipScheOne(MEMSHIPSCHE_Code);
		// ExP 스케쥴 데이터
		CmnPrmScheDTO PrmScheDTO = new CmnPrmScheDAO().SltPrmScheOne(PRMSCHE_Code);

		// 가저온 스케쥴 데이터의 강의 Type 데이터
		// 회원권 스케쥴 데이터로 회원권 정보 get
		CmnMemShipDTO MsDTO = new CmnMemShipDAO().SltMemShipOne(MsScheDTO.getMEMSHIP_Code());
		// ExP 스케쥴 데이터로 ExP정보 get

		// 프로그램 명 Sector
		if ((PrmScheDTO == null) && (MsScheDTO != null)) {// 회원권 스케쥴이 데이터O, Exp스케쥴X
			ScheNameLabel.setText(MsDTO.getMEMSHIP_Type() + "개월 회원권");
		} else if ((PrmScheDTO != null) && (MsScheDTO == null)) {// 회원권 스케쥴이 데이터X, Exp스케쥴O
			ScheNameLabel.setText(PrmScheDTO.getPRMSCHE_Name());
		} else {
			CommonService.WindowClose(buyingTypeForm);
			CommonService.Msg("프로그램명 로드 이상 발생 문의 부탁 드립니다.");
		}
		// 금액 Sector
		if ((PrmScheDTO == null) && (MsScheDTO != null)) {// 회원권 스케쥴이 데이터O, Exp스케쥴X
			SchePriceLabel.setText(Integer.toString(MsDTO.getMEMSHIP_Price()) + "원");// 금액
		} else if ((PrmScheDTO != null) && (MsScheDTO == null)) {// 회원권 스케쥴이 데이터X, Exp스케쥴O
			SchePriceLabel.setText(Integer.toString(PrmScheDTO.getPRMSCHE_Price()));
		} else {
			CommonService.WindowClose(buyingTypeForm);
			CommonService.Msg("금액 로드 이상 발생 문의 부탁 드립니다.");
		}
		//결제 일자 sector
		PayDateLabel.setText(CommonService.getNowDatetoString());
	}
	

}
