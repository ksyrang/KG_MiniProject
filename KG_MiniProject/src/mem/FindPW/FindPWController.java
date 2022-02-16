package mem.FindPW;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class FindPWController implements Initializable{
	
	private Parent memberFindPWForm;
	private FindPWService findPWService;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		findPWService = new FindPWService();
	}

	public void setFindPWForm(Parent memberFindPWForm) {
		this.memberFindPWForm = memberFindPWForm;
	}
	
	public void findPWProc() {
		findPWService.FindPW(memberFindPWForm);
		CommonService.WindowClose(memberFindPWForm);
		
	}
	
	public void findPWCancelProc() {
		CommonService.WindowClose(memberFindPWForm);
	}

}
