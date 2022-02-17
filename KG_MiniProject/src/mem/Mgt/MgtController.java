package mem.Mgt;

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
import trn.TrnMgt.TrnMgtService;


public class MgtController implements Initializable{

	@FXML private Label TitleMemNameLabel;
    @FXML private TextField MemIDField;
    @FXML private TextField MemNameField;
    @FXML private PasswordField MemPWField;
    @FXML private PasswordField MemPWCField;
    @FXML private TextField MemBirthField;
    @FXML private TextField MemMobileField;
    @FXML private RadioButton MaleRabtn;
    @FXML private RadioButton FeMaleRabtn;
    @FXML private TextField MemAddr1;
    @FXML private TextField MemAddr2;
    @FXML private Button MemMgtbtn;
    @FXML private Button MemBackbtn;
    @FXML private Button LogoutMgtbtn;
    @FXML private Button Deletebtn;
    
	private Parent memMgtForm;
	private MgtService mgtService;
	private String membCode;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public MgtController() {
		mgtService = new MgtService();	
	}
	
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memMgtForm = memberMgtForm;
	}
	
	
	// 수정중복 체크 클릭 시
	public void TnrModifyProc() {
		mgtService.MemModifyProc(memMgtForm, membCode);
	}
		
	public void BackMgtProc() {
		mgtService.BackMgtProc(memMgtForm);
	}
	
	



	
	

}

