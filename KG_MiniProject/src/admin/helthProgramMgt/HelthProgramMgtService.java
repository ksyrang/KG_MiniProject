package admin.helthProgramMgt;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HelthProgramMgtService {
	
	private HelthProgramMgtDAO helthProgramDao;
	
	// 테이블 뷰 불러오기 & 텍스트 필드 내용 비우기
	public void refreshTable(Parent helthProgramMgtForm) {
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		typetxt.clear();
		pricetxt.clear();
		
		ObservableList<HelthProTable> tableView = FXCollections.observableArrayList();
		TableView<HelthProTable> allTable = (TableView<HelthProTable>) helthProgramMgtForm.lookup("#memshipTable");
		ObservableList<HelthProgramMgtDTO> allList = helthProgramDao.getAllPro();
		for(HelthProgramMgtDTO m : allList) {
			String price = CommonService.priceFormat(m.getMemship_price());
			tableView.add(new HelthProTable(m.getMemship_code(), "헬스 회원권 " + m.getMemship_type() + "개월", price));
		}
		allTable.setItems(tableView);
	}
	
	// 테이블뷰 행 클릭 시
	public void cellClick(Parent helthProgramMgtForm, String colCode) {
		helthProgramDao = new HelthProgramMgtDAO();
		HelthProgramMgtDTO helthProgramDto = helthProgramDao.selectCode(colCode);
		
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		typetxt.setText(helthProgramDto.getMemship_type());
		String price = Integer.toString(helthProgramDto.getMemship_price());
		pricetxt.setText(price);
	}
	
	// 헬스 회원권 등록
	public void memshipInsert(Parent helthProgramMgtForm) {
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		
		String type = typetxt.getText();
		String price = pricetxt.getText();
		int hprice = 0;
		try {
			hprice = Integer.parseInt(price);
		} catch (NumberFormatException e) {}
		
		try {
			if (type.isEmpty() || price.isEmpty()) {
				CommonService.Msg("입력란을 채워주세요.");
				typetxt.requestFocus();
			} else {
				if (hprice != 0) {
					helthProgramDao = new HelthProgramMgtDAO();
					HelthProgramMgtDTO helthProgramDto = helthProgramDao.selectType(type);
					if (helthProgramDto == null) {
						helthProgramDto = new HelthProgramMgtDTO();
						helthProgramDto.setMemship_type(type);
						helthProgramDto.setMemship_price(hprice);
						helthProgramDao.memshipInsert(helthProgramDto);

						CommonService.Msg("회원권 등록이 완료되었습니다.");
						refreshTable(helthProgramMgtForm);
					} else {
						CommonService.Msg("이미 등록된 회원권입니다.");
						typetxt.clear();
						pricetxt.clear();
					}
				} else {
					CommonService.Msg("가격란엔 숫자만 입력 가능합니다.");
					pricetxt.clear();
					pricetxt.requestFocus();
				}
			}
		} catch (NullPointerException e) {
			CommonService.Msg("입력란을 채워주세요.");
			typetxt.requestFocus();
		}
	}
	
	//헬스 회원권 삭제
	public void memshipDelete(Parent helthProgramMgtForm) {
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		String type = typetxt.getText();
		
		try {
			HelthProgramMgtDTO healthPrmDto = helthProgramDao.selectType(type);
		
			if (healthPrmDto != null) {
				CmnMemShipScheDAO memshipScheDao = new CmnMemShipScheDAO();
				CmnMemShipScheDTO memshipScheDto = memshipScheDao.SltMemShipOne(healthPrmDto.getMemship_code());
				if (memshipScheDto == null) {
					if (CommonService.CheckMsg("헬스 이용권 " + type + " 개월 회원권을 삭제하시겠습니까?") == true) {
						helthProgramDao.memshipDelete(type);
						CommonService.Msg("헬스 이용권 " + type + " 개월 회원권 삭제");
						refreshTable(helthProgramMgtForm);
					}
				} else {
					CommonService.Msg("회원이 사용하고 있는 회원권입니다. 삭제할 수 없습니다.");
				}
			} else {
				CommonService.Msg("회원권을 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg("회원권을 선택해주세요.");
		}

	}

}
