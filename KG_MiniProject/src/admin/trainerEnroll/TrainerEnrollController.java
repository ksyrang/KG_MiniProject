package admin.trainerEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import admin.trainerMgt.TrainerMgtController;
import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

public class TrainerEnrollController implements Initializable{
	
	private Parent TrainerMgtForm;
	private Parent trainerEnrollForm;
	private TrainerEnrollService trainerEnrollSvc;
	
	@FXML private TextField trnIdTxt, trnNameTxt, trnBirthTxt, trnMobileTxt, trnAddrTxt1, trnAddrTxt2, trnCareerTxt;
	@FXML private PasswordField trnPwTxt, trnPwComfrimTxt;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerEnrollSvc = new TrainerEnrollService();
		//mgtSvc = new TrainerMgtService();
		trainerEnrollSvc.setTrainerEnrollController(this);
		
		// 길이 제한
		trnIdTxt.textProperty().addListener((attribute,before, after) -> {
			trnIdTxt.setText(CommonService.getLengthLimit(20, trnIdTxt.getText()));
		});
		trnPwTxt.textProperty().addListener((attribute,before, after) -> {
			trnPwTxt.setText(CommonService.getLengthLimit(20, trnPwTxt.getText()));
		});
		trnPwComfrimTxt.textProperty().addListener((attribute,before, after) -> {
			trnPwComfrimTxt.setText(CommonService.getLengthLimit(20, trnPwComfrimTxt.getText()));
		});
		trnNameTxt.textProperty().addListener((attribute,before, after) -> {
			trnNameTxt.setText(CommonService.getLengthLimit(5, trnNameTxt.getText()));
		});
		trnBirthTxt.textProperty().addListener((attribute,before, after) -> {
			trnBirthTxt.setText(CommonService.getLengthLimit(8, trnBirthTxt.getText()));
		});
		trnMobileTxt.textProperty().addListener((attribute,before, after) -> {
			trnMobileTxt.setText(CommonService.getLengthLimit(11, trnMobileTxt.getText()));
		});
		trnAddrTxt1.textProperty().addListener((attribute,before, after) -> {
			trnAddrTxt1.setText(CommonService.getLengthLimit(200, trnAddrTxt1.getText()));
		});
		trnAddrTxt2.textProperty().addListener((attribute,before, after) -> {
			trnAddrTxt2.setText(CommonService.getLengthLimit(100, trnAddrTxt2.getText()));
		});
		trnCareerTxt.textProperty().addListener((attribute,before, after) -> {
			trnCareerTxt.setText(CommonService.getLengthLimit(2, trnCareerTxt.getText()));
		});
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
