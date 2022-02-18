package mem.Welcome;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import Main.main.MainService;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mem.Mgt.MgtController;
import trn.TrnMgt.TrnMgtController;

public class MEM_WelcomeController implements Initializable {
	private Parent healthProgramBuyingForm;
	private Parent exProgramBuyingForm;
	private MEM_WelcomeService memWelcomeSvc;
	private MEM_WelcomeController memWelcomeController;
	private MgtController memMgtController;
	private Parent memWelcomeForm;
	private String membCode;
	//test
	
	
	
	@FXML private TextField prm_nameTxtFld;
	@FXML private TextField trainer_nameTxtFld;
	@FXML private TextField trainer_careerTxtFld;
	@FXML private TextField prmsche_strdateTxtFld;
	@FXML private TextField prmsche_enddateTxtFld;
	@FXML private TextField prmsche_timeTxtFld;
	
	@FXML private TableView<MEM_WelcomeMgtTable> memProgramTable;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colPrmName;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colPrmscheTime;
	@FXML private TableColumn<MEM_WelcomeMgtTable, Integer> colPrmschePrice;
	@FXML private TableColumn<MEM_WelcomeMgtTable, Date> colPrmscheStrdate;
	@FXML private TableColumn<MEM_WelcomeMgtTable, Date> colPrmscheEnddate;
	
	ObservableList<MEM_WelcomeMgtTable> obserList;
	
	public MEM_WelcomeController() {
		memWelcomeSvc = new MEM_WelcomeService();
		memWelcomeSvc.setMEM_WelcomeController(this);
	}
	
	public MEM_WelcomeController getMem_WelcomeController() {
		return memWelcomeController;
	}
	
	public void setMem_WelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}
	////
	public String getMembCode() {
		return this.membCode;
	}
	
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	/////
	public MgtController getMgtController() {
		return memMgtController;
	}
	public void setMgtController(MgtController memMgtController) {
		this.memMgtController = memMgtController;
	}
	
	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;	
	}
	public Parent getMemWelcomeForm() {
		return memWelcomeForm;
	}
	
	public void MemClickProc() {
		System.out.println(membCode);
		memWelcomeSvc.memMgtOpen(membCode);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
		obserList = FXCollections.observableArrayList();
		
		colPrmName.setCellValueFactory(new PropertyValueFactory<>("colPrmName"));
		colPrmscheTime.setCellValueFactory(new PropertyValueFactory<>("colPrmscheTime"));
		colPrmschePrice.setCellValueFactory(new PropertyValueFactory<>("colPrmschePrice"));
		colPrmscheStrdate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheStrdate"));
		colPrmscheEnddate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheEnddate"));
				
		ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemAllProgram("user1");
		
		for(MEM_WelcomeDTO m : memWelcomeDto) {
			String prm_name = m.getPrm_name();
			String prmsche_time = m.getPrmsche_time();
			int prmsche_price = m.getPrmsche_price();
			Date prmsche_strdate = m.getPrmsche_strdate();
			Date prmsche_enddate = m.getPrmsche_enddate();
			obserList.add(new MEM_WelcomeMgtTable(prm_name,prmsche_time,prmsche_price,prmsche_strdate,prmsche_enddate));
		}
		memProgramTable.setItems(obserList);
	} 
    
    //회색 버튼 의 핸들러 부분 여기를 누르면 테이블뷰의 리스트가 출력됩니다.
    @FXML
    void sel_bt_view(ActionEvent event) {
	}

	//테이블 뷰에서 각 항목을 선택했을때 출력창에 표시 해주는 부분
    @FXML void selectPrmDetail(MouseEvent event) {
    	MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
    	MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();
    	int select = memProgramTable.getSelectionModel().getSelectedIndex(); 
    	memWelcomeDto = memWelcomeDao.selectProgram("user1");

    	prm_nameTxtFld.setText(memWelcomeDto.getPrm_code());
    	
    	CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
    	CmnTrainerDTO cmnTrainerDto = new CmnTrainerDTO();
    	cmnTrainerDto = cmnTrainerDao.SltTrnOne(memWelcomeDto.getTrainer_code());
    	System.out.println(cmnTrainerDto.getTRAINER_Name());
    	System.out.println(cmnTrainerDto.getTRAINER_Career());
    	
    	trainer_nameTxtFld.setText(cmnTrainerDto.getTRAINER_Name());
    	trainer_careerTxtFld.setText(Integer.toString(cmnTrainerDto.getTRAINER_Career()));
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String dateToStrdate = dateFormat.format(memWelcomeDto.getPrmsche_strdate());
		String dateToEnddate = dateFormat.format(memWelcomeDto.getPrmsche_enddate());
    	prmsche_strdateTxtFld.setText(dateToStrdate);
    	prmsche_enddateTxtFld.setText(dateToEnddate);
    	prmsche_timeTxtFld.setText(memWelcomeDto.getPrmsche_time());
    }
		
	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc();
	}
	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingProc(membCode);
	}
	public void logoutProc() {
		memWelcomeSvc.logoutProc(memWelcomeForm);
		CommonService.WindowClose(memWelcomeForm);

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

	public void setMemExPBuyingContorller(Object controller) {
		// TODO Auto-generated method stub
		
	}

	public Object getMemExPBuyingContorller() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMemExpBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
	}
}
