package admin.memberMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

public class MemberMgtController implements Initializable{
	private Parent KG_ADM_FX_MemberMgt;
	private MemberMgtService memberMgtService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberMgtService = new MemberMgtService();
	}
	
	public void setMemberMgtForm(Parent KG_ADM_FX_MemberMgt) {
		this.KG_ADM_FX_MemberMgt = KG_ADM_FX_MemberMgt;
	}
	public void memberMgtCancelProc() {
		CommonService.WindowClose(KG_ADM_FX_MemberMgt);
	}

}
