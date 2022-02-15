package mem.Welcome;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MEM_WelcomeService {
	private MEM_WelcomeController memWelcomeController;
	
	public void setWelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}
	
	public void healthProgramBuyingProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/HelthProgramBuying/KG_MEM_FX_HealthProgramBuying.fxml"));
		Parent healthProgramBuyingForm;
		try {
			healthProgramBuyingForm = loader.load();
			memWelcomeController.setHealthProgramBuyingForm(healthProgramBuyingForm);
			
//			ComboBox<String> combo = (ComboBox<String>)healthProgramBuyingForm.lookup("#filterCombo");
//			if(combo != null) {
//				combo.getItems().addAll("전체보기", "승인여부");
//			}
			
			Scene scene = new Scene(healthProgramBuyingForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("MEM_HealthProgramBuyingForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exProgramBuyingProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/EXProgramBuying/KG_MEM_FX_EXProgramBuying.fxml"));
		Parent exProgramBuyingForm;
		try {
			exProgramBuyingForm = loader.load();
			memWelcomeController.setExProgramBuyingForm(exProgramBuyingForm);
			
			Scene scene = new Scene(exProgramBuyingForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("MEM_EXProgramBuyingForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logoutProc() {
		// TODO Auto-generated method stub
		
	}

	public void cancelProc() {
		// TODO Auto-generated method stub
		
	}
}
