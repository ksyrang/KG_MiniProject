package trn.ExprogramEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import common.CmnTrainerDAO;
import common.CommonService;
import common.LogOut;
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
    @FXML
    private TextField ExPNameFeild;
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
    @FXML
    private TextField ExPPriceField;
    
	
	private TrnExPEnrollService trnExPEnrollSvc;
	private Parent trnExpEnrollForm;
	private String trnCode;
	private Parent WlcForm;

	//Logout용
	private LogOut logOut;
	
	public TrnExpEnrollController() {
		trnExPEnrollSvc = new TrnExPEnrollService();
		trnExPEnrollSvc.setTrnExpEnrollController(this);
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		ExPNameFeild.textProperty().addListener((attribute,before, after) -> {
			ExPNameFeild.setText(CommonService.getLengthLimit(250, ExPNameFeild.getText()));
	      });
		ExPPriceField.textProperty().addListener((attribute,before, after) -> {
			ExPPriceField.setText(CommonService.getLengthLimit(8, ExPPriceField.getText()));
	      });
//		ExPPriceField.textProperty().addListener((attribute,before, after) -> {
//			String tmp = String.format("%,d", Integer.parseInt(CommonService.getLengthLimit(9, ExPPriceField.getText())));
//			ExPPriceField.setText(CommonService.getLengthLimit(9, tmp));
//	      });
		
		
	}
	public void CheckSrtDate() {
		trnExPEnrollSvc.CheckSrtDate();
	}
	public void CheckEndDate() {
		trnExPEnrollSvc.CheckEndDate();
	}
	public void ExPErllProc(){
		trnExPEnrollSvc.ExPErllProc(trnExpEnrollForm);
		
	}
	public void BackProc() {
		trnExPEnrollSvc.BackProc(trnExpEnrollForm);
	}
	public void LogOutProc(){
		trnExPEnrollSvc.BackProc(trnExpEnrollForm);
		CommonService.WindowClose(WlcForm);
		logOut.LogOut();
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
	public LogOut getLogOut() {
		return logOut;
	}
	public void setLogOut(LogOut logOut) {
		this.logOut = logOut;
	}
	public Parent getWlcForm() {
		return WlcForm;
	}
	public void setWlcForm(Parent wlcForm) {
		WlcForm = wlcForm;
	}
	
}
