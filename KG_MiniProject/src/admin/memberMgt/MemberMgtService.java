package admin.memberMgt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

 class MemberMgtService {
	 
	 // @FXML private TextField idtxt, nametxt, pwtxt, mobiletxt, addrtxt;
	 private MemberMgtDAO memberMgtDao;

	// 필터
	public void filter(Parent memberMgtForm) {
		
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		String combo = comboBox.getValue();
		
		ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
		memberMgtDao = new MemberMgtDAO();
		
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
		memberMgtDao = new MemberMgtDAO();
		MemberMgtDTO memberMgtDto = memberMgtDao.selectCode(colCode);
		
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		TextField pwfield = (TextField) memberMgtForm.lookup("#pwtxt");
		TextField mobilefield = (TextField) memberMgtForm.lookup("#mobiletxt");
		TextField addrfield = (TextField) memberMgtForm.lookup("#addrtxt");
		RadioButton mem = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton womem = (RadioButton) memberMgtForm.lookup("#womenradio");
		ToggleGroup gender = new ToggleGroup();
		mem.setToggleGroup(gender);
		womem.setToggleGroup(gender);
		
		if(memberMgtDto.getMem_gender() != null) {
			if(memberMgtDto.getMem_gender().equals("남")) {
				mem.setSelected(true);
			} else if(memberMgtDto.getMem_gender().equals("여")) {
				womem.setSelected(true);
			} 
		}
		
		idfield.setText(memberMgtDto.getMem_id());
		namefield.setText(memberMgtDto.getMem_name());
		pwfield.setText(memberMgtDto.getMem_pw());
		
		if(memberMgtDto.getMem_mobile() != null) {
			mobilefield.setText(memberMgtDto.getMem_mobile());
		} else {
			mobilefield.setText("정보 없음");
		}
		if(memberMgtDto.getMem_addr() != null) {
			addrfield.setText(memberMgtDto.getMem_addr());
		} else {
			addrfield.setText("정보 없음");
		}
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
