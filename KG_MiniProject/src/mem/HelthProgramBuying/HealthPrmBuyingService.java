package mem.HelthProgramBuying;

import java.io.IOException;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	public void selectTypeCombo(Parent healthPrmBuyingForm) {
//		System.out.println("서비스로 넘어옴");
		ComboBox<String> memshipComboBox = (ComboBox<String>) healthPrmBuyingForm.lookup("#memshipComboBox");
		Label memshipPriceTxt = (Label) healthPrmBuyingForm.lookup("#memshipPriceTxt");
		memshipPriceTxt.setText(memshipComboBox.getSelectionModel().getSelectedItem());
//		memshipDao = new CmnMemShipDAO();
//		CmnMemShipDTO memshipDto = memshipDao.SltMemShipAll(type);
//		String price = Integer.toString(memshipDto.getMEMSHIP_Price());
//		memshipPriceTxt.setText(price);
	}
	public void PaymentProc() {
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
