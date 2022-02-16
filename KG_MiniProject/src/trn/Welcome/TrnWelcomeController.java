package trn.Welcome;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Main.main.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import trn.DBDAO.TrnExPDAO;
import trn.DBDAO.TrnExPSCHEDAO;
import trn.DBDAO.TrnTrainerDAO;
import trn.DBDTO.TrnExPSCHEDTO;
import trn.DBDTO.TrnTrainerDTO;
import trn.EXProgramMgt.TrnExPMgtController;
import trn.ExprogramEnroll.TrnExpEnrollController;
import trn.TrnMgt.TrnMgtController;


public class TrnWelcomeController implements Initializable {
	
	@FXML private Label TitleUserNameLabel;
    @FXML private TableView<TrnTbVDTO> CurrentProgramTableList;
    @FXML private TableColumn<TrnTbVDTO, String> CodeColumn;
    @FXML private TableColumn<TrnTbVDTO, String> NameColumn;
    @FXML private TableColumn<TrnTbVDTO, String> MemsColumn;
    @FXML private ListView<TrnTbVDTO> Programinfo;
    @FXML private Button ExPEnrollbtn;
    @FXML private Button ExPMgtbtn;
    @FXML private Button Backbtn;
    
    private ObservableList<TrnTbVDTO> Tablelist;
	private TrnWelcomeService WelcomeSvc;
	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExPMgtController trnExpMgtController;
	private Parent WelcomeForm;
	private String trnCode;
	
	public TrnWelcomeController() {
		WelcomeSvc = new TrnWelcomeService();
		WelcomeSvc.setTrnWelcomeController(this);
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
	
//
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//이니셜라이즈는 인스턴스의 최초의 행동이기 때문에 해당괄호안에서는 어떤 데이터든 null만이 있는것
		//인스턴스 이후의 데이터는 계속 메소드의 매개변수로 넣어줘야 한다
		//EX : 	WelcomeSvc.backClose(WelcomeForm);
		CodeColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PCodeColumn"));
	    NameColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PNameColumn"));
	    MemsColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("MembersColumn"));
//	    WelcomeSvc.InitTable(CurrentProgramTableList);
	}
	
	public void TrnClickProc() {
		WelcomeSvc.TrnMgtOpen(trnCode);
	}
	//프로그램이 클릭될때
	public void programclickPro() {
		WelcomeSvc.programclickProc(WelcomeForm);
	}
	
	public void ExPEnrollProc() {
		WelcomeSvc.ExPEnrollOpen(trnCode);
	}
	
	public void ExPMgtProc() {
		WelcomeSvc.ExPMgtOpen(trnCode);
	}
	
	public void BackProc() {
		WelcomeSvc.ShutDown(WelcomeForm);
		
	}
	public void LogOutProc(){
		System.out.println("logout");
		WelcomeSvc.LogOut();
//		WelcomeSvc.ShutDown(WelcomeForm);
		
	}

	public TableView<TrnTbVDTO> getCurrentProgramTableList() {
		return CurrentProgramTableList;
	}

}
 