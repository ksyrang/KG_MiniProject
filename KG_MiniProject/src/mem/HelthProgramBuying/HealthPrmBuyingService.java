package mem.HelthProgramBuying;

import java.io.IOException;
import java.time.LocalDate;

import common.CmnMemScheDAO;
import common.CmnMemScheDTO;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CommonService;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HealthPrmBuyingService {
	private HealthPrmBuyingController HealthPrmBuyingController;
	private ObservableList<String> allProgram;
	private CmnMemShipDAO memshipDao;

	// 콤보박스 헬스 회원권 항목
	public ObservableList<String> getAllProgram() {
		memshipDao = new CmnMemShipDAO();
		this.allProgram = memshipDao.GETMemshipType();
		return this.allProgram;
	}

	// 회원권 선택시 가격 변동
	public void selectTypeCombo(Parent MyForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		Label memshipPriceTxt = (Label) MyForm.lookup("#memshipPriceTxt");
		CmnMemShipDTO memshipDto = memshipDao.SltMemShipAll(memshipComboBox.getValue());
		String price = Integer.toString(memshipDto.getMEMSHIP_Price());
		memshipPriceTxt.setText(price);
	}
	//날짜 선택 시
	public void sltDateProc(Parent MyForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		DatePicker sltDate = (DatePicker)MyForm.lookup("#SltDate");
		Label SrtDateLabel = (Label)MyForm.lookup("#SrtDateLabel");
		Label EndDateLabel = (Label)MyForm.lookup("#EndDateLabel");

		if(sltDate.getValue() == null || CommonService.CompareDate(LocalDate.now(),sltDate.getValue())) {
			CommonService.Msg("현재 일짜 이후로 입력해주십시오.");
			sltDate.getEditor().clear();
			return;
		}
		else {
			//시작날짜 표시
			SrtDateLabel.setText(sltDate.getValue().toString());
			//종료 날짜 계산
			LocalDate enddate = sltDate.getValue().plusMonths(Integer.parseInt(memshipComboBox.getSelectionModel().getSelectedItem()));
			//종료 날짜 표시
			EndDateLabel.setText(enddate.toString());	
		}
		
	}
	
	// 결제 버튼
	public void PaymentProc(Parent MyForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		Label memshipPriceTxt = (Label) MyForm.lookup("#memshipPriceTxt");
		CmnMemShipDTO memshipDto = memshipDao.SltMemShipAll(memshipComboBox.getValue());
		if(memshipComboBox.getValue() == "0") {
			CommonService.Msg("회원권을 선택하세요.");
			return;
		}
		
		DatePicker sltDate = (DatePicker)MyForm.lookup("#SltDate");
		if(sltDate.getValue() == null || CommonService.CompareDate(LocalDate.now(),sltDate.getValue())) {
			CommonService.Msg("현재 일짜 이후로 입력해주십시오.");
			sltDate.getEditor().clear();
			return;
		}
		//회원권 스케줄 생성!
		CmnMemShipScheDAO ShceDAO = new CmnMemShipScheDAO();
		
		//필요 데이터 : 회원권 코드, 코드 넘버, 시작일, 종료일, 회원 코드
		CmnMemShipScheDTO ShceDTO = new CmnMemShipScheDTO();
		
		//코드 번호 개설
		//DB에서 번호의 최대 값을 가지고 와서 +1 해줘서 넣어줘야 함
		int InputCodeNum = ShceDAO.MemShipScheMaxCodeNum()+1;
//		CmnMemShipDTO memshipDto = memshipDao.SltMemShipAll(memshipComboBox.getValue());
//		memshipDto = memshipDao.SltMemShipAll(memshipComboBox.getValue());
		ShceDTO.setMEMSHIPSCHE_Code(
				memshipDto.getMEMSHIP_Code()
				+"_"+HealthPrmBuyingController.getMembCode()+"_"+ InputCodeNum);
//				+"_"+HealthPrmBuyingController.getMembCode()+"_"+"코드번호1");//DB 수정 후 해당 위치의 코드번호용 알고리즘 코딩 필요
		String memShipScheCode = memshipDto.getMEMSHIP_Code()
				+"_"+HealthPrmBuyingController.getMembCode()+"_"+ InputCodeNum;
		
		ShceDTO.setMEMSHIPSCHECode_Num(InputCodeNum);
		ShceDTO.setMEMSHIPSCHE_Strdate(CommonService.LocalDateCnvt(sltDate.getValue()));
		LocalDate enddate = sltDate.getValue().plusMonths(Integer.parseInt(memshipComboBox.getSelectionModel().getSelectedItem()));
		ShceDTO.setMEMSHIPSCHE_Enddate(CommonService.LocalDateCnvt(enddate));
		ShceDTO.setMEMSHIP_Code(memshipDto.getMEMSHIP_Code());
		ShceDTO.setMEM_Code(HealthPrmBuyingController.getMembCode());
//		int result = 0;
//		result = ShceDAO.IstMemShipSche(ShceDTO);
//		System.out.println(ShceDTO.getMEMSHIPSCHE_Code());
//		System.out.println(ShceDTO.getMEM_Code());
//		if(result > 0) {
//			System.out.println("생성 완료");
//			CommonService.WindowClose(MyForm);
//		}else System.out.println("이상 발생");
//		//회원권 스케줄 생성 End
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		try {
			Parent BuyingTypeForm = loader.load();
			HealthPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			HealthPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(BuyingTypeForm);
			HealthPrmBuyingController.getMEM_BuyingTypeController().setUserCode(HealthPrmBuyingController.getMembCode());
			HealthPrmBuyingController.getMEM_BuyingTypeController().setMemWelcomeForm(HealthPrmBuyingController.getMemWelcomeForm());
			HealthPrmBuyingController.getMEM_BuyingTypeController().setCmnMemShipScheDtoforRecive(ShceDTO);
			HealthPrmBuyingController.getMEM_BuyingTypeController().setHealthBForm(MyForm);
			
			Text ScheNameLabel = (Text)BuyingTypeForm.lookup("#ScheNameLabel");
			Text SchePriceLabel = (Text)BuyingTypeForm.lookup("#SchePriceLabel");
			Text PayDateLabel = (Text)BuyingTypeForm.lookup("#PayDateLabel");
			
			//결제 회원권 이름
			CmnMemShipDTO MemshipDTO = new CmnMemShipDAO().SltMemShipOne(ShceDTO.getMEMSHIP_Code());		
			ScheNameLabel.setText("KGGYM 헬스장 "+MemshipDTO.getMEMSHIP_Type()+" 개월 이용권");
			//결제 금액	
			SchePriceLabel.setText(Integer.toString(MemshipDTO.getMEMSHIP_Price())+" 원");
			//결제 일 표시
			PayDateLabel.setText(CommonService.getNowDatetoString());
			
			Scene scene = new Scene(BuyingTypeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("BuyingType");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setHealthPrmBuyingController(HealthPrmBuyingController healthPrmBuyingController) {
		this.HealthPrmBuyingController = healthPrmBuyingController;
	}




}
