package admin.exProgramMgt;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import admin.helthProgramMgt.HelthProTable;
import admin.helthProgramMgt.HelthProgramMgtDTO;
import admin.memberMgt.MemberMgtDAO;
import admin.memberMgt.MemberMgtDTO;
import admin.memberMgt.MemberMgtTable;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class ExProgramMgtController implements Initializable{
	private Parent exProgramMgtForm;
	private ExProgramMgtService exProgramSvc;
	private String selectData;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	@FXML private ComboBox<String> kindComboBox;
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML public ListView<String> programListView;
	@FXML public TableView<ExProTable> exProgramTableView;
	@FXML public TableColumn<ExProTable, String> programName;
	@FXML public TableColumn<ExProTable, String> code;
	@FXML public TableColumn<ExProTable, String> trainerName;
	@FXML public TableColumn<ExProTable, Integer> limtPerson;
	@FXML public TableColumn<ExProTable, Integer> currentPerson;
	@FXML public TableColumn<ExProTable, String> strDate;
	@FXML public TableColumn<ExProTable, String> endDate;
	@FXML public TableColumn<ExProTable, Integer> price;
	@FXML public TableColumn<ExProTable, String> timeC;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		exProgramSvc = new ExProgramMgtService();
		//리스트 창 
		exProgramSvc.listUp(this.programListView);
		
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
		this.exProgramSvc.tableUp(exProgramTableView);
		
		
		//수정창
		allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(allProgram);
		
		

		//listview 클릭 시
		programListView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			
			@Override public void handle(MouseEvent event) { 
				selectData = programListView.getSelectionModel().getSelectedItem(); 
				System.out.println(selectData);
				exProgramSvc.setSelectData(selectData);
				}
			});
		
		exProgramTableView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override public void handle(MouseEvent event) { 
				codeTable = exProgramTableView.getSelectionModel().getSelectedItem();
				exProgramSvc.setCodeTable(codeTable);
				exProgramSvc.modifyTableUp(exProgramMgtForm);
				
				}
			});
	}
	
	public void setExProgramMgtForm(Parent exProgramMgtForm) {
		this.exProgramMgtForm = exProgramMgtForm;
	}
	
	
	
	// 등록 버튼 클릭 시
	public void insertProc() {
		System.out.println("프로그램 등록");
		exProgramSvc.insertProc(exProgramMgtForm);
		this.allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(this.allProgram);
	}
	
	// 삭제 버튼 클릭 시
	public void deleteProc() {
		System.out.println("프로그램 삭제");
		exProgramSvc.deleteProc(exProgramMgtForm);
		this.allProgram = exProgramSvc.getAllProgram();
		kindComboBox.setItems(this.allProgram);
	}
	
	
	// 세부 수정 버튼 클릭 시
	public void exProgramModifyProc() {
		System.out.println("프로그램 수정");
		exProgramSvc.exProgramModifyProc(exProgramMgtForm);
	}
	
	// 세부 삭제 버튼 클릭 시
	public void exProgramDeleteProc() {
		System.out.println("프로그램 삭제");
		exProgramSvc.exProgramDeleteProc(exProgramMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void exProgramCancleProc() {
		CommonService.WindowClose(exProgramMgtForm);
	}

	public void getStrDateProc() {
		LocalDate startDate = startDatePicker.getValue();
		String StrStartDate = startDate.toString();
		exProgramSvc.setStrStartDate(StrStartDate);
	}
	public void getEndDateProc() {
		LocalDate endDate = endDatePicker.getValue();
		String StrEndDate = endDate.toString();
		exProgramSvc.setStrEndDate(StrEndDate);
	}
}
