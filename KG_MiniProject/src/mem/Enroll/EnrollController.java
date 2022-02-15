package mem.Enroll;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.paint.Color;



public class EnrollController implements Initializable{
	private Parent KG_MEM_FX_Enroll;
	private EnrollService enrollService;
	
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
	
//	public final void setOnInputMethodTextChanged1(EventHandler<? super InputMethodEvent> value) {
//		EnrollDTO enrollDTOC = new EnrollDTO();
//		EnrollService enrollService = new EnrollService();
//		Label colorLabel1 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel1");
//		if(enrollDTOC.getMobile().equals(enrollService)) {
//			colorLabel1.setTextFill(Color.RED);
//		}
//	}
		
	
//	public final void setOnInputMethodTextChanged2(EventHandler<? super InputMethodEvent> value) {
//		EnrollDTO enrollDTOC = new EnrollDTO();
//		EnrollService enrollService = new EnrollService();
//		Label colorLabel2 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel2");
//		if(enrollDTOC.getMobile().equals(enrollService)) {
//			colorLabel2.setTextFill(Color.RED);
//		}
//	}
}

