package admin.welcome;

import java.io.IOException;

import admin.memberMgt.MemberMgtTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class WelcomeService {
	private WelcomeController welcomeController;
	private TableView<MemberMgtTable> memberMgtTabel;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}
	
	public void memberMgtProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/memberMgt/KG_ADM_FX_MemberMgt.fxml"));
		Parent memberMgtForm;
		try {
			memberMgtForm = loader.load();
			welcomeController.setMemberMgtController(loader.getController());
			welcomeController.setMemberMgtForm(memberMgtForm);
			welcomeController.settingMemberMgt();
			
			// 콤보 박스
			ComboBox<String> filterCombo = (ComboBox<String>)memberMgtForm.lookup("#filterCombo");
			if(filterCombo != null) {
				filterCombo.getItems().addAll("전체보기", "승인여부");
			}
			
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
			welcomeController.setTrainerMgtController(loader.getController());
			welcomeController.setTrainerMgtForm(trainerMgtForm);
			welcomeController.settingTrainerMgt();
			
			// 콤보 박스
			ComboBox<String> kindCombo = (ComboBox<String>)exProgramMgtForm.lookup("#filterCombo");
			if(kindCombo != null) {
				kindCombo.getItems().addAll("필라테스", "요가", "스피닝", "경찰시험 대비반");
			}
			
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
			welcomeController.setExProgramMgtController(loader.getController());
			welcomeController.setExProgramMgtForm(exProgramMgtForm);
			welcomeController.settingExProgramMgt();
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
			welcomeController.setHelthProgramMgtController(loader.getController());
			welcomeController.setHelthProgramMgtForm(helthProgramMgtForm);
			welcomeController.settingHelthProgramMgt();
			
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
			welcomeController.setSalesController(loader.getController());
			welcomeController.setSalesForm(salesForm);
			welcomeController.settingSales();
			
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
			welcomeController.setStatisticsController(loader.getController());
			welcomeController.setStatisticsForm(statisticsForm);
			welcomeController.settingStatistics();
			
			Scene scene = new Scene(statisticsForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("statisticsForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
