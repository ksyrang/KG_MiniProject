package mem.Welcome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MEM_Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource("KG_MEM_FX_Welcome.fxml"));
		
		Parent TRN_WelcomeForm = Loader.load();
	
		primaryStage.setTitle("TRN_Page_TEST");
		primaryStage.setScene(new Scene(TRN_WelcomeForm));
		primaryStage.show();

	}
	

}

