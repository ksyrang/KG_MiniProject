package Main.main;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//새로운 창 오픈을 위한 메인 서비스 클래스
public class MainService {
	private Controller controller;

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void adminWelcomeOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/welcome/KG_ADM_FX_Welcome.fxml"));
		Parent adminWelcomeForm;
		try {
			adminWelcomeForm = loader.load();
			controller.setAdminWelcomeForm(adminWelcomeForm);
			
			Scene scene = new Scene(adminWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("adminWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void memberWelcomeOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Welcome/KG_MEM_FX_Welcome.fxml"));
		Parent memberWelcomeForm;
		try {
			memberWelcomeForm = loader.load();
			controller.setmemberWelcomeForm(memberWelcomeForm);
			
			Scene scene = new Scene(memberWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void trainerWelcomeOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/Welcome/KG_TRN_FX_Welcome.fxml"));
		Parent trainerWelcomeForm;
		try {
			trainerWelcomeForm = loader.load();
			controller.setTrinerWelcomeForm(trainerWelcomeForm);
			
			Scene scene = new Scene(trainerWelcomeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("trainerWelcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void memberJoinOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Enroll/KG_MEM_FX_Enroll.fxml"));
		Parent memberJoinForm;
		try {
			memberJoinForm = loader.load();
			controller.setmemberJoinForm(memberJoinForm);
			
			Scene scene = new Scene(memberJoinForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberJoin");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}