package admin.memberMgt;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

 class MemberMgtService {
	 
	 private MemberMgtDAO memberMgtDao;

	// 필터
	public void filter(Parent memberMgtForm) {
		
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		String combo = comboBox.getValue();
		
		ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
		memberMgtDao = new MemberMgtDAO();
		
		if (combo.equals("전체보기")) {
			// 전체 회원 테이블 뷰
			TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
			ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();
			for(MemberMgtDTO m : allList) {
				tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
			}
			allTable.setItems(tableView);
			
		} else if (combo.equals("승인여부")) {
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
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		ToggleGroup gender = new ToggleGroup();
		men.setToggleGroup(gender);
		women.setToggleGroup(gender);
		
		if(memberMgtDto.getMem_gender() != null) {
			if(memberMgtDto.getMem_gender().equals("남")) {
				men.setSelected(true);
			} else if(memberMgtDto.getMem_gender().equals("여")) {
				women.setSelected(true);
			} 
		}
		
		idfield.setText(memberMgtDto.getMem_id());
		namefield.setText(memberMgtDto.getMem_name());
		pwfield.setText(memberMgtDto.getMem_pw());
		
		if(memberMgtDto.getMem_mobile() != null) {
			mobilefield.setText(memberMgtDto.getMem_mobile());
		} else {
			mobilefield.setText(null);
		}
		if(memberMgtDto.getMem_addr() != null) {
			addrfield.setText(memberMgtDto.getMem_addr());
		} else {
			addrfield.setText(null);
		}
	}


	// 가입 승인
	public void approve(Parent memberMgtForm) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		String id = idfield.getText();
		
		try {
			MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
			if (memberMgtDto != null) {
				if (memberMgtDto.getMem_approve().equals("false")) {
					memberMgtDao.approveUpdate(id);

					CommonService.Msg(id + " 회원님 가입 승인 완료");
					ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
					comboBox.setValue("전체보기");
					ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
					TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
					ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();
					for (MemberMgtDTO m : allList) {
						tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
					}
					allTable.setItems(tableView);
				} else {
					CommonService.Msg("이미 승인된 회원입니다.");
				}
			} else {
				CommonService.Msg("회원을 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원을 선택해주세요.");
		}
		
	}

	// 회원 수정
	public void update(Parent memberMgtForm) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		TextField pwfield = (TextField) memberMgtForm.lookup("#pwtxt");
		TextField mobilefield = (TextField) memberMgtForm.lookup("#mobiletxt");
		TextField addrfield = (TextField) memberMgtForm.lookup("#addrtxt");
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		ToggleGroup genderGroup = new ToggleGroup();
		men.setToggleGroup(genderGroup);
		women.setToggleGroup(genderGroup);
		
		String id = idfield.getText();
		String name = namefield.getText();
		String pw = pwfield.getText();
		String mobile = mobilefield.getText();
		String addr = addrfield.getText();
		String gender = null;
		if(men.isSelected()) {
			gender = "남";
		}else if(women.isSelected()) {
			gender = "여";
		}
		
		try {
			if(name.isEmpty() || pw.isEmpty()) {
				CommonService.Msg("이름과 비밀번호는 필수입니다.");
			}else {
				MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
				if (memberMgtDto != null) {
					memberMgtDao.memberUpdate(id, name, pw, mobile, gender, addr);
					CommonService.Msg(id + " 회원 수정 완료");

					ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
					comboBox.setValue("전체보기");
					ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
					TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
					ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();
					for (MemberMgtDTO m : allList) {
						tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
					}
					allTable.setItems(tableView);

					idfield.setText(null);
					namefield.setText(null);
					pwfield.setText(null);
					mobilefield.setText(null);
					addrfield.setText(null);
					men.setSelected(false);
					women.setSelected(false);
				} else {
					CommonService.Msg("회원을 선택해주세요.");
				}
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원을 선택해주세요.");
		}
		
		
	
	}

	// 회원 삭제
	public void delete(Parent memberMgtForm) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		TextField pwfield = (TextField) memberMgtForm.lookup("#pwtxt");
		TextField mobilefield = (TextField) memberMgtForm.lookup("#mobiletxt");
		TextField addrfield = (TextField) memberMgtForm.lookup("#addrtxt");
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		
		String id = idfield.getText();
		
		try {
			MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
			if (memberMgtDto != null) {
				memberMgtDao.memberDelete(id);
				CommonService.Msg(id + " 회원 삭제 완료");

				ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
				comboBox.setValue("전체보기");
				ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
				TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
				ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();
				for (MemberMgtDTO m : allList) {
					tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
				}
				allTable.setItems(tableView);

				idfield.setText(null);
				namefield.setText(null);
				pwfield.setText(null);
				mobilefield.setText(null);
				addrfield.setText(null);
				men.setSelected(false);
				women.setSelected(false);
			} else {
				CommonService.Msg("회원을 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원을 선택해주세요.");
		}
		
	}

}
