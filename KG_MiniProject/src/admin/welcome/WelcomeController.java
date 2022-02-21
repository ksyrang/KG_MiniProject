package admin.welcome;

import java.net.URL;
import java.util.ResourceBundle;

import admin.exProgramMgt.ExProgramMgtController;
import admin.helthProgramMgt.HelthProgramMgtController;
import admin.memberMgt.MemberMgtController;
import admin.sales.SalesController;
import admin.statistics.StatisticsController;
import admin.trainerMgt.TrainerMgtController;
import common.CommonService;
import common.LogOut;
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
	private MemberMgtController memberMgtController;
	private TrainerMgtController trainerMgtController;
	private ExProgramMgtController exProgramMgtController;
	private SalesController salesController;
	private StatisticsController statisticsController;
	private WelcomeService welcomSvc;
	private LogOut logout;
	
	
	
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
		//common으로 동작
		CommonService.WindowClose(welcomForm);
		logout.LogOut();
	}
	
	// setForm
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

	// getForm
	public Parent getHelthProgramMgtForm() {
		return helthProgramMgtForm;
	}
	
	public Parent getMemberMgtForm() {
		return memberMgtForm;
	}
	
	public Parent getTrainerMgtForm() {
		return trainerMgtForm;
	}
	
	public Parent getExProgramMgtForm() {
		return exProgramMgtForm;
	}
	
	public Parent getSalesForm() {
		return salesForm;
	}
	
	public Parent getStatisticsForm() {
		return statisticsForm;
	}
	
	
	// setController
	public void setHelthProgramMgtController(HelthProgramMgtController helthProgramController) {
		this.helthProgramController = helthProgramController;
	}
	
	public void setMemberMgtController(MemberMgtController memberMgtController) {
		this.memberMgtController = memberMgtController;
	}
	
	public void setTrainerMgtController(TrainerMgtController trainerMgtController) {
		this.trainerMgtController = trainerMgtController;
	}
	
	public void setExProgramMgtController(ExProgramMgtController exProgramMgtController) {
		this.exProgramMgtController = exProgramMgtController;
	}
	
	public void setSalesController(SalesController salesController) {
		this.salesController = salesController;
	}
	
	public void setStatisticsController(StatisticsController statisticsController) {
		this.statisticsController = statisticsController;
	}

	
	//setting
	public void settingHelthProgramMgt() {
		this.helthProgramController.setHelthMgtForm(this.helthProgramMgtForm);
	}
	
	public void settingMemberMgt() {
		this.memberMgtController.setMemberMgtForm(this.memberMgtForm);
	}
	
	public void settingTrainerMgt() {
		this.trainerMgtController.setTrainerMgtForm(this.trainerMgtForm);
	}
	
	public void settingExProgramMgt() {
		this.exProgramMgtController.setExProgramMgtForm(this.exProgramMgtForm);
	}
	
	public void settingSales() {
		this.salesController.setSalesForm(this.salesForm);
	}
	
	public void settingStatistics() {
		this.statisticsController.setStatisticsForm(this.statisticsForm);
	}
	
}
