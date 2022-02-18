package mem.BuyingType;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MEM_BuyingTypeService {
	private MEM_BuyingTypeController buyingTypeController;
	
	public void setBuyingTypeController(MEM_BuyingTypeController buyingTypeController) {
		this.buyingTypeController = buyingTypeController;
	}
	
	public void paymentProc() {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/HelthProgramBuying/KG_MEM_FX_HealthProgramBuying.fxml"));
//		Parent healthProgramBuyingForm;
//		try {
//			healthProgramBuyingForm = loader.load();
//			memWelcomeController.setHealthProgramBuyingForm(healthProgramBuyingForm);
//			
//			ComboBox<String> combo = (ComboBox<String>)healthProgramBuyingForm.lookup("#filterCombo");
//			if(combo != null) {
//				combo.getItems().addAll("전체보기", "승인여부");
//			}
//			
//			Scene scene = new Scene(healthProgramBuyingForm);
//			Stage primaryStage = new Stage();
//			primaryStage.setTitle("MEM_HealthProgramBuyingForm");
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}

