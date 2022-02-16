package admin.trainerMgt;

import java.net.URL;
import java.util.ResourceBundle;

import admin.trainerEnroll.TrainerEnrollController;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrainerMgtController implements Initializable{
	private Parent trainerMgtForm;
	private TrainerMgtService trainerMgtSvc;
	// 강사 등록
	private Parent trainerEnrollForm;
	private TrainerEnrollController trainerEnrollController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerMgtSvc = new TrainerMgtService();
		trainerMgtSvc.setTrainerMgtController(this);
	}
	
	public void setTrainerMgtForm(Parent trainerMgtForm) {
		this.trainerMgtForm = trainerMgtForm;
	}
	
	//  set 강사 등록 폼
	public void setTrainerEnrollForm(Parent trainerEnrollForm) {
		this.trainerEnrollForm = trainerEnrollForm;
	}
	
	// get 강사 등록 폼
	public Parent getTrainerEnrollForm() {
		return trainerEnrollForm;
	}
	
	// set 강사 등록 컨트롤러
	public void setTrainerEnrollController(TrainerEnrollController trainerEnrollController) {
		this.trainerEnrollController = trainerEnrollController;
	}
	
	public void settingTrainerEnroll() {
		this.trainerEnrollController.setTrainerEnrollForm(this.trainerEnrollForm);
	}
	
	// 강사 등록 버튼 클릭 시
	public void trnInsertProc() {
		trainerMgtSvc.trnInsertProc();
	}
	
	// 강사 수정 버튼 클릭 시
	public void trnUpdateProc() {
		
	}
	
	// 강사 삭제 버튼 클릭 시
	public void trnDeleteProc() {
		
	}
	
	// 이전 버튼 클릭 시
	public void trnCloseProc() {
		CommonService.WindowClose(trainerMgtForm);
	}

}
