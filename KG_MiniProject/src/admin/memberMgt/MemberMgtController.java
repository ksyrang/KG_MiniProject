package admin.memberMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MemberMgtController implements Initializable{
	private Parent memberMgtForm;
	private MemberMgtService memberMgtService;
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberMgtService = new MemberMgtService();
	}
	
	
	//이전 버튼 클릭 시
	public void memberMgtCancelProc() {
		CommonService.WindowClose(memberMgtForm);
	}

}
