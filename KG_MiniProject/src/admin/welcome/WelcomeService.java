package admin.welcome;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeService {
	private WelcomeController welcomeController;
	
	public void memberMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/memberMgt/KG_ADM_FX_MemberMgt.fxml"));
		Parent memberMgtForm;
		try {
			memberMgtForm = loader.load();
			
			welcomeController.setmemberMgtForm(memberMgtForm);
			
			Scene scene = new Scene(memberMgtForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberMgtForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void trainerMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerMgt/KG_ADM_FX_TrainerMgt.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			WelcomeController.setmemberMgtForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exProgramMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerMgt/KG_ADM_FX_EXProgramMgt.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			WelcomeController.setmemberMgtForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void helthProgramMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerMgt/KG_ADM_FX_HelthProgramMgt.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			WelcomeController.setmemberMgtForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void salesProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/welcome/KG_ADM_FX_Sales.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			WelcomeController.setmemberMgtForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void statisticsProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/welcome/KG_ADM_FX_Statistics.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			WelcomeController.setmemberMgtForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
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
