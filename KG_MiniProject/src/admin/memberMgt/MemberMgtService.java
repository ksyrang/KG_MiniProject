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
	 private MemberMgtController MemberMgtController;
	 private MemberMgtDAO memberMgtDao;

	 public void  setMemberMgtController(MemberMgtController MemberMgtController) {
		 this.MemberMgtController = MemberMgtController;
	 }
	 
	// 필터
	public void filter(Parent memberMgtForm) {
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		String combo = comboBox.getValue();
		if (combo.equals("전체보기")) {
			// 전체 회원 테이블 뷰
			refreshTable(memberMgtForm);
		} else if (combo.equals("승인여부")) {
			// 가입 승인 안된 회원 테이블 뷰
			refreshApporve(memberMgtForm);
		}
	}
	
	// 테이블 전체보기
	public void refreshTable(Parent memberMgtForm) {
		ComboBox<String> comboBox = (ComboBox<String>) memberMgtForm.lookup("#filterCombo");
		comboBox.setValue("전체보기");
		ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
		memberMgtDao = new MemberMgtDAO();
		TableView<MemberMgtTable> allTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
		ObservableList<MemberMgtDTO> allList = memberMgtDao.getAllMemberList();;
		for(MemberMgtDTO m : allList) {
			tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
		}
		allTable.setItems(tableView);
		removeTxt(memberMgtForm);
	}
	
	// 가입승인 안된 테이블 보기
	public void refreshApporve(Parent memberMgtForm) {
		ObservableList<MemberMgtTable> tableView = FXCollections.observableArrayList();
		memberMgtDao = new MemberMgtDAO();
		TableView<MemberMgtTable> notApproveTable = (TableView<MemberMgtTable>) memberMgtForm.lookup("#memTable");
		ObservableList<MemberMgtDTO> notApproveList = memberMgtDao.getNotApproveList();
		for(MemberMgtDTO m : notApproveList) {
			tableView.add(new MemberMgtTable(m.getMem_code(), m.getMem_name(), m.getMem_approve()));
		}
		notApproveTable.setItems(tableView);
		removeTxt(memberMgtForm);
	}
	
	// 텍스트필드 비우기 내용 비우기
	public void removeTxt(Parent memberMgtForm) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		TextField pwfield = (TextField) memberMgtForm.lookup("#pwtxt");
		TextField mobilefield = (TextField) memberMgtForm.lookup("#mobiletxt");
		TextField birthfield = (TextField) memberMgtForm.lookup("#birthtxt");
		TextField addr1field = (TextField) memberMgtForm.lookup("#addrtxt1");
		TextField addr2field = (TextField) memberMgtForm.lookup("#addrtxt2");
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		
		idfield.setText("");
		namefield.setText("");
		pwfield.setText("");
		mobilefield.setText("");
		birthfield.setText("");
		addr1field.setText("");
		addr2field.setText("");
		men.setSelected(false);
		women.setSelected(false);
	}
	
	// 테이블뷰 행 클릭시 이벤트
	public void cellClick(Parent memberMgtForm, String colCode) {
		memberMgtDao = new MemberMgtDAO();
		MemberMgtDTO memberMgtDto = memberMgtDao.selectCode(colCode);
		
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		TextField pwfield = (TextField) memberMgtForm.lookup("#pwtxt");
		TextField mobilefield = (TextField) memberMgtForm.lookup("#mobiletxt");
		TextField birthfield = (TextField) memberMgtForm.lookup("#birthtxt");
		TextField addr1field = (TextField) memberMgtForm.lookup("#addrtxt1");
		TextField addr2field = (TextField) memberMgtForm.lookup("#addrtxt2");
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		ToggleGroup gender = new ToggleGroup();
		men.setToggleGroup(gender);
		women.setToggleGroup(gender);
		
		idfield.setText(memberMgtDto.getMem_id());
		namefield.setText(memberMgtDto.getMem_name());
		pwfield.setText(memberMgtDto.getMem_pw());
		
		if(memberMgtDto.getMem_mobile() != 0) {
			String memMobile = Integer.toString(memberMgtDto.getMem_mobile());
			mobilefield.setText(memMobile);
		} else {
			mobilefield.setText("");
		}
		if(memberMgtDto.getMem_birth() != 0) {
			String memBirth = Integer.toString(memberMgtDto.getMem_birth());
			birthfield.setText(memBirth);
		}else {
			birthfield.setText("");
		}
		
		if(memberMgtDto.getMem_addr() != null) {
			String[] memAddr = memberMgtDto.getMem_addr().split("/");
			if (memAddr.length == 1) {
				addr1field.setText(memAddr[0]);
				addr2field.setText("");
			} else if (memAddr.length == 2) {
				addr1field.setText(memAddr[0]);
				addr2field.setText(memAddr[1]);
			} else {
				addr1field.setText("");
				addr2field.setText("");
			}
		} else {
			addr1field.setText("");
			addr2field.setText("");
		}
		
		if(memberMgtDto.getMem_gender() != null) {
			if(memberMgtDto.getMem_gender().equals("남")) {
				men.setSelected(true);
			} else if(memberMgtDto.getMem_gender().equals("여")) {
				women.setSelected(true);
			} 
		}else {
			men.setSelected(false);
			women.setSelected(false);
		}
	}


	// 가입 승인
	public void approve(Parent memberMgtForm) {
		TextField idfield = (TextField) memberMgtForm.lookup("#idtxt");
		String id = idfield.getText();
		TextField namefield = (TextField) memberMgtForm.lookup("#nametxt");
		String name = namefield.getText();
		
		try {
			MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
			if (memberMgtDto != null) {
				if (memberMgtDto.getMem_approve().equals("false")) {
					memberMgtDao.approveUpdate(id);

					CommonService.Msg(name + "(" + id + ") 회원님 가입 승인 완료");
					filter(memberMgtForm);
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
		TextField birthfield = (TextField) memberMgtForm.lookup("#birthtxt");
		TextField addr1field = (TextField) memberMgtForm.lookup("#addrtxt1");
		TextField addr2field = (TextField) memberMgtForm.lookup("#addrtxt2");
		RadioButton men = (RadioButton) memberMgtForm.lookup("#menradio");
		RadioButton women = (RadioButton) memberMgtForm.lookup("#womenradio");
		ToggleGroup genderGroup = new ToggleGroup();
		men.setToggleGroup(genderGroup);
		women.setToggleGroup(genderGroup);
		
		String id = idfield.getText();
		String name = namefield.getText();
		String pw = pwfield.getText();
		
		int memBirth;
		if(birthfield.getText().isEmpty()) {
			memBirth = 0;
		} else {
			memBirth = Integer.parseInt(birthfield.getText());
		}
		
		String addr = addr1field.getText() + "/" + addr2field.getText();
		String gender;
		if(men.isSelected()) {
			gender = "남";
		} else if(women.isSelected()) {
			gender = "여";
		} else {
			gender = null;
		}
		
		try {
			if(name.isEmpty() || pw.isEmpty()) {
				CommonService.Msg("* 필수 입력란을 입력해주세요.");
			}else {
				MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
				if (memberMgtDto != null) {
					memberMgtDao.memberUpdate(id, name, pw, gender, memBirth, addr);
					CommonService.Msg(name + "(" + id + ") 회원 수정 완료");
					filter(memberMgtForm);
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
		String id = idfield.getText();
		String name = namefield.getText();
		
		try {
			MemberMgtDTO memberMgtDto = memberMgtDao.selectId(id);
			if (memberMgtDto != null) {
				memberMgtDao.memberDelete(id);
				CommonService.Msg(name + "(" + id + ") 회원 삭제 완료");
				filter(memberMgtForm);
			} else {
				CommonService.Msg("회원을 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원을 선택해주세요.");
		}
		
	}

}
