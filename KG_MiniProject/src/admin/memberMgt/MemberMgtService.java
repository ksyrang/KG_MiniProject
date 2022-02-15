package admin.memberMgt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

 class MemberMgtService {
	 
	 // @FXML private TextField idtxt, nametxt, pwtxt, mobiletxt, addrtxt;


	// 필터
	public void filter(Parent memberMgtForm) {
		
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		String combo = comboBox.getValue();
		
		ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
		MemberMgtDAO memberMgtDao = new MemberMgtDAO();
		
		if (combo.equals("전체보기")) {
			System.out.println("전체보기");
			
			// 전체 회원 테이블 뷰
			TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
			ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();
			for(MemberMgtDTO m : allList) {
				tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
			}
			allTable.setItems(tableView);
			
		} else if (combo.equals("승인여부")) {
			System.out.println("승인여부");
			
			// 가입 승인 안된 회원 테이블 뷰
			TableView<MemberMgtTable> notApproveTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
			ObservableList<MemberMgtDTO> notApproveList = memberMgtDao.getNotApproveList();
			for(MemberMgtDTO m : notApproveList) {
				tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
			}
			notApproveTable.setItems(tableView);
			
		}
	}
	
	// 테이블뷰 행 클릭시 이벤트
	public void cellClick(Parent memberMgtForm, String colCode) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		idfield.setText(colCode);
		
		
		
	}


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
