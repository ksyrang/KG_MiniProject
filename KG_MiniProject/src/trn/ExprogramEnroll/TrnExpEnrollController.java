package trn.ExprogramEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import common.CmnTrainerDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TrnExpEnrollController implements Initializable {
	
//	@FXML
//    private Label TitleUserNameLabel;
//
//    @FXML
//    private Button ExPErllBtn;
//
//    @FXML
//    private Button Backbtn;
//
//    @FXML
//    private ComboBox<?> ExPTypeBox;
//
//    @FXML
//    private TextField ExPNameFeild;
//
//    @FXML
//    private RadioButton PMRBtn;
//
//    @FXML
//    private DatePicker SrtDate;
//
//    @FXML
//    private DatePicker EndDate;
//
//    @FXML
//    private RadioButton AMRBtn;
//
//    @FXML
//    private TextField LimitMemField;

	
	private TrnExPEnrollService trnExPEnrollSvc;
	private Parent trnExpEnrollForm;
	private String trnCode;
	
	public TrnExpEnrollController() {
		trnExPEnrollSvc = new TrnExPEnrollService();
		trnExPEnrollSvc.setTrnExpEnrollController(this);
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	
	}
	public void CheckSrtDate() {
		trnExPEnrollSvc.CheckSrtDate();
	}
	
	public void ExPErllProc(){
		trnExPEnrollSvc.ExPErllProc(trnExpEnrollForm);
		
	}
	
	public void BackProc() {
		trnExPEnrollSvc.BackProc(trnExpEnrollForm);
	}
	
	
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	
	public String getTrnCode() {
		return trnCode;
	}
	public void setTrnExpEnrollForm(Parent trnExpEnrollForm) {
		this.trnExpEnrollForm = trnExpEnrollForm;
		trnExPEnrollSvc.SetFxId(trnExpEnrollForm);
	}
}
