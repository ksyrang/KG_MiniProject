package admin.trainerEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import admin.trainerMgt.TrainerMgtController;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrainerEnrollController implements Initializable{
	
//	private TrainerEnrollController TrainerEnrollController;
	private Parent TrainerMgtForm;
	private Parent trainerEnrollForm;
	private TrainerEnrollService trainerEnrollSvc;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerEnrollSvc = new TrainerEnrollService();
		//mgtSvc = new TrainerMgtService();
		trainerEnrollSvc.setTrainerEnrollController(this);
	}
	
	public void setTrainerEnrollForm(Parent trainerEnrollForm) {
		this.trainerEnrollForm = trainerEnrollForm;
	}
	
	public Parent getTrainerMgtForm() {
		return TrainerMgtForm;
	}

	public void setTrainerMgtForm(Parent trainerMgtForm) {
		TrainerMgtForm = trainerMgtForm;
	}

	// 중복 체크 클릭 시
	public void overlapProc() {
		trainerEnrollSvc.overlapProc(trainerEnrollForm);
	}
	// 강사 등록 클릭 시
	public void trnEnrollProc() {
		trainerEnrollSvc.trnEnrollProc(trainerEnrollForm);
	}
	
	// 이전 버튼 클릭 시
	public void trnEnrollCloseProc() {
		CommonService.WindowClose(trainerEnrollForm);
	}
	

}
