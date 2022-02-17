package admin.sales;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import common.CommonService;
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
	
	
	@FXML private TableView<SalesTable> salesTableView;
	@FXML private TableColumn<SalesTable, String> colMemNumber;
	@FXML private TableColumn<SalesTable, String> colProgramName;
	@FXML private TableColumn<SalesTable, String> colProgramType;
	@FXML private TableColumn<SalesTable, Integer> colPrice;
	@FXML private TableColumn<SalesTable, Date> colDate;
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
		
		salesSvc.tableUp(salesTableView);
		
		
		//콤보박스
		filterCombo.setValue("전체 매출");
		filterCombo.setValue("헬스 회원권 전체 매출");
		filterCombo.setValue("EX 프로그램 전체 매출");
		filterCombo.setValue("EX 프로그램 종류별 매출");
		filterCombo.setValue("강사별 매출");
		
	}

	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
	
	
	//전체선택 시 테이블뷰 리로딩
	public void filterComboProc() {
		 String selectFilter = filterCombo.getValue();
		if(selectFilter.equals("전체 매출")) {
			salesSvc.tableUp(salesTableView);
		}else if(selectFilter.equals("헬스 회원권 전체 매출")) {
			salesSvc.memSalesTableUp(salesTableView);
		}else if(selectFilter.equals("EX 프로그램 전체 매출")) {
			salesSvc.exProgramSalesTableUp(salesTableView);
		}else if(selectFilter.equals("EX 프로그램 종류별 매출")) {
			salesSvc.detailComboSetting("EXProgram",detailCombo);
			String selectDetail = detailCombo.getValue();
			salesSvc.exProgramTypeSalesTableUp(salesTableView, selectDetail);
		}else if(selectFilter.equals("강사별 매출")) {
			salesSvc.detailComboSetting("Trainer",detailCombo);
			String selectDetail = detailCombo.getValue();
			salesSvc.trainerTypeTableUp(salesTableView, selectDetail);
		}

	}
	//헬스 회원권 매출 선택 시 테이블뷰 로딩
	//EXProgram매출 선택 시 테이블뷰 로딩
	//각 EXprogram 종류 매출 선택 시 테이블뷰 로딩
	//각 EXprogram 강사별 매출 선택 시 테이블뷰 로딩
	
		
		// 이전 버튼 클릭 시
	public void cancelButtonProc() {
		CommonService.WindowClose(salesForm);
		}
	
}
