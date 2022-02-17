package admin.sales;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import admin.exProgramMgt.ExProTable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesController implements Initializable{
	private Parent salesForm;
	private SalesService salesSvc;
	private ObservableList<String> allProgram;
	
	@FXML private TableView<ExProTable> salesTableView;
	@FXML private TableColumn<ExProTable, String> colMemNumber;
	@FXML private TableColumn<ExProTable, String> colProgramName;
	@FXML private TableColumn<ExProTable, String> colProgramType;
	@FXML private TableColumn<ExProTable, Integer> colPrice;
	@FXML private TableColumn<ExProTable, Date> colDate;
	
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
		
		
	}

	public void setSalesForm(Parent salesForm) {
		this.salesForm = salesForm;
	}
}
