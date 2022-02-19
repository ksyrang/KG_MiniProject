package mem.HelthProgramBuying;

import java.io.IOException;
import java.time.LocalDate;

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
//		System.out.println("서비스로 넘어옴");
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		Label memshipPriceTxt = (Label) MyForm.lookup("#memshipPriceTxt");
		CmnMemShipDTO tmpDto = new CmnMemShipDAO().SltMemShipOne("Memship_"+memshipComboBox.getSelectionModel().getSelectedItem());
		memshipPriceTxt.setText(Integer.toString(tmpDto.getMEMSHIP_Price()));
		
	}
	//날짜 선택 시
	public void sltDateProc(Parent MyForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		DatePicker sltDate = (DatePicker)MyForm.lookup("#SltDate");
		Label SrtDateLabel = (Label)MyForm.lookup("#SrtDateLabel");
		Label EndDateLabel = (Label)MyForm.lookup("#EndDateLabel");
		//시작날짜 표시
		SrtDateLabel.setText(sltDate.getValue().toString());
		//종료 날짜 계산
		LocalDate enddate = sltDate.getValue().plusMonths(Integer.parseInt(memshipComboBox.getSelectionModel().getSelectedItem()));
		//종료 날짜 표시
		EndDateLabel.setText(enddate.toString());
	}
	
	public void PaymentProc(Parent MyForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>) MyForm.lookup("#memshipComboBox");
		DatePicker sltDate = (DatePicker)MyForm.lookup("#SltDate");
		//회원권 스케줄 생성!
		CmnMemShipScheDAO ShceDAO = new CmnMemShipScheDAO();
		//필요 데이터 : 회원권 코드, 시작일, 종료일, 회원 코드
		CmnMemShipScheDTO ShceDTO = new CmnMemShipScheDTO();
		ShceDTO.setMEMSHIPSCHE_Code(
				"Memship_"+memshipComboBox.getSelectionModel().getSelectedItem()
				+"_"+HealthPrmBuyingController.getMembCode()+"_"+"코드번호");
		ShceDTO.setMEMSHIPSCHE_Strdate(CommonService.LocalDateCnvt(sltDate.getValue()));
		LocalDate enddate = sltDate.getValue().plusMonths(Integer.parseInt(memshipComboBox.getSelectionModel().getSelectedItem()));
		ShceDTO.setMEMSHIPSCHE_Enddate(CommonService.LocalDateCnvt(enddate));
		ShceDTO.setMEMSHIP_Code("Memship_"+memshipComboBox.getSelectionModel().getSelectedItem());
		ShceDTO.setMEM_Code(HealthPrmBuyingController.getMembCode());
		
		int result = 0;
		result = ShceDAO.IstMemShipSche(ShceDTO);
		if(result > 0)System.out.println("생성 완료");
		else System.out.println("이상 발생");
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		try {
			Parent BuyingTypeForm = loader.load();
			HealthPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			HealthPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(BuyingTypeForm);
			HealthPrmBuyingController.getMEM_BuyingTypeController().setUserCode(HealthPrmBuyingController.getMembCode());
			
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
