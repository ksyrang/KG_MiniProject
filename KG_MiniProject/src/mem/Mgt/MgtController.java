package mem.Mgt;

import java.net.URL;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


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
	
	private Parent memMgtForm;
	private MgtService mgtService;
	private String memCode;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mgtService = new MgtService();
	}
	
	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memMgtForm = memberMgtForm;
	}
	
	// 아이디중복 체크 클릭 시
	public void TnrModifyProc() {
		mgtService.MemModifyProc(memMgtForm, memCode);
	}
		
	public void BackMgtProc() {
		mgtService.BackMgtProc(memMgtForm);
	}
		




	
	

}

