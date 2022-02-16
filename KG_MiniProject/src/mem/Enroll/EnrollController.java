package mem.Enroll;

import java.net.URL;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;



public class EnrollController implements Initializable{

	private Parent memberJoinForm;
	private EnrollService enrollService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enrollService = new EnrollService();
	}
	

	
	public void setEnrollForm(Parent memberJoinForm) {
		this.memberJoinForm = memberJoinForm;
	}
	
	// 아이디중복 체크 클릭 시
		public void idConfirmProc() {
			enrollService.idConfirmProc(memberJoinForm);
		}
	
	//	전화번호 중복 체크 클릭 시
		public void mobileConfirmProc() {
			enrollService.mobileConfirmProc(memberJoinForm);
		}
		
//	 회원 가입 버튼 클릭 시 동작.
	public void enrollProc() {
		enrollService.insert(memberJoinForm);
		CommonService.WindowClose(memberJoinForm);
		
	}
	
	// 취소 버튼 클릭 시 동작.
	public void enrollCancelProc() {
		CommonService.WindowClose(memberJoinForm);
	}
	

}

