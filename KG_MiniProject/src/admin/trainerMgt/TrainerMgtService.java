package admin.trainerMgt;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrainerMgtService {
	private TrainerMgtController trainerMgtController;
	
	public void setTrainerMgtController(TrainerMgtController trainerMgtController) {
		this.trainerMgtController = trainerMgtController;
	}
	
	// 강사 등록
	public void trnInsertProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerEnroll/KG_ADM_FX_TrainerEnroll.fxml"));
		Parent trainerEnrollForm;
		try {
			trainerEnrollForm = loader.load();
			trainerMgtController.setTrainerEnrollController(loader.getController());
			trainerMgtController.setTrainerEnrollForm(trainerEnrollForm);
			trainerMgtController.settingTrainerEnroll();
			
			Scene scene = new Scene(trainerEnrollForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("trainerEnrollForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 강사 수정
	public void trnUpdateProc(Parent trainerMgtForm) {
		
	}
	
	// 강사 삭제
	public void trnDeleteProc(Parent trainerMgtForm) {
		
	}

}
