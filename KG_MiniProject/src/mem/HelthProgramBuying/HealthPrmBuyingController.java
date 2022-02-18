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


public class HealthPrmBuyingController implements Initializable{
	private Parent healthPrmBuyingForm;
	private HealthPrmBuyingService healthPrmBuyingSvc;
	private String selectData;
	private HealthPrmBuyingTable healthPrmBuyingTable;
	private ObservableList<String> allProgram;
	
	@FXML private ComboBox<String> memshipComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML private ListView<String> programListView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		healthPrmBuyingSvc = new HealthPrmBuyingService();

		allProgram = healthPrmBuyingSvc.getAllProgram();
		memshipComboBox.setItems(allProgram);
	}
	
	public void setHealthPrmBuyingForm(Parent healthPrmBuyingForm) {
		this.healthPrmBuyingForm = healthPrmBuyingForm;
	}
	
	// 결제 버튼 클릭 시
	public void PaymentProc() {
		System.out.println("결제처리");
		healthPrmBuyingSvc.PaymentProc();
	}
	
	// 이전 버튼 클릭 시
	public void healthPrmBuyingCancleProc() {
		CommonService.WindowClose(healthPrmBuyingForm);
	}


}