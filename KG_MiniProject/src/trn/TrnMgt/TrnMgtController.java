package trn.TrnMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
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
	    
	private TrnMgtService TrnMgtSvc;
	private String trnCode;
	private Parent trnMgtForm;
	
	public TrnMgtController() {
		TrnMgtSvc = new TrnMgtService();
		TrnMgtSvc.setTrnMgtController(this);
	}
	
	public void setTrnMgtForm(Parent trnMgtForm) {
		this.trnMgtForm = trnMgtForm;
	}
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void TnrModifyProc() {
		TrnMgtSvc.TnrModifyProc(trnMgtForm, trnCode);		
	}

	
	public void BackProc() {
		TrnMgtSvc.BackProc(trnMgtForm);
	}
	
	
	
}
