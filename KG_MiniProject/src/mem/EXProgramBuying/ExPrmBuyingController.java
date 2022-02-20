package mem.EXProgramBuying;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import admin.exProgramMgt.ExProTable;
import admin.exProgramMgt.ExProgramMgtService;
import common.CommonService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mem.BuyingType.MEM_BuyingTypeController;


public class ExPrmBuyingController implements Initializable{
	private Parent exProgramBuyingForm;
	private Parent buyingTypeForm;
	private ExPrmBuyingService ExPrmBuyingSvc;
	private String selectData;
	private ExPrmBuyingTable ExPrmBuyingTable;
	private ObservableList<String> allProgram;
	private String membCode;
	private ExPrmBuyingController exPrmBuyingController;
	private Parent memWelcomeForm;
	private MEM_BuyingTypeController buyingTypeController;
	@FXML private Label TitleMemNameLabel;
	@FXML private ComboBox<String> memshipComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	@FXML private Button BackBtn;
	@FXML private Button ExPScheBuyBtn;
	@FXML private Button ExlogoutButton;
	@FXML private ListView<String> programListView;
	@FXML private TableView<ExPrmBuyingTable> exProgramTableView;
	@FXML private TableColumn<ExPrmBuyingTable, String> programName;
	@FXML private TableColumn<ExPrmBuyingTable, String> code;
	@FXML private TableColumn<ExPrmBuyingTable, String> trainerName;
	@FXML private TableColumn<ExPrmBuyingTable, Integer> limtPerson;
	@FXML private TableColumn<ExPrmBuyingTable, Integer> currentPerson;
	@FXML private TableColumn<ExPrmBuyingTable, Date> strDate;
	@FXML private TableColumn<ExPrmBuyingTable, Date> endDate;
	@FXML private TableColumn<ExPrmBuyingTable, Integer> price;
	@FXML private TableColumn<ExPrmBuyingTable, String> timeC;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//ExPrmBuyingSvc = new ExPrmBuyingService();

		//allProgram = ExPrmBuyingSvc.getAllProgram();
		//programListView.setItems(allProgram);
		/*
		ExPrmBuyingSvc = new ExPrmBuyingService();
		//리스트 창 
		ExPrmBuyingSvc.listUp(this.programListView);
		
		//테이블 창
		programName.setCellValueFactory(new PropertyValueFactory<>("programName"));
		code.setCellValueFactory(new PropertyValueFactory<>("code"));
		trainerName.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
		programName.setCellValueFactory(new PropertyValueFactory<>("programName"));
		limtPerson.setCellValueFactory(new PropertyValueFactory<>("limtPerson"));
		currentPerson.setCellValueFactory(new PropertyValueFactory<>("currentPerson"));
		strDate.setCellValueFactory(new PropertyValueFactory<>("strDate"));
		endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		timeC.setCellValueFactory(new PropertyValueFactory<>("timeC"));
		
		ExPrmBuyingSvc.tableUp(exProgramTableView);
		 */
	}
	public ExPrmBuyingController() {
		ExPrmBuyingSvc = new ExPrmBuyingService();
		ExPrmBuyingSvc.setExPrmBuyingController(this);
	}
	
	public ExPrmBuyingController getExPrmBuyingController() {
		return exPrmBuyingController;
	}
	
	public void setExPrmBuyingController(ExPrmBuyingController exPrmBuyingController) {
		this.exPrmBuyingController = exPrmBuyingController;
	}
	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}
	
	public String getMembCode() {
		return membCode;
	}
	
	public void setExProgramBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
		
	}
	
	public MEM_BuyingTypeController getMEM_BuyingTypeController() {
		return buyingTypeController;
	}
	
	public void setMEM_BuyingTypeController(MEM_BuyingTypeController buyingTypeController) {
		this.buyingTypeController = buyingTypeController;
	}

	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;	
	}
	public Parent getMemWelcomeForm() {
		return memWelcomeForm;
	}
	public void setBuyingTypeForm(Parent buyingTypeForm) {
		this.buyingTypeForm = buyingTypeForm;
	}
	
	// 예약 버튼 클릭 시
	public void paymentProc() {
	//	System.out.println("결제처리");
		ExPrmBuyingSvc.paymentProc(buyingTypeForm, membCode);
	}
	
	// 이전 버튼 클릭 시
	public void cancleProc() {
		//CommonService.WindowClose(exProgramBuyingForm);
		ExPrmBuyingSvc.cancelProc(exProgramBuyingForm);
	}

	public void logoutProc() {
		ExPrmBuyingSvc.logoutProc(exProgramBuyingForm);
	//	CommonService.WindowClose(exProgramBuyingForm);

	}
	
}
