package mem.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MEM_WelcomeController implements Initializable {
	
	private Parent memWelcomeForm;
	private Parent healthProgramBuyingForm;
	private Parent exProgramBuyingForm;
	
	private MEM_WelcomeService memWelcomeSvc;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memWelcomeSvc = new MEM_WelcomeService();
		memWelcomeSvc.setWelcomeController(this);
	}

	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;
	}
	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc();
	}
	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingProc();
	}
	public void logoutProc() {
		memWelcomeSvc.logoutProc();
	}
	public void cancelProc() {
		memWelcomeSvc.cancelProc();
	}

	public void setHealthProgramBuyingForm(Parent healthProgramBuyingForm) {
		this.healthProgramBuyingForm = healthProgramBuyingForm;
	}
	
	public void setExProgramBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
	}
	
}
