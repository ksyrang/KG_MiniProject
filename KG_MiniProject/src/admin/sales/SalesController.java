package admin.sales;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesController implements Initializable{
	private Parent salesForm;
	private SalesService salesSvc;
	private ObservableList<String> allProgram;
	private String selectFilter;
	
	@FXML private TableView<SalesTable> salesTableView;
	@FXML private TableColumn<SalesTable, String> colMemNumber;
	@FXML private TableColumn<SalesTable, String> colProgramName;
	@FXML private TableColumn<SalesTable, String> colProgramType;
	@FXML private TableColumn<SalesTable, Integer> colPrice;
	@FXML private TableColumn<SalesTable, Date> colDate;
	@FXML private TableColumn<SalesTable, String> colSalesType;
	@FXML private TableColumn<SalesTable, String> colTrainerName;
	
	@FXML private ComboBox<String> filterCombo;
	@FXML private ComboBox<String> detailCombo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		salesSvc = new SalesService();
		//테이블창
		colMemNumber.setCellValueFactory(new PropertyValueFactory<>("colMemNumber"));
		colProgramName.setCellValueFactory(new PropertyValueFactory<>("colProgramName"));
		colProgramType.setCellValueFactory(new PropertyValueFactory<>("colProgramType"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("colDate"));
		colSalesType.setCellValueFactory(new PropertyValueFactory<>("colSalesType"));
		colTrainerName.setCellValueFactory(new PropertyValueFactory<>("colTrainerName"));
		
		salesSvc.tableUp(salesTableView);
		
		
		//콤보박스
		filterCombo.getItems().add("전체 매출");
		filterCombo.getItems().add("헬스 회원권 전체 매출");
		filterCombo.getItems().add("EX 프로그램 전체 매출");
		filterCombo.getItems().add("EX 프로그램 종류별 매출");
		filterCombo.getItems().add("강사별 매출");
		
	}

	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
	
	
	//필터 선택 시
	public void filterComboProc() {
		detailCombo.getItems().clear();
		selectFilter = filterCombo.getValue();
		if(selectFilter.equals("전체 매출")) {
			salesSvc.tableUp(salesTableView);
		}else if(selectFilter.equals("헬스 회원권 전체 매출")) {
			salesSvc.memSalesTableUp(salesTableView);
		}else if(selectFilter.equals("EX 프로그램 전체 매출")) {
			salesSvc.exProgramSalesTableUp(salesTableView);
		}else if(selectFilter.equals("EX 프로그램 종류별 매출")) {
			salesSvc.detailComboSetting("EXProgram",detailCombo);
		}else if(selectFilter.equals("강사별 매출")) {
			salesSvc.detailComboSetting("Trainer",detailCombo);
		}

	}
	
	//세부항목 선택 시
	public void detailComboProc() {
		String selectDetail = detailCombo.getValue();
		if(selectFilter.equals("EX 프로그램 종류별 매출")) {
			salesSvc.exProgramTypeSalesTableUp(salesTableView, selectDetail);
		}else if(selectFilter.equals("강사별 매출")) {
			salesSvc.trainerTypeTableUp(salesTableView, selectDetail);
		}

		
	}

		
		// 이전 버튼 클릭 시
	public void cancelButtonProc() {
		CommonService.WindowClose(salesForm);
		}
	
}
