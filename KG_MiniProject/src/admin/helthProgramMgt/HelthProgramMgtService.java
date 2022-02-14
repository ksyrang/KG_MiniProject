package admin.helthProgramMgt;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class HelthProgramMgtService {
	
	//@FXML private TextField memshipType, memshipPrice;
	
	
	public void memshipInsert(Parent helthProgramMgtForm) {
		System.out.println("회원권 등록 svc");
		
		System.out.println(helthProgramMgtForm);
		TextField typetxt = (TextField) helthProgramMgtForm.lookup("#memshipType");
		TextField pricetxt = (TextField) helthProgramMgtForm.lookup("#memshipPrice");
		
		String type = typetxt.getText();
		int price = Integer.parseInt(pricetxt.getText());
		
		System.out.println(type);
		System.out.println(price);
		
		HelthProgramMgtDAO helthProgramDao = new HelthProgramMgtDAO();
		HelthProgramMgtDTO helthProgramDto = helthProgramDao.selectType(type);
		if(helthProgramDto == null) {
			helthProgramDto = new HelthProgramMgtDTO();
			helthProgramDto.setMemship_type(type);
			helthProgramDto.setMemship_price(price);
			
			if(helthProgramDao.memshipInsert(helthProgramDto) == 1) {
				CommonService.Msg("회원권 등록이 완료되었습니다.");
			} else {
				CommonService.Msg("회원권 등록에 실패");
			}
			
		} else {
			CommonService.Msg("이미 등록된 회원권입니다.");
		}

		
	}
	
	public void memshipDelete(Parent helthProgramMgtForm) {
		
	}

}
