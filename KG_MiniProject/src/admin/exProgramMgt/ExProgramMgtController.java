package admin.exProgramMgt;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class ExProgramMgtController implements Initializable{
	private Parent exProgramMgtForm;
	private ExProgramMgtService exProgramSvc;
	private String selectData;
	
	@FXML public ListView<String> programListView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exProgramSvc = new ExProgramMgtService();
		exProgramSvc.listUp(this.programListView);
		
		programListView.refresh();
		programListView.setOnMouseClicked(new EventHandler<MouseEvent>() { 
			@Override public void handle(MouseEvent event) { 
				selectData = programListView.getSelectionModel().getSelectedItem(); 
				System.out.println(selectData);
				exProgramSvc.setSelectData(selectData);
				programListView.refresh();
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
	}
	
	// 삭제 버튼 클릭 시
	public void deleteProc() {
		System.out.println("프로그램 삭제");
		exProgramSvc.deleteProc(exProgramMgtForm);
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
		System.out.println("이전 버튼");
	}

}
