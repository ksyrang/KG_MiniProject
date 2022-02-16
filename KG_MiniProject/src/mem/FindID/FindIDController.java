package mem.FindID;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class FindIDController implements Initializable{
	
	private Parent memberFindIDForm;
	private FindIDService findIDService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		findIDService = new FindIDService();
	}

	public void setMemberFindIDForm(Parent memberFindIDForm) {
		this.memberFindIDForm = memberFindIDForm;
	}
	
	public void findIDProc() {
		findIDService.FindID(memberFindIDForm);
		CommonService.WindowClose(memberFindIDForm);
		
	}
	
	public void findIDCancelProc() {
		CommonService.WindowClose(memberFindIDForm);
	}
	
}
