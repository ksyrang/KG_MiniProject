package trn.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import Main.main.Controller;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class TrnWelcomeController implements Initializable {
	
	private WelcomeService WelcomeService;
	private Parent WelcomeForm;
	
	
	public void setWelcomeForm(Parent welcomeForm) {
		this.WelcomeForm = welcomeForm;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WelcomeService = new WelcomeService();
//		WelcomeService.setWelcomeForm(WelcomeForm);
		
	}
	
	public void programclickPro() {
		WelcomeService.programclickProc(WelcomeForm);
	}
	
	public void ExPEnrollProc() {
		WelcomeService.ExPEnrollOpen();
	}
	
	public void ExPMgtProc() {
		WelcomeService.ExPMgtOpen();
	}
	
	public void BackProc() {
		WelcomeService.backClose(WelcomeForm);
	}

}
 