package trn.TrnMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import common.LogOut;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import trn.DBDTO.TrnTrainerDTO;

public class TrnMgtController implements Initializable {
	
	@FXML private Label TitleUserNameLabel;
    @FXML private HBox TrnIDDIs;
    @FXML private TextField TrnIDField;
    @FXML private TextField TrnNameField;
    @FXML private PasswordField TrnPWField;
    @FXML private PasswordField TrnPWCField;
    @FXML private TextField TrnBirthField;
    @FXML private RadioButton MaleRbtn;
    @FXML private RadioButton FeMaleRbtn;
    @FXML private TextField TrnMobileField;
    @FXML private TextField TrnAddr1;
    @FXML private TextField TrnAddr2;
    @FXML private TextField TrnCareer;
    @FXML private Button TnrMgtbtn;
    @FXML private Button Backbtn;
	    
	//Logout용
	private LogOut logOut;
    
	private TrnMgtService TrnMgtSvc;
	private String trnCode;
	private Parent trnMgtForm;
	private Parent WlcForm;


	public TrnMgtController() {
		TrnMgtSvc = new TrnMgtService();
		TrnMgtSvc.setTrnMgtController(this);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TrnPWField.textProperty().addListener((attribute,before, after) -> {
			TrnPWField.setText(CommonService.getLengthLimit(20, TrnPWField.getText()));
	      });
		TrnNameField.textProperty().addListener((attribute,before, after) -> {
			TrnNameField.setText(CommonService.getLengthLimit(5, TrnNameField.getText()));
	      });
		TrnPWCField.textProperty().addListener((attribute,before, after) -> {
			TrnPWCField.setText(CommonService.getLengthLimit(20, TrnPWCField.getText()));
	      });
		TrnCareer.textProperty().addListener((attribute,before, after) -> {
			TrnCareer.setText(CommonService.getLengthLimit(2, TrnCareer.getText()));
	      });
		TrnMobileField.textProperty().addListener((attribute,before, after) -> {
			TrnMobileField.setText(CommonService.getLengthLimit(11, TrnMobileField.getText()));
	      });
		TrnBirthField.textProperty().addListener((attribute,before, after) -> {
			TrnBirthField.setText(CommonService.getLengthLimit(8, TrnBirthField.getText()));
	      });
		TrnAddr1.textProperty().addListener((attribute,before, after) -> {
			TrnAddr1.setText(CommonService.getLengthLimit(200, TrnAddr1.getText()));
	      });
		TrnAddr2.textProperty().addListener((attribute,before, after) -> {
			TrnAddr2.setText(CommonService.getLengthLimit(100, TrnAddr2.getText()));
	      });
	}
	public void TnrModifyProc() {
		TrnMgtSvc.TnrModifyProc(trnMgtForm, trnCode);		
	}

	
	public void BackProc() {
		TrnMgtSvc.BackProc(trnMgtForm);
	}
	public void LogOutProc(){
		TrnMgtSvc.BackProc(trnMgtForm);
		CommonService.WindowClose(WlcForm);
		logOut.LogOut();
	}
	
	public void setTrnMgtForm(Parent trnMgtForm) {
		this.trnMgtForm = trnMgtForm;
	}
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
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
