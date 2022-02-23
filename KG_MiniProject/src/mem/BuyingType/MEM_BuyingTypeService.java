package mem.BuyingType;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.sql.Date;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemScheDAO;
import common.CmnMemScheDTO;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPayDAO;
import common.CmnPayDTO;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mem.Welcome.MEM_WelcomeDAO;
import mem.Welcome.MEM_WelcomeDTO;
import mem.Welcome.MEM_WelcomeMgtTable;

public class MEM_BuyingTypeService {

	private MEM_BuyingTypeController buyingTypeController;
	private Parent MyForm;
	private Parent exProgramForm;
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
	private String SltPrmScheName;

	public void setMEM_BuyingTypeController(MEM_BuyingTypeController buyingTypeController) {
		this.buyingTypeController = buyingTypeController;
	}

	public void PaymentProc() {
//		buyingTypeController.getBuyingTypeForm()
		CmnPayDTO PayDTO = new CmnPayDTO();
		String PayType;
		int result = 0;
		
		//결제 타입 입력. 	
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
		//DB 입력을 위한 메소드변수 선언 
		CmnPayDAO payDao = new CmnPayDAO();	
		//PayDB의 순서 확인		
		int maxCodeNum = payDao.PayMaxCodeNum() + 1;
		//결제일 입력
		Date strPayDate = CommonService.CnvtsqlDate(new java.util.Date());
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String payDate = transFormat.format(strPayDate);
		
		//PayDB 입력용 변수에 인스턴스
		//PayCode
		PayDTO.setPAY_Code(buyingTypeController.getUserCode()+ payDate + maxCodeNum);
		//PayCodeNum
		PayDTO.setPAYCode_Num(maxCodeNum);
		//PayType
		PayDTO.setPAY_Type(PayType);
		//PayDate
		PayDTO.setPAY_Date(CommonService.CnvtsqlDate(new java.util.Date()));		
		//MemCode
		PayDTO.setMEM_Code(buyingTypeController.getUserCode());
		
		//MEMSHIPSCHE_CODE
		CmnMemScheDAO cmnMemScheDao = new CmnMemScheDAO();
		CmnMemScheDTO cmnMemScheDto = new CmnMemScheDTO();
		
		//회원권/프로그램 구분
		int MemShipScheReuslt = 0; //회원권스케쥴 결과
		int PrmcheReuslt = 0; //프로그램스케쥴 DB입력 결과
		int PayResult = 0;//결재 DB입력 결과
		int MemScheResult = 0;//맴버스케쥴 DB입력 결과
		
		if(buyingTypeController.getCmnMemShipScheDtoforRecive() != null) { 
			//회원권스케쥴 DB 입력
			CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
			MemShipScheReuslt = cmnMemShipScheDao.IstMemShipSche(buyingTypeController.getCmnMemShipScheDtoforRecive());
//			MemShipScheReuslt = new CmnMemShipScheDAO().IstMemShipSche(buyingTypeController.getCmnMemShipScheDtoforRecive());			
			//PAyDB에 ScheCode입력
			PayDTO.setMEMSHIPSCHE_Code(buyingTypeController.getCmnMemShipScheDtoforRecive().getMEMSHIPSCHE_Code());
			
			cmnMemScheDto.setMEMSHIPSCHE_Code(buyingTypeController.getCmnMemShipScheDtoforRecive().getMEMSHIPSCHE_Code());
			cmnMemScheDto.setMEM_Code(buyingTypeController.getUserCode());
			cmnMemScheDto.setMEMSCHE_Code(cmnMemScheDto.getMEM_Code()+cmnMemScheDto.getMEMSHIPSCHE_Code()+maxCodeNum);
			MemScheResult = cmnMemScheDao.IstMem(cmnMemScheDto);
		}else {
			//프로그램스케쥴 DB 입력
			CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
			CmnPrmScheDTO cmnPrmScheDto = buyingTypeController.getCmnPrmScheDto();
			
			PrmcheReuslt = cmnPrmScheDao.UptPrmSche(cmnPrmScheDto);
			//PAyDB에 ScheCode입력
			PayDTO.setPRMSCHE_Code(cmnPrmScheDto.getPRMSCHE_Code());
			//for MemSche+TB;
			cmnMemScheDto.setMEMSCHE_Code((buyingTypeController.getUserCode()+cmnPrmScheDto.getPRMSCHE_Code()+maxCodeNum));
			cmnMemScheDto.setPRMSCHE_Code(cmnPrmScheDto.getPRMSCHE_Code());
			cmnMemScheDto.setMEM_Code(buyingTypeController.getUserCode());
			MemScheResult = cmnMemScheDao.IstPro(cmnMemScheDto);
		}
		
		//입력 결과
		PayResult = payDao.Istpay(PayDTO);
		
		if(PayResult == 1 && MemScheResult == 1 && (PrmcheReuslt  == 1 || MemShipScheReuslt == 1)) {
			if(buyingTypeController.getCmnMemShipScheDtoforRecive() != null) { 
				//회원권	
				CommonService.WindowClose(MyForm);
				CommonService.WindowClose(buyingTypeController.getHealthBForm());
				CommonService.Msg("결제완료");
			}else {
				CommonService.WindowClose(MyForm);
				CommonService.WindowClose(buyingTypeController.getBuyingTypeForm());
				CommonService.Msg("결제완료");
			}
		}else {
			CommonService.Msg("결제 이상 발생");
			return;
		}
		
		//welcomePage 리로드
		this.welcomereload();
		
		
		
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
	
	//리
	private void welcomereload() {
		Parent memberWelcomeForm = buyingTypeController.getMemWelcomeForm();
		TableView<MEM_WelcomeMgtTable> memProgramTable = (TableView<MEM_WelcomeMgtTable>) memberWelcomeForm
				.lookup("#memProgramTable");
		MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
		ObservableList<MEM_WelcomeMgtTable> obserList = FXCollections.observableArrayList();
		ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemScheAllProgram(buyingTypeController.getUserCode());
		int checknum = 0;
		for (MEM_WelcomeDTO m : memWelcomeDto) {
			String memCode = m.getMem_code();
			String PrmScheCode = m.getPrmsche_code();
			String memShipScheCode = m.getMemshipsche_code();
			String memScheCode = m.getMemsche_code();

			String type = null;
			String time = null;
			String trainerName = null;
			int prmsche_price = 0;
			Date prmsche_strdate = null;
			Date prmsche_enddate = null;
			if (PrmScheCode != null) {
				// 프로그램임
				// type(프로그램명)
				CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
				CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(PrmScheCode);
				String prmCode = cmnPrmScheDto.getPRM_Code();
				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(prmCode);
				type = cmnPrmDto.getPRM_Name();

				// time
				time = cmnPrmScheDto.getPRMSCHE_Time();

				// 트레이너 이름
				String trainerCode = cmnPrmScheDto.getTRAINER_Code();
				CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
				CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(trainerCode);
				trainerName = cmnTrainerDto.getTRAINER_Name();

				// 가격
				prmsche_price = cmnPrmScheDto.getPRMSCHE_Price();

				// 시작, 마무리 날짜
				prmsche_strdate = cmnPrmScheDto.getPRMSCHE_Strdate();
				prmsche_enddate = cmnPrmScheDto.getPRMSCHE_Enddate();

			} else {
				// 회원권
				// type(프로그램명)
				CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
				CmnMemShipScheDTO cmnMemShipScheDto = cmnMemShipScheDao.SltMemShipScheOne(memShipScheCode);
				String memShipCode = cmnMemShipScheDto.getMEMSHIP_Code();
				CmnMemShipDAO cmnMemShipDao = new CmnMemShipDAO();
				CmnMemShipDTO cmnMemShipDto = cmnMemShipDao.SltMemShipOne(memShipCode);
				String memShipType = cmnMemShipDto.getMEMSHIP_Type();
				type = "회원권_" + memShipType + "개월";
				// time
				time = "-";

				// 트레이너 이름
				trainerName = "KGGYM";

				// 가격
				prmsche_price = cmnMemShipDto.getMEMSHIP_Price();

				// 시작, 마무리 날짜
				prmsche_strdate = cmnMemShipScheDto.getMEMSHIPSCHE_Strdate();
				prmsche_enddate = cmnMemShipScheDto.getMEMSHIPSCHE_Enddate();

			}
			
			obserList.add(
					new MEM_WelcomeMgtTable(trainerName, type, time, prmsche_price, prmsche_strdate, prmsche_enddate));
			checknum++;
		}
		memProgramTable.setItems(obserList);
	}

	
	
	public void setName(String SltPrmScheName) {
		this.SltPrmScheName = SltPrmScheName;
		
	}
	
	
}
