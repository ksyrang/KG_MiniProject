package admin.welcome;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class WelcomeService {
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}
	
	public void memberMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/memberMgt/KG_ADM_FX_MemberMgt.fxml"));
		Parent memberMgtForm;
		try {
			memberMgtForm = loader.load();
			welcomeController.setMemberMgtForm(memberMgtForm);
			
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
		Parent trainerMgtForm;
		try {
			trainerMgtForm = loader.load();
			welcomeController.setTrainerMgtForm(trainerMgtForm);
			
			Scene scene = new Scene(trainerMgtForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exProgramMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/exProgramMgt/KG_ADM_FX_EXProgramMgt.fxml"));
		Parent exProgramMgtForm;
		try {
			exProgramMgtForm = loader.load();
			welcomeController.setExProgramMgtForm(exProgramMgtForm);
			
			Scene scene = new Scene(exProgramMgtForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void helthProgramMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/helthProgramMgt/KG_ADM_FX_HelthProgramMgt.fxml"));
		Parent helthProgramMgtForm;
		try {
			helthProgramMgtForm = loader.load();
			welcomeController.setHelthProgramMgtForm(helthProgramMgtForm);
			
			Scene scene = new Scene(helthProgramMgtForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void salesProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/sales/KG_ADM_FX_Sales.fxml"));
		Parent salesForm;
		try {
			salesForm = loader.load();
			welcomeController.setSalesForm(salesForm);
			
			Scene scene = new Scene(salesForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void statisticsProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/statistics/KG_ADM_FX_Statistics.fxml"));
		Parent statisticsForm;
		try {
			statisticsForm = loader.load();
			welcomeController.setStatisticsForm(statisticsForm);
			
			Scene scene = new Scene(statisticsForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("statisticsForm");
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
