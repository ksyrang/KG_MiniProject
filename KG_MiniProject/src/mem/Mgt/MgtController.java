package mem.Mgt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import mem.Delete.DeleteController;


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
	private MgtController mgtController;
	private Parent deleteForm;
	private DeleteController deleteController;
	private String formerMobileNum;
	private Parent memWelcomeForm;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public MgtController() {
		mgtService = new MgtService();
		mgtService.setMgtController(this);
	}
	
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	
	public MgtController getMgtController() {
		return mgtController;
	}
	public void setMgtController(MgtController mgtController) {
		this.mgtController = mgtController;
	}
	public void setMemberMgtForm(Parent memMgtForm) {
		this.memMgtForm = memMgtForm;
	}
	
	public void setDeleteForm(Parent deleteForm) {
		this.deleteForm = deleteForm;
	}
	
	public DeleteController getDeleteController() {
		return deleteController;
	}
	
	public void setDeleteController(DeleteController deleteController) {
		this.deleteController = deleteController;
	}
	
	
//	public void settingDelete() {
//		this.deleteController.setDeleteForm(this.deleteForm);
//	}
	
	// ???????????? ?????? ???
	public void MemModifyProc() {
		mgtService.MemModifyProc(memMgtForm, membCode);
	}
	// ???????????? ?????? ???
	public void MemDeleteProc() {
		mgtService.MemDeleteOpen(memMgtForm, membCode);
	//???????????? ?????? ?????? ?????? ???	
	}
	public void MBCheckProc() {
		mgtService.MBCheckProc(memMgtForm, formerMobileNum);
	}
	
	//???????????? ?????? ???	
	public void BackMgtProc() {
		mgtService.BackMgtProc(memMgtForm);
	}

	public String getFormerMobileNum() {
		return formerMobileNum;
	}

	public void setFormerMobileNum(String formerMobileNum) {
		this.formerMobileNum = formerMobileNum;
	}

	public Parent getMemWelcomeForm() {
		return memWelcomeForm;
	}

	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;
	}
	
	
	


}

