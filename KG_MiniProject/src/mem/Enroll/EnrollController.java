package mem.Enroll;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import common.CommonService;

public class EnrollController implements Initializable{
	private Parent enrollForm;
	private EnrollService enrollService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enrollService = new EnrollService();
	}
	
	public void setEnrollForm(Parent enrollForm) {
		this.enrollForm = enrollForm;
	}
	
	// 회원 가입 버튼 클릭 시 동작.
	public void enrollProc() {
		enrollService.insert(enrollForm);
	}
	
	// 취소 버튼 클릭 시 동작.
	public void enrollCancelProc() {
		CommonService.WindowClose(enrollForm);
	}
}

