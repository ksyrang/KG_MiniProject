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
		
		CmnPayDAO payDao = new CmnPayDAO();	
		int maxCodeNum = payDao.PayMaxCodeNum() + 1;
		
		Date strPayDate = CommonService.CnvtsqlDate(new java.util.Date());
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String payDate = transFormat.format(strPayDate);

		PayDTO.setPAY_Code(buyingTypeController.getUserCode()+ payDate + maxCodeNum);
		PayDTO.setPAYCode_Num(maxCodeNum);
		PayDTO.setPAY_Type(PayType);
		PayDTO.setPAY_Date(CommonService.CnvtsqlDate(new java.util.Date()));
		
		System.out.println("buyingTypeController.getUserCode() : "+ buyingTypeController.getUserCode());
		PayDTO.setMEM_Code(buyingTypeController.getUserCode());
		
		
		//MEM_CODE		
		//MEMSCHE_CODE
		//MEMSHIPSCHE_CODE
		//넣어주기
		
		
//		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheCode(buyingTypeController.getMEMSHIPSCHE_Code());
		
		CmnMemScheDAO cmnMemScheDao = new CmnMemScheDAO();
		CmnMemScheDTO cmnMemScheDto = new CmnMemScheDTO();
//		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
//		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheName(buyingTypeController.getPrmScheName());
	
		if(buyingTypeController.getCmnMemShipScheDtoforRecive() != null) { 
			//회원권
			CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
			int iresult = 0;
			iresult = cmnMemShipScheDao.IstMemShipSche(buyingTypeController.getCmnMemShipScheDtoforRecive());
			if(iresult > 0) {
				System.out.println("생성 완료");
			}else System.out.println("이상 발생");
			
			System.out.println(buyingTypeController.getCmnMemShipScheDtoforRecive().getMEMSHIPSCHE_Code()+"///////////");
			PayDTO.setMEMSHIPSCHE_Code(buyingTypeController.getCmnMemShipScheDtoforRecive().getMEMSHIPSCHE_Code());
			cmnMemScheDto.setMEMSHIPSCHE_Code(buyingTypeController.getCmnMemShipScheDtoforRecive().getMEMSHIPSCHE_Code());
			cmnMemScheDto.setMEM_Code(buyingTypeController.getUserCode());
			cmnMemScheDto.setMEMSCHE_Code(cmnMemScheDto.getMEM_Code()+cmnMemScheDto.getMEMSHIPSCHE_Code()+maxCodeNum);
			cmnMemScheDao.IstMem(cmnMemScheDto);
		}else {
			//프로그램
			
			CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
			CmnPrmScheDTO cmnPrmScheDto = buyingTypeController.getCmnPrmScheDto();
//			int iresult = 0;
////			iresult = cmnPrmScheDao.IstPrmSche(cmnPrmScheDto);
//			if(iresult > 0) {
//				System.out.println("생성 완료");
//			}else System.out.println("이상 발생");
			String prmScheCode = cmnPrmScheDto.getPRMSCHE_Code();
			//for PAY_TB
			PayDTO.setPRMSCHE_Code(prmScheCode);
			//for MemSche+TB;
			cmnMemScheDto.setMEMSCHE_Code((buyingTypeController.getUserCode()
											+cmnPrmScheDto.getPRMSCHE_Code()+maxCodeNum));
			cmnMemScheDto.setPRMSCHE_Code(prmScheCode);
			cmnMemScheDto.setMEM_Code(buyingTypeController.getUserCode());
			cmnMemScheDao.IstPro(cmnMemScheDto);
		}
		
		//입력 결과
		result = payDao.Istpay(PayDTO);

		if(result == 1) {
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
//			System.out.println(memCode+": memCode");
//			System.out.println(PrmScheCode+": PrmScheCode");
//			System.out.println(memShipScheCode+": memShipScheCode");
//			System.out.println(memScheCode+": memScheCode");

			String type = null;
			String time = null;
			String trainerName = null;
			int prmsche_price = 0;
			Date prmsche_strdate = null;
			Date prmsche_enddate = null;
			if (PrmScheCode != null) {
				// 프로그램임
System.out.println(checknum + " CHECK1 PrmScheCode : "+ PrmScheCode);
				// type(프로그램명)
				CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
				CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(PrmScheCode);
				String prmCode = cmnPrmScheDto.getPRM_Code();
System.out.println(checknum + " CHECK1 prmCode : "+ prmCode);
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
System.out.println(checknum + " CHECK2 memShipScheCode : "+ memShipScheCode);
				// 회원권
				// type(프로그램명)
				CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
				CmnMemShipScheDTO cmnMemShipScheDto = cmnMemShipScheDao.SltMemShipScheOne(memShipScheCode);
				String memShipCode = cmnMemShipScheDto.getMEMSHIP_Code();
				CmnMemShipDAO cmnMemShipDao = new CmnMemShipDAO();
				CmnMemShipDTO cmnMemShipDto = cmnMemShipDao.SltMemShipOne(memShipCode);
				String memShipType = cmnMemShipDto.getMEMSHIP_Type();
System.out.println(checknum + " CHECK2 memShipCode : "+ memShipCode);
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
			
			obserList.add(new MEM_WelcomeMgtTable(trainerName, type, time, prmsche_price, prmsche_strdate,
					prmsche_enddate));
checknum++;
		}
		memProgramTable.setItems(obserList);
	}

	
	
	public void setName(String SltPrmScheName) {
		this.SltPrmScheName = SltPrmScheName;
		
	}
	
	
}
