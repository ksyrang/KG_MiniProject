package trn.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

import Main.login.LoginController;
import Main.main.Controller;
import common.LogOut;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import trn.EXProgramMgt.TrnExPMgtController;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.TrnMgt.TrnMgtController;


public class TrnWelcomeController implements Initializable {
	
	//Top Sector
	@FXML private Label TitleUserNameLabel;
	//Mid Sector
	//Mid Sector TableViewPart
    @FXML private TableView<TrnTbVDTO> CurrentProgramTableList;
    @FXML private TableColumn<TrnTbVDTO, String> CodeColumn;
    @FXML private TableColumn<TrnTbVDTO, String> NameColumn;
    @FXML private TableColumn<TrnTbVDTO, String> MemsColumn;
//    @FXML private ListView<TrnTbVDTO> Programinfo; // NotUse
    
    //Mid Sector Detail InfoPart
    @FXML private Label ExPCodeDisLabel;
    @FXML private Label ExPTypeDisLabel;
    @FXML private Label ExPNameDisLabel;
    @FXML private DatePicker ExpSrtDateDisPicker;
    @FXML private DatePicker ExpEndDateDisPicker;
    @FXML private Label ExPTimeDisLabel;
    @FXML private Label ExPCrtMemsDisLabel;
    @FXML private Label ExPLmtMemsDisLabel;
    
    //Bottom Sector
    @FXML private Button ExPEnrollbtn;
    @FXML private Button ExPMgtbtn;
    @FXML private Button Backbtn;
    
    
    //종속시키는 컨트롤러
	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExPMgtController trnExpMgtController;
	
	//Logout용
	private LogOut logOut;
	
	//내데이터
	private TrnWelcomeService WelcomeSvc;
	private Parent WelcomeForm;
	private String trnCode;
	
	
//	//윈도우 오픈용
//	private LoginController loginController;
	
	public TrnWelcomeController() {
		WelcomeSvc = new TrnWelcomeService();
		WelcomeSvc.setTrnWelcomeController(this);
	}
	
//
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//이니셜라이즈는 인스턴스의 최초의 행동이기 때문에 해당괄호안에서는 어떤 데이터든 null만이 있는것
		//인스턴스 이후의 데이터는 계속 메소드의 매개변수로 넣어줘야 한다
		//EX : 	WelcomeSvc.backClose(WelcomeForm);
		CodeColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PCodeColumn"));
	    NameColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PNameColumn"));
	    MemsColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("MembersColumn"));
//	    ExpSrtDateDisPicker.setEditable(false);//설정 금지
//	    ExpSrtDateDisPicker.setEditable(false);//설정 금지
	    
//	    WelcomeSvc.InitTable(CurrentProgramTableList);
	}
	
	public void TrnClickProc() {
		WelcomeSvc.TrnMgtOpen();
	}
	//프로그램이 클릭될때
	public void programclickPro() {
		WelcomeSvc.programclickProc(WelcomeForm);
	}
	
	public void ExPEnrollProc() {
		WelcomeSvc.ExPEnrollOpen(WelcomeForm);
	}
	
	public void ExPMgtProc() {
		WelcomeSvc.ExPMgtOpen(WelcomeForm);
	}
	
	public void BackProc() {
		WelcomeSvc.ShutDown(WelcomeForm);
		
	}
	public void LogOutProc(){
		WelcomeSvc.ShutDown(WelcomeForm);
		logOut.LogOut();
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
	public TrnExPMgtController getTrnExpMgtController() {
		return trnExpMgtController;
	}
	public void setTrnExpMgtController(TrnExPMgtController trnExpMgtController) {
		this.trnExpMgtController = trnExpMgtController;
	}

	public void setTrnWelcomeForm(Parent welcomeForm) {
		this.WelcomeForm = welcomeForm;	
	}
	public Parent getTrnWelcomeForm() {
		return WelcomeForm;
	}
	public TableView<TrnTbVDTO> getCurrentProgramTableList() {
		return CurrentProgramTableList;
	}
	public LogOut getLogOut() {
		return logOut;
	}
	public void setLogOut(LogOut logOut) {
		this.logOut = logOut;
	}
	
	

}
 