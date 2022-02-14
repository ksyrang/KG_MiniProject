package mem.Enroll;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


public class EnrollController implements Initializable{
	private Parent KG_MEM_FX_Enroll;
	private EnrollService enrollService;
	@FXML private Button btnConfirm;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enrollService = new EnrollService();
	}
	
	public void setEnrollForm(Parent KG_MEM_FX_Enroll) {
		this.KG_MEM_FX_Enroll = KG_MEM_FX_Enroll;
	}
	
	// 아이디 중복확인 클릭 시 동작.
	public void enrollConfirmProc() {
		CommonService.Msg("사용 가능한 ID입니다.");
	}
	
	// 회원 가입 버튼 클릭 시 동작.
	public void enrollProc() {
		enrollService.insert(KG_MEM_FX_Enroll);
	}
	
	// 취소 버튼 클릭 시 동작.
	public void enrollCancelProc() {
		CommonService.WindowClose(KG_MEM_FX_Enroll);
	}
}

