package admin.welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class WelcomeController implements Initializable {
	
	private Parent welcomForm;
	private Parent memberMgtForm;
	private WelcomeService welcomSvc;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomSvc = new WelcomeService();
	}

	public void setWelcomeForm(Parent welcomForm) {
		this.welcomForm = welcomForm;
	}
	
	public void memberMgtProc() {
		welcomSvc.memberMgtProc();
	}
	
	public void trainerMgtProc() {
		welcomSvc.trainerMgtProc();
	}
	
	public void exProgramMgtProc() {
		welcomSvc.exProgramMgtProc();
	}
	
	public void helthProgramMgtProc() {
		welcomSvc.helthProgramMgtProc();
	}
	
	public void salesProc() {
		welcomSvc.salesProc();
	}
	
	public void statisticsProc() {
		welcomSvc.statisticsProc();
	}
	
	public void logoutProc() {
		welcomSvc.logoutProc();
	}
	
	public void cancelProc() {
		welcomSvc.cancelProc();
	}

	public void setmemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}

	
	
	
}
