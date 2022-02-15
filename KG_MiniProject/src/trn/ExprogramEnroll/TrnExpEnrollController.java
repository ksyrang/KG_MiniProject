package trn.ExprogramEnroll;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class TrnExpEnrollController implements Initializable {
	private TrnExPEnrollService trnExPEnrollService;
	private Parent trnExpEnrollForm;
	private String trnCode;
	
	public TrnExpEnrollController() {
		trnExPEnrollService = new TrnExPEnrollService();
	}
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	
	public void setTrnExpEnrollForm(Parent trnExpEnrollForm) {
		this.trnExpEnrollForm = trnExpEnrollForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void BackProc() {
		trnExPEnrollService.BackProc(trnExpEnrollForm);
	}
	
}
