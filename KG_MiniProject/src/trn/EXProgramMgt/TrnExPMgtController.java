package trn.EXProgramMgt;


import java.net.URL;
import java.util.ResourceBundle;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnTrainerDTO;

public class TrnExPMgtController implements Initializable {
	
//	  @FXML
//    private Label TitleUserNameLabel;
//    @FXML
//    private Button ExPMdyBtn;
//    @FXML
//    private Button ExPDltBtn;
//    @FXML
//    private Button Backbtn;
//    @FXML
//    private Label TrnName;
//    @FXML
//    private TextField ExPNameField;
//    @FXML
//    private DatePicker SrtDate;
//    @FXML
//    private DatePicker EndDate;
//    @FXML
//    private RadioButton AMRBtn;
//    @FXML
//    private RadioButton PMRBtn;
//    @FXML
//    private TextField LimitMemsField;
	
	
	private TrnExPMgtService trnExPMgtsvc;
	private Parent trnWelcomForm;
	private Parent trnExPMgtForm;
	private String trnCode;

	public TrnExPMgtController() {
		trnExPMgtsvc = new TrnExPMgtService();
		trnExPMgtsvc.setTrnExPMgtController(this);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void ExPDltProc() {//삭제
		trnExPMgtsvc.ExPDltProc(trnExPMgtForm, trnWelcomForm);
	}
	
	public void ExPMdyProc() {//수정
		trnExPMgtsvc.ExPMdyProc(trnExPMgtForm, trnWelcomForm);
	}
	
	public void BackProc() {
		trnExPMgtsvc.backClose(trnExPMgtForm);
	}
	
	
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	public String getTrnCode() {
		return trnCode;
	}
	
	public void setTrnExProgramMgtForm(Parent trnExPMgtForm) {
		this.trnExPMgtForm = trnExPMgtForm;
		trnExPMgtsvc.SetFxId(trnExPMgtForm);
	}
	public Parent getTrnWelcomForm() {
		return trnWelcomForm;
	}
	public void setTrnWelcomForm(Parent trnWelcomForm) {
		this.trnWelcomForm = trnWelcomForm;
	}	
	
}
