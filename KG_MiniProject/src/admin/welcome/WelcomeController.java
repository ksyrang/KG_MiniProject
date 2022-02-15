package admin.welcome;

import java.net.URL;
import java.util.ResourceBundle;

import admin.helthProgramMgt.HelthProgramMgtController;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class WelcomeController implements Initializable {
	
	private Parent welcomForm;
	private Parent memberMgtForm;
	private Parent trainerMgtForm;
	private Parent exProgramMgtForm;
	private Parent helthProgramMgtForm;
	private Parent salesForm;
	private Parent statisticsForm;
	private HelthProgramMgtController helthProgramController;
	
	private WelcomeService welcomSvc;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomSvc = new WelcomeService();
		welcomSvc.setWelcomeController(this);
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
	
	public Parent getHelthProgramMgtForm() {
		return helthProgramMgtForm;
	}
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	public void setTrainerMgtForm(Parent trainerMgtForm) {
		this.trainerMgtForm = trainerMgtForm;
	}
	public void setExProgramMgtForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
	}
	public void setHelthProgramMgtForm(Parent helthProgramMgtForm) {
		this.helthProgramMgtForm = helthProgramMgtForm;
		
	}
	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
	public void setStatisticsForm(Parent statisticsForm) {
		this.statisticsForm = statisticsForm;
	}

	
	
	//이작업 다해야함
	public void setHelthProgramMgtController(HelthProgramMgtController helthProgramController) {
		this.helthProgramController = helthProgramController;
	}

	public void settingHelthProgramMgt() {
		this.helthProgramController.setHelthMgtForm(this.helthProgramMgtForm);
		
	}
	
	
	
}
