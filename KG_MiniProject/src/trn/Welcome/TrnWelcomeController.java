package trn.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import trn.EXProgramMgt.TrnExProgramMgtController;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.TrnMgt.TrnMgtController;


public class TrnWelcomeController implements Initializable {
	
	private WelcomeService WelcomeService;
	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExProgramMgtController trnExpMgtController;
	private Parent WelcomeForm;
	
	public TrnWelcomeController() {
		WelcomeService = new WelcomeService();
		WelcomeService.setTrnController(this);
	}

	public TrnWelcomeController getTrnWelcomeController() {
		return trnWelcomeController;
	}
	public void setTrnWelcomeController(TrnWelcomeController trnWelcomeController) {
		this.trnWelcomeController = trnWelcomeController;
	}
	public TrnMgtController getTrnMgtController() {
		return trnMgtController;
	}
	public void setTrnMgtController(TrnMgtController trnMgtController) {
		this.trnMgtController = trnMgtController;
	}
	public TrnExpEnrollController getTrnExpEnrollController() {
		return trnExpEnrollController;
	}
	public void setTrnExpEnrollController(TrnExpEnrollController trnExpEnrollController) {
		this.trnExpEnrollController = trnExpEnrollController;
	}
	public TrnExProgramMgtController getTrnExpMgtController() {
		return trnExpMgtController;
	}
	public void setTrnExpMgtController(TrnExProgramMgtController trnExpMgtController) {
		this.trnExpMgtController = trnExpMgtController;
	}

	public void setTrnWelcomeForm(Parent welcomeForm) {
		this.WelcomeForm = welcomeForm;	
	}
	public Parent getTrnWelcomeForm() {
		return WelcomeForm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		WelcomeService.setWelcomeForm(WelcomeForm);
	}
	
	public void TrnClickProc() {
		WelcomeService.TrnMgtOpen();
	}
	
	public void programclickPro() {
//		WelcomeService.programclickProc(WelcomeForm);
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
 