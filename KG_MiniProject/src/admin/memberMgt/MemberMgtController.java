package admin.memberMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;

import common.CommonService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;

public class MemberMgtController implements Initializable{
	private Parent memberMgtForm;
	private MemberMgtService memberMgtSvc;
	
	@FXML private TextField idtxt;
	@FXML private TextField nametxt;
	@FXML private ComboBox<String> filterCombo; 
	
	@FXML private TableView<MemberMgtTable> memTable;
	@FXML private TableColumn<MemberMgtTable, String> colCode;
	@FXML private TableColumn<MemberMgtTable, String> colName;
	@FXML private TableColumn<MemberMgtTable, String> colApprove;
	
	ObservableList<MemberMgtTable> obserList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberMgtSvc = new MemberMgtService();
		
		//TextField 편집 붉가능
		idtxt.setEditable(false);
		nametxt.setEditable(false);
		
		filterCombo.setValue("전체보기");
		
		MemberMgtDAO memberMgtDao = new MemberMgtDAO();
		ObservableList<MemberMgtDTO> memberMgtDto = memberMgtDao.getAllMemberList();
		
		obserList = FXCollections.observableArrayList();
//				for(int i = 0; i < memberMgtDto.size(); i++) {
//					new MemberMgtTable("mem1", "회원1", "false")
//				}
//				);
		
		colCode.setCellValueFactory(new PropertyValueFactory<>("colCode"));
		colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
		colApprove.setCellValueFactory(new PropertyValueFactory<>("colApprove"));
		for(MemberMgtDTO m : memberMgtDto) {
			obserList.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
		}
		//obserList.add(new MemberMgtTable("mem1", "회원1", "false"));
		
		memTable.setItems(obserList);
		
	}
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	
	// 필터 콤보 박스
	public void memberMgtFilterCombo() {
		memberMgtSvc.filter(memberMgtForm);
	}
	
	// 가입 승인 버튼 클리 시
	public void memberMgtApproveProc() {
		System.out.println("가입 승인 버튼 클릭");
		memberMgtSvc.approve(memberMgtForm);
	}
	// 회원 수정 버튼 클릭 시
	public void memberMgtUpdateProc() {
		System.out.println("회원 수정 버튼 클릭");
		memberMgtSvc.update(memberMgtForm);
	}
	
	//회원 삭제 버튼 클릭 시
	public void memberMgtDeleteProc() {
		System.out.println("회원 삭제 버튼 클릭");
		memberMgtSvc.delete(memberMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void memberMgtCancelProc() {
		System.out.println("이전 버튼 클릭");
		CommonService.WindowClose(memberMgtForm);
	}

}
