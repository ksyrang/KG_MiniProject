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
	private Controller controller;
	private TrnWelcomeService WelcomeService;
	private TrnWelcomeController trnWelcomeController;
	private TrnMgtController trnMgtController;
	private TrnExpEnrollController trnExpEnrollController;
	private TrnExPMgtController trnExpMgtController;
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
		//EX : 	WelcomeService.backClose(WelcomeForm);
//		this.trnCode = controller.getUserCode();
//		this.trnCode = "0"; //테스트용 입력제한
//		TrnTrainerDTO tmpTrnDTO = new TrnTrainerDAO().SelectTrnInfo(trnCode);
//		TitleUserNameLabel.setText(tmpTrnDTO.getTRAINER_Name());
		
		CodeColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PCodeColumn"));
	    NameColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("PNameColumn"));
	    MemsColumn.setCellValueFactory(new PropertyValueFactory<TrnTbVDTO, String>("MembersColumn"));
		
//	    for(TrnExPSCHEDTO dto : new TrnExPSCHEDAO().ExPSCHESelectALLbyTrn(trnCode)) {
//	    	String mems =  Integer.toString(dto.getPRMSCHE_CURRENTP())+" / "+Integer.toString(dto.getPRMSCHE_LIMITP());
//	    	Tablelist.add(new TrnTbVDTO(dto.getPRMSCHE_CODE(),
//	    			new TrnExPDAO().SelectExP(dto.getPRM_CODE()).getPRM_Name(),
//	    			mems));
//	    }
	    
		Tablelist = FXCollections.observableArrayList(
				new TrnTbVDTO("1","2","3")	
		);

		CurrentProgramTableList.setItems(Tablelist);
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
 