package mem.HelthProgramBuying;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import mem.BuyingType.MEM_BuyingTypeController;

public class HealthPrmBuyingController implements Initializable{
	
	private Parent healthPrmBuyingForm;
	private HealthPrmBuyingService healthPrmBuyingSvc;
	private String selectData;
	private HealthPrmBuyingTable healthPrmBuyingTable;
	private ObservableList<String> allProgram;
	private String membCode;
	private MEM_BuyingTypeController MEM_BuyingTypeController;
	private Parent memWelcomeForm;


    
    
	@FXML private ComboBox<String> memshipComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	@FXML private DatePicker SltDate;
	@FXML private ListView<String> programListView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		healthPrmBuyingSvc = new HealthPrmBuyingService();
		healthPrmBuyingSvc.setHealthPrmBuyingController(this);
		allProgram = healthPrmBuyingSvc.getAllProgram();
		memshipComboBox.setItems(allProgram);
	}
	
	public void setHealthPrmBuyingForm(Parent healthPrmBuyingForm) {
		this.healthPrmBuyingForm = healthPrmBuyingForm;
	}
	
	// 결제 버튼 클릭 시
	public void PaymentProc() {
		healthPrmBuyingSvc.PaymentProc(healthPrmBuyingForm);
	}
	//콤보박스 클릭 시
	public void selectTypeCombo() {
		healthPrmBuyingSvc.selectTypeCombo(healthPrmBuyingForm);
	}
	//날짜 선택 시
	public void sltDateProc() {
		healthPrmBuyingSvc.sltDateProc(healthPrmBuyingForm);
	}
	// 이전 버튼 클릭 시
	public void healthPrmBuyingCancleProc() {
		CommonService.WindowClose(healthPrmBuyingForm);
	}

	public String getMembCode() {
		return membCode;
	}

	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}

	public MEM_BuyingTypeController getMEM_BuyingTypeController() {
		return MEM_BuyingTypeController;
	}

	public void setMEM_BuyingTypeController(MEM_BuyingTypeController mEM_BuyingTypeController) {
		MEM_BuyingTypeController = mEM_BuyingTypeController;
	}

	public void setWelcomForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;
	}

	public Parent getMemWelcomeForm() {
		return memWelcomeForm;
	}
	
	


}
