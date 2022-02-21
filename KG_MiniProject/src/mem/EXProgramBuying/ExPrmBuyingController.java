package mem.EXProgramBuying;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import common.CommonService;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mem.BuyingType.MEM_BuyingTypeController;



public class ExPrmBuyingController implements Initializable{
	private Parent exProgramMgtForm;
	private ExPrmBuyingService exProgramSvc;
	private String selectData;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	private String membCode;
	private ExPrmBuyingController exPrmBuyingController;
	private MEM_BuyingTypeController buyingTypeController;
	private Parent memWelcomeForm;
	private Parent buyingTypeForm;
	@FXML private ComboBox<String> kindComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	@FXML private ListView<String> programListView;
	@FXML private TableView<ExProTable> exProgramTableView;
	@FXML private TableColumn<ExProTable, String> programName;
	@FXML private TableColumn<ExProTable, String> code;
	@FXML private TableColumn<ExProTable, String> trainerName;
	@FXML private TableColumn<ExProTable, Integer> limtPerson;
	@FXML private TableColumn<ExProTable, Integer> currentPerson;
	@FXML private TableColumn<ExProTable, Date> strDate;
	@FXML private TableColumn<ExProTable, Date> endDate;
	@FXML private TableColumn<ExProTable, Integer> price;
	@FXML private TableColumn<ExProTable, String> timeC;

	
	/*
	 * 1.ex프로그램 combobox 선택 후 수정 연동
	*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
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
		
		exProgramSvc.tableUp(exProgramTableView);
		
		
		//수정창		
		//tabelView 클릭 시
		exProgramTableView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override public void handle(MouseEvent event) { 
				codeTable = exProgramTableView.getSelectionModel().getSelectedItem();
				exProgramSvc.setCodeTable(codeTable);
				
				}
			});
		
	}
	
	public void setExProgramMgtForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
	}
	
	
	
	public ExPrmBuyingController() {
		exProgramSvc = new ExPrmBuyingService();
		exProgramSvc.setExPrmBuyingController(this);
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
	
	public void setExProgramBuyingForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
		
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
		exProgramSvc.paymentProc(buyingTypeForm, membCode);
	}
	
	// 이전 버튼 클릭 시
	public void cancleProc() {
		//CommonService.WindowClose(exProgramBuyingForm);
		exProgramSvc.cancelProc(exProgramMgtForm);
	}

	public void logoutProc() {
		exProgramSvc.logoutProc(exProgramMgtForm);
	//	CommonService.WindowClose(exProgramBuyingForm);

	}
	
}
