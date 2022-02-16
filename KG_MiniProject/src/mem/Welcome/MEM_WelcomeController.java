package mem.Welcome;

import java.net.URL;
import java.util.ResourceBundle;

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

public class MEM_WelcomeController implements Initializable {
	private Parent memWelcomeForm;
	private Parent healthProgramBuyingForm;
	private Parent exProgramBuyingForm;
	private MEM_WelcomeService memWelcomeSvc;
	
	@FXML private TextField programcodeTextField;
	@FXML private TextField trainernameTextField;
	@FXML private TextField trainercareerTextField;
	@FXML private TextField prmsche_strdateTextField;
	@FXML private TextField prmsche_enddateTextField;
	@FXML private TextField prmschetimeTextField;
	
	@FXML private TableView<MEM_WelcomeMgtTable> memProgramTable;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colCode;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colPrmsche_code;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colMemshipsche_code;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colID;
	@FXML private TableColumn<MEM_WelcomeMgtTable, String> colName;
	
	ObservableList<MEM_WelcomeMgtTable> obserList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memWelcomeSvc = new MEM_WelcomeService();
		memWelcomeSvc.setWelcomeController(this);
		
		MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
		
		obserList = FXCollections.observableArrayList();
		
		colCode.setCellValueFactory(new PropertyValueFactory<>("colCode"));
		colPrmsche_code.setCellValueFactory(new PropertyValueFactory<>("colPrmsche_code"));
		colMemshipsche_code.setCellValueFactory(new PropertyValueFactory<>("colMemshipsche_code"));
		colID.setCellValueFactory(new PropertyValueFactory<>("colID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
		
		ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemAllProgram("admin");
		for(MEM_WelcomeDTO m : memWelcomeDto) {
			obserList.add(new MEM_WelcomeMgtTable(m.getMem_code(), m.getPrmsche_code(), m.getMemshipsche_code(),m.getMem_id(),m.getMem_name()));
		}
		memProgramTable.setItems(obserList);
		
	} 
    
    //회색 버튼 의 핸들러 부분 여기를 누르면 테이블뷰의 리스트가 출력됩니다.
    @FXML
    void sel_bt_view(ActionEvent event) {
	}

	//테이블 뷰에서 각 항목을 선택했을때 출력창에 표시 해주는 부분
	@FXML void selecttableView1(MouseEvent event) {

	}
		
	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;
	}
	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc();
	}
	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingProc();
	}
	public void logoutProc() {
		memWelcomeSvc.logoutProc();
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
	
}
