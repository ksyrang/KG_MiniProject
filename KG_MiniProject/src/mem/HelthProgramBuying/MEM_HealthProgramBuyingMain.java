package mem.HelthProgramBuying;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MEM_HealthProgramBuyingMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = 
				new FXMLLoader(getClass().getResource("KG_MEM_FX_HealthProgramBuying.fxml"));
		System.out.println("Check 00");
		Parent HPBuyingForm = loader.load();
		
//		MEM_HealthProgramBuyingController cont = new MEM_HealthProgramBuyingController();
//		cont.setHPBuyingController(loader.getController());
//		
//		MEM_HealthProgramBuyingController HPBuyingController = cont.getHPBuyingController();
//		HPBuyingController.setHPBuyingForm(HPBuyingForm);
//		
		primaryStage.setTitle("MEM_FX_HealthProgramBuying");
		primaryStage.setScene(new Scene(HPBuyingForm));
		primaryStage.show();

	}
}
