package mem.FindID;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class FindIDService {
	
	
	
	public void FindID(Parent memberFindIDForm) {
		TextField nameTxt1 = (TextField) memberFindIDForm.lookup("#nameTxt1");
		TextField birthTxt1 = (TextField) memberFindIDForm.lookup("#birthTxt1");
		TextField mobileTxt1 = (TextField) memberFindIDForm.lookup("#mobileTxt1");
		
		String name = nameTxt1.getText();
		String birth = birthTxt1.getText();
		String mobile = mobileTxt1.getText();
		
		if(name.isEmpty() || birth.isEmpty() || mobile.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}else {
			FindIDDAO findIDDAO = new FindIDDAO();
			FindIDDTO findIDDTO = findIDDAO.FindID(name, birth, mobile);
			if(findIDDTO == null) {
				CommonService.Msg(name + "의 계정은 없는 계정 입니다.");
			}else {
				CommonService.Msg(name + "의 계정은" + findIDDTO + "입니다.");
			}
		}
		
	}
	
}
