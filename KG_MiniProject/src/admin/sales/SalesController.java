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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesController implements Initializable{
	private Parent salesForm;
	private SalesService salesSvc;
//	private ObservableList<String> allProgram;
	private String selectFilter;
	private int allPrice;
	private String strAllPrice ;
	
	
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
	@FXML private Label totalPriceLabel;
	
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
		
		//로딩 시 전체 매출 표시
		allPrice = salesSvc.seles("전체 매출");
		strAllPrice = CommonService.priceFormat(allPrice);
		totalPriceLabel.setText(strAllPrice);
	}

	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
	
	
	//필터 선택 시
	public void filterComboProc() {
		detailCombo.getItems().clear();
		selectFilter = filterCombo.getValue();
		if(selectFilter.equals("전체 매출")) {
			salesTableView.getItems().clear();
			salesSvc.allSalesTable(salesTableView);
			allPrice = salesSvc.seles("전체 매출");
		}else if(selectFilter.equals("헬스 회원권 전체 매출")) {
			salesTableView.getItems().clear();
			salesSvc.memSalesTableUp(salesTableView);
			allPrice = salesSvc.seles("헬스 회원권 전체 매출");
		}else if(selectFilter.equals("EX 프로그램 전체 매출")) {
			salesTableView.getItems().clear();
			salesSvc.exProgramSalesTableUp(salesTableView);
			allPrice = salesSvc.seles("EX 프로그램 전체 매출");
		}else if(selectFilter.equals("EX 프로그램 종류별 매출")) {
			salesTableView.getItems().clear();
			salesSvc.detailComboSetting("EXProgram",detailCombo);
			allPrice = salesSvc.seles("EX 프로그램 종류별 매출");
		}else if(selectFilter.equals("강사별 매출")) {
			salesTableView.getItems().clear();
			salesSvc.detailComboSetting("Trainer",detailCombo);
			allPrice = salesSvc.seles("강사별 매출");
		}
		
		
		String strAllPrice = CommonService.priceFormat(allPrice);
		totalPriceLabel.setText(strAllPrice);

	}
	
	//세부항목 선택 시
	public void detailComboProc() {
		String selectDetail = detailCombo.getValue();
		if(selectFilter.equals("EX 프로그램 종류별 매출")) {
			salesSvc.exProgramTypeSalesTableUp(salesTableView, selectDetail);
			allPrice = salesSvc.seles("EX 프로그램 종류별 매출",selectDetail);
		}else if(selectFilter.equals("강사별 매출")) {
			salesSvc.trainerTypeTableUp(salesTableView, selectDetail);
			allPrice = salesSvc.seles("강사별 매출",selectDetail);
		}
		String strAllPrice =CommonService.priceFormat(allPrice);
		totalPriceLabel.setText(strAllPrice);
	}

		
		// 이전 버튼 클릭 시
	public void cancelButtonProc() {
		CommonService.WindowClose(salesForm);
		}
	
}
