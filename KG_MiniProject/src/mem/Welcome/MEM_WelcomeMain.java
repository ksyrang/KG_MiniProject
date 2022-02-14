package mem.Welcome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MEM_WelcomeMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader Loader = new FXMLLoader(getClass().getResource("KG_MEM_FX_Welcome.fxml"));
		
		Parent MEM_WelcomeForm = Loader.load();
	
		primaryStage.setTitle("MEM_WelcomeMain_TEST");
		primaryStage.setScene(new Scene(MEM_WelcomeForm));
		primaryStage.show();

	}
	

}

