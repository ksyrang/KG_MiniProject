package mem.EXProgramBuying;

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
import mem.Mgt.MgtController;
import mem.Mgt.MgtService;


public class ExPrmBuyingController implements Initializable{
	private Parent exProgramBuyingForm;
	private ExPrmBuyingService ExPrmBuyingSvc;
	private String selectData;
	private ExPrmBuyingTable ExPrmBuyingTable;
	private ObservableList<String> allProgram;
	private String membCode;
	private ExPrmBuyingController exPrmBuyingController;
	
	@FXML private ComboBox<String> memshipComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML private ListView<String> programListView;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ExPrmBuyingSvc = new ExPrmBuyingService();

		allProgram = ExPrmBuyingSvc.getAllProgram();
		memshipComboBox.setItems(allProgram);
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
	
	public void setExPrmBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
	}

	
	// 예약 버튼 클릭 시
	public void paymentProc() {
	//	System.out.println("결제처리");
		ExPrmBuyingSvc.paymentProc(exProgramBuyingForm);
	}
	
	// 이전 버튼 클릭 시
	public void ExPrmBuyingCancleProc() {
		CommonService.WindowClose(exProgramBuyingForm);
	}


}
