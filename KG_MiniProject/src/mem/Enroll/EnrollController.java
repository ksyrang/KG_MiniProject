package mem.Enroll;

import java.net.URL;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;



public class EnrollController implements Initializable{

	@FXML
    private TextField idTxt;
    @FXML
    private Button idConfirmButton;
    @FXML
    private TextField nameTxt;
    @FXML
    private PasswordField pwTxt, confirmTxt;
    @FXML
    private TextField mobileTxt;
    @FXML
    private Button mobileConfirmButton;
    @FXML
    private TextField birthTxt;
    @FXML
    private RadioButton manRadio, womanRadio;
    @FXML
    private TextField addrTxt1, addrTxt2;
	
	
	private Parent memberJoinForm;
	private EnrollService enrollService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enrollService = new EnrollService();
		 
//		idTxt.textProperty().addListener((attribute,before, after) -> {
//			idTxt.setText(CommonService.getLengthLimit(20, idTxt.getText()));
//	      });
		nameTxt.textProperty().addListener((attribute,before, after) -> {
			nameTxt.setText(CommonService.getLengthLimit(5, nameTxt.getText()));
	      });
		pwTxt.textProperty().addListener((attribute,before, after) -> {
			pwTxt.setText(CommonService.getLengthLimit(20, pwTxt.getText()));
	      });
		confirmTxt.textProperty().addListener((attribute,before, after) -> {
			confirmTxt.setText(CommonService.getLengthLimit(20, confirmTxt.getText()));
	      });
//		mobileTxt.textProperty().addListener((attribute,before, after) -> {
//			mobileTxt.setText(CommonService.getLengthLimit(11, mobileTxt.getText()));
//	      });
		birthTxt.textProperty().addListener((attribute,before, after) -> {
			birthTxt.setText(CommonService.getLengthLimit(8, birthTxt.getText()));
	      });
		addrTxt1.textProperty().addListener((attribute,before, after) -> {
			addrTxt1.setText(CommonService.getLengthLimit(200, addrTxt1.getText()));
	      });
		addrTxt2.textProperty().addListener((attribute,before, after) -> {
			addrTxt2.setText(CommonService.getLengthLimit(100, addrTxt2.getText()));
	      });
	}
	

	
	public void setEnrollForm(Parent memberJoinForm) {
		this.memberJoinForm = memberJoinForm;
	}
	
	// ??????????????? ?????? ?????? ???
		public void idConfirmProc() {
			enrollService.idConfirmProc(memberJoinForm);
		}
	
	//	???????????? ?????? ?????? ?????? ???
		public void mobileConfirmProc() {
			enrollService.mobileConfirmProc(memberJoinForm);
		}
		
//	 ?????? ?????? ?????? ?????? ??? ??????.
	public void enrollProc() {
		enrollService.insert(memberJoinForm);
		
	}
	
	// ?????? ?????? ?????? ??? ??????.
	public void enrollCancelProc() {
		CommonService.WindowClose(memberJoinForm);
	}
	

}

