package mem.Mgt;

import java.net.URL;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;



public class MgtController implements Initializable{

	private Parent memberMgtForm;
	private MgtService mgtService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mgtService = new MgtService();
	}
	

	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	
	// 아이디중복 체크 클릭 시
		public void idConfirmProcc() {
			mgtService.idConfirmProc(memberMgtForm);
		}
	
	//	전화번호 중복 체크 클릭 시
		public void mobileConfirmProcc() {
			mgtService.mobileConfirmProc(memberMgtForm);
		}
		
//	 회원 가입 버튼 클릭 시 동작.
	public void enrollProcc() {
		mgtService.insert(memberMgtForm);
		CommonService.WindowClose(memberMgtForm);
		
	}
	
	// 취소 버튼 클릭 시 동작.
	public void enrollCancelProcc() {
		CommonService.WindowClose(memberMgtForm);
	}
	

}

