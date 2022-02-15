package admin.memberMgt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberMgtService {

	@FXML private TableView<MemberMgtTable> memTable;
	@FXML private TableColumn<MemberMgtTable, String> colCode;
	@FXML private TableColumn<MemberMgtTable, String> colName;
	@FXML private TableColumn<MemberMgtTable, String> colApprove;
	

	// 필터
	public void filter(Parent memberMgtForm) {
		
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		String combo = comboBox.getValue();
		
		
//		colCode = new TableColumn<MemberMgtTable, String>();
//		colCode.setCellValueFactory(new PropertyValueFactory<>("strCode"));
//		
//		colName = new TableColumn<MemberMgtTable, String>();
//		colName.setCellValueFactory(new PropertyValueFactory<>("strName"));
//		
//		colApprove = new TableColumn<MemberMgtTable, String>();
//		colApprove.setCellValueFactory(new PropertyValueFactory<>("strApprove"));
		
		if (combo.equals("전체보기")) {
			System.out.println("전체보기");
			
//			memTable = new TableView<MemberMgtTable>();
//			memTable.setItems(getCell());
//			memTable.getColumns().addAll(colCode, colName, colApprove);
			
		} else if (combo.equals("승인여부")) {
			System.out.println("승인여부");
		}
	}
	
//	public ObservableList<MemberMgtTable> getCell() {
//		ObservableList<MemberMgtTable> cells = FXCollections.observableArrayList();
//		cells.add(new MemberMgtTable("mem01", "멤버1", "false"));
//		return cells;
//	}

	// 가입 승인
	public void approve(Parent memberMgtForm) {

	}

	// 회원 수정
	public void update(Parent memberMgtForm) {

	}

	// 회원 삭제
	public void delete(Parent memberMgtForm) {

	}

}
