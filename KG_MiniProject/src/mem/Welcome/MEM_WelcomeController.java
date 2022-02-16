package mem.Welcome;

import java.net.URL;
import java.util.Date;
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
	
	@FXML private TextField prm_codeTxtFld;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memWelcomeSvc = new MEM_WelcomeService();
		memWelcomeSvc.setWelcomeController(this);
		
		MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
		obserList = FXCollections.observableArrayList();
		
		colPrmName.setCellValueFactory(new PropertyValueFactory<>("colPrmName"));
		colPrmscheTime.setCellValueFactory(new PropertyValueFactory<>("colPrmscheTime"));
		colPrmschePrice.setCellValueFactory(new PropertyValueFactory<>("colPrmschePrice"));
		colPrmscheStrdate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheStrdate"));
		colPrmscheEnddate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheEnddate"));
		
		ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemAllProgram("user1");
		
		for(MEM_WelcomeDTO m : memWelcomeDto) {
//			obserList.add(new MEM_WelcomeMgtTable(
//					m.getPrm_name(), m.getPrmsche_time(), m.getPrmsche_price(),
//					m.getPrmsche_strdate(),m.getPrmsche_enddate()));
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
    	// 선택했을 때 인덱스 값을 가지고 옴
    	//.getSelectedIndex() 메소드를 사용
    	int select = memProgramTable.getSelectionModel().getSelectedIndex(); 

    	//선택한 행의 데이터를 테이블 데이터 로 보낸다.
    	//.getSelectedItem() 메소드를 사용
    	MEM_WelcomeMgtTable tvd = memProgramTable.getSelectionModel().getSelectedItem();
//
//    	prm_codeTxtFld.setText(tvd.getColPrmName());
//    	trainer_nameTxtFld.setText(tvd.getColPrmscheTime());
//    	trainer_careerTxtFld.setText(tvd.getColPrmscheTime());
//    	prmsche_strdateTxtFld.setText(tvd.getColPrmscheTime());
//    	prmsche_enddateTxtFld.setText(tvd.getColPrmscheTime());
//    	prmsche_timeTxtFld.setText(tvd.getColPrmscheTime());
    	prm_codeTxtFld.setText(tvd.getColPrmName());
    	trainer_nameTxtFld.setText(tvd.getColPrmscheTime());
    	trainer_careerTxtFld.setText(toString(tvd.getColPrmschePrice()));
    	prmsche_strdateTxtFld.setText(toString(tvd.getColPrmscheStrdate()));
    	prmsche_enddateTxtFld.setText(toString(tvd.getColPrmscheEnddate()));
    	prmsche_timeTxtFld.setText(tvd.getColPrmscheTime());
    	

    }
		
	private String toString(Date colPrmscheStrdate2) {
		// TODO Auto-generated method stub
		return null;
	}

	private String toString(int colPrmschePrice2) {
		// TODO Auto-generated method stub
		return null;
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
