package trn.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import Main.main.Controller;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import trn.EXProgramMgt.TrnExProgramMgtController;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.TrnMgt.TrnMgtController;


public class TrnWelcomeController implements Initializable {
	
	
	private TrnWelcomeService WelcomeService;
	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExProgramMgtController trnExpMgtController;
	private Parent WelcomeForm;
	private String trnCode;
	
	public TrnWelcomeController() {
		WelcomeService = new TrnWelcomeService();
		WelcomeService.setTrnWelcomeController(this);
	}
	
	public String getTrnCode() {
		return this.trnCode;
	}

	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
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
//
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//이니셜라이즈는 인스턴스의 최초의 행동이기 때문에 해당괄호안에서는 어떤 데이터든 null만이 있는것
		//인스턴스 이후의 데이터는 계속 메소드의 매개변수로 넣어줘야 한다
		//EX : 	WelcomeService.backClose(WelcomeForm);
	}
	
	public void TrnClickProc() {
		WelcomeService.TrnMgtOpen();
	}
	//프로그램이 플릭될때
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
 