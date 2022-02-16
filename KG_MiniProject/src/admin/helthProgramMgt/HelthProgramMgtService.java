package admin.helthProgramMgt;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HelthProgramMgtService {
	
	private HelthProgramMgtDAO helthProgramDao;
	
	// 테이블뷰 행 클릭 시
	public void cellClick(Parent helthProgramMgtForm, String colCode) {
		helthProgramDao = new HelthProgramMgtDAO();
		HelthProgramMgtDTO helthProgramDto = helthProgramDao.selectCode(colCode);
		
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		typetxt.setText(helthProgramDto.getMemship_type());
		pricetxt.setText(helthProgramDto.getMemship_price());
	}
	
	// 헬스 회원권 등록
	public void memshipInsert(Parent helthProgramMgtForm) {
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		
		String type = typetxt.getText();
		String price = pricetxt.getText();
		
		try {
			if(type.isEmpty() || price.isEmpty()) {
				CommonService.Msg("입력란을 채워주세요.");
			}else {
				helthProgramDao = new HelthProgramMgtDAO();
				HelthProgramMgtDTO helthProgramDto = helthProgramDao.selectType(type);
				if (helthProgramDto == null) {
					helthProgramDto = new HelthProgramMgtDTO();
					helthProgramDto.setMemship_type(type);
					helthProgramDto.setMemship_price(price);
					helthProgramDao.memshipInsert(helthProgramDto);
					CommonService.Msg("회원권 등록이 완료되었습니다.");
					
					ObservableList<HelthProTable> tableView = FXCollections.observableArrayList();
					TableView<HelthProTable> allTable = (TableView<HelthProTable>) helthProgramMgtForm.lookup("#memshipTable");
					ObservableList<HelthProgramMgtDTO> allList = helthProgramDao.getAllPro();
					for(HelthProgramMgtDTO m : allList) {
						tableView.add(new HelthProTable(m.getMemship_code(), "헬스 회원권 " + m.getMemship_type() + "개월", m.getMemship_price() + "원"));
					}
					allTable.setItems(tableView);
					
					typetxt.setText(null);
					pricetxt.setText(null);
				} else {
					CommonService.Msg("이미 등록된 회원권입니다.");
				}
			}

		} catch (NullPointerException e) {
			CommonService.Msg("입력란을 채워주세요.");
		}
		
		
		
	}
	
	public void memshipDelete(Parent helthProgramMgtForm) {
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		String type = typetxt.getText();
		
		try {
			if(helthProgramDao.selectType(type) != null) {
			helthProgramDao.memshipDelete(type);
			CommonService.Msg("헬스 이용권 " + type + " 개월 회원권 삭제");

			ObservableList<HelthProTable> tableView = FXCollections.observableArrayList();
			TableView<HelthProTable> allTable = (TableView<HelthProTable>) helthProgramMgtForm.lookup("#memshipTable");
			ObservableList<HelthProgramMgtDTO> allList = helthProgramDao.getAllPro();
			for (HelthProgramMgtDTO m : allList) {
				tableView.add(new HelthProTable(m.getMemship_code(), "헬스 회원권 " + m.getMemship_type() + "개월",
						m.getMemship_price() + "원"));
			}
			allTable.setItems(tableView);

			typetxt.setText(null);
			pricetxt.setText(null);
			} else {
				CommonService.Msg("회원권을 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원권을 선택해주세요.");
		}

	}

}
