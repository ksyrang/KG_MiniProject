package admin.helthProgramMgt;

import java.net.URL;
import java.util.ResourceBundle;

import admin.welcome.WelcomeController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HelthProgramMgtController implements Initializable{
	private Parent helthProgramMgtForm;
	private HelthProgramMgtService healthSvc;
	
	@FXML private TableView<HelthProTable> memshipTable;
	@FXML private TableColumn<HelthProTable, String> colCode;
	@FXML private TableColumn<HelthProTable, String> colType;
	@FXML private TableColumn<HelthProTable, String> colPrice;
	
	ObservableList<HelthProTable> obserList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		healthSvc = new HelthProgramMgtService();
		
		//테이블뷰
		HelthProgramMgtDAO helthProMgtDao = new HelthProgramMgtDAO();
		
	}
	
	public void setHelthMgtForm(Parent helthProgramMgtForm) {
		this.helthProgramMgtForm = helthProgramMgtForm;
	}
	
	
	// 회원권 등록 버튼 클릭 시
	public void memshipInsertProc() {
		healthSvc.memshipInsert(helthProgramMgtForm);
	}
	
	// 회원권 삭제 버튼 클릭 시
	public void memshipDeleteProc() {
		System.out.println("회원권 삭제");
		healthSvc.memshipDelete(helthProgramMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void memshipCancelProc() {
		System.out.println("이전 버튼");
		
	}
	
	

}
