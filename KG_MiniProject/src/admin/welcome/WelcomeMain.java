package admin.welcome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeMain extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource());
		Parent welcomeForm = loader.load();
		
		Controller
		
		WelcomeController welcomeController = 
		
		Scene scene = new Scene(welcomeForm);
		primaryStage.setTitle("welcomeForm");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
