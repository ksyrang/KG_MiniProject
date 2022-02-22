package admin.memberMgt;

import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MemberMgtController implements Initializable{
	private Parent memberMgtForm;
	private MemberMgtService memberMgtSvc;
	
	@FXML private TextField idtxt, nametxt, pwtxt, mobiletxt, addrtxt1, addrtxt2, birthtxt;
	@FXML private ComboBox<String> filterCombo; 
	
	@FXML private TableView<MemberMgtTable> memTable;
	@FXML private TableColumn<MemberMgtTable, String> colCode;
	@FXML private TableColumn<MemberMgtTable, String> colName;
	@FXML private TableColumn<MemberMgtTable, String> colApprove;
	
	ObservableList<MemberMgtTable> obserList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberMgtSvc = new MemberMgtService();
		memberMgtSvc.setMemberMgtController(this);
		initSetting();
		
		// 길이 제한
		nametxt.textProperty().addListener((attribute, before, after) -> {
			nametxt.setText(CommonService.getLengthLimit(5, nametxt.getText()));
		});
		pwtxt.textProperty().addListener((attribute, before, after) -> {
			pwtxt.setText(CommonService.getLengthLimit(20, pwtxt.getText()));
		});
		mobiletxt.textProperty().addListener((attribute, before, after) -> {
			mobiletxt.setText(CommonService.getLengthLimit(11, mobiletxt.getText()));
		});
		birthtxt.textProperty().addListener((attribute, before, after) -> {
			birthtxt.setText(CommonService.getLengthLimit(8, birthtxt.getText()));
		});
		addrtxt1.textProperty().addListener((attribute, before, after) -> {
			addrtxt1.setText(CommonService.getLengthLimit(200, addrtxt1.getText()));
		});
		addrtxt2.textProperty().addListener((attribute, before, after) -> {
			addrtxt2.setText(CommonService.getLengthLimit(100, addrtxt2.getText()));
		});
	}
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	
	//초기세팅
	public void initSetting() {
		// TextField 편집 붉가능
		idtxt.setEditable(false);
		mobiletxt.setEditable(false);

		filterCombo.setValue("전체보기");

		// tableview
		MemberMgtDAO memberMgtDao = new MemberMgtDAO();
		ObservableList<MemberMgtDTO> memberMgtDto = memberMgtDao.getAllMemberList();

		obserList = FXCollections.observableArrayList();

		colCode.setCellValueFactory(new PropertyValueFactory<>("colCode"));
		colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
		colApprove.setCellValueFactory(new PropertyValueFactory<>("colApprove"));
		String approve;
		for (MemberMgtDTO m : memberMgtDto) {
			if(m.getMem_approve().equals("true")) approve = "승인";
			else approve = "미승인";
			obserList.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), approve));
		}

		memTable.setItems(obserList);
	}
	// 필터 콤보 박스
	public void memberMgtFilterCombo() {
		memberMgtSvc.filter(memberMgtForm);
	}
	
	// 테이블뷰 행 클릭시 이벤트 처리
	public void tableClick() {
		memTable.setOnMouseClicked((MouseEvent e) -> {
			try {
				MemberMgtTable mt = memTable.getSelectionModel().getSelectedItem();
				memberMgtSvc.cellClick(memberMgtForm, mt.getColCode());
			} catch (NullPointerException e2) {
				CommonService.Msg("회원을 선택해주세요.");
			}
		});
	}
	
	// 가입 승인 버튼 클리 시
	public void memberMgtApproveProc() {
		memberMgtSvc.approve(memberMgtForm);
	}
	// 회원 수정 버튼 클릭 시
	public void memberMgtUpdateProc() {
		memberMgtSvc.update(memberMgtForm);
	}
	
	//회원 삭제 버튼 클릭 시
	public void memberMgtDeleteProc() {
		memberMgtSvc.delete(memberMgtForm);
	}
	
	
	// 이전 버튼 클릭 시
	public void memberMgtCancelProc() {
		CommonService.WindowClose(memberMgtForm);
	}

}
