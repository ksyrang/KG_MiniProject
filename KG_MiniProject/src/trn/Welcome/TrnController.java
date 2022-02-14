package trn.Welcome;

import Main.main.Controller;
import javafx.scene.Parent;

public class TrnController {

	private TrnWelcomeController trnWelcomeController;
	private Parent trnMgtForm;
	private Parent trnEnrollForm;
	private Parent trnWelcomeForm;
	private Parent trnExPMegForm;
//	private WelcomeService Welcomsvic;
//	
//	
//	public TrnController() {
//		Welcomsvic = new WelcomeService();
//		Welcomsvic.setController(this);
//	}
	
	public void settrnWelcomeController(Parent controller) {
		
	}
	
	public void settrnMgtForm(Parent trnMgtForm) {
		this.trnMgtForm = trnMgtForm;
	}
	public void settrnEnrollForm(Parent trnEnrollForm) {
		this.trnEnrollForm = trnEnrollForm;
	}
	public void settrnWelcomeForm(Parent trnWelcomeForm) {
		this.trnWelcomeForm = trnWelcomeForm;
	}
	public void settrnExPMegForm(Parent trnExPMegForm) {
		this.trnExPMegForm = trnExPMegForm;
	}
		
	
	

}
