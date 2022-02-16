package mem.FindID;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class FindIDService {

	public void FindID(Parent memberFindIDForm) {
		TextField nameTxt = (TextField) memberFindIDForm.lookup("#nameTxt");
		TextField birthTxt = (TextField) memberFindIDForm.lookup("#nameTxt");
		TextField mobileTxt = (TextField) memberFindIDForm.lookup("#nameTxt");
		
		String name = nameTxt.getText();
		String birth = birthTxt.getText();
		String mobile = mobileTxt.getText();
		
		if(name.isEmpty() || birth.isEmpty() || mobile.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}
		
		FindIDDAO findIDDAO = new FindIDDAO();
		String id = findIDDAO.FindID(name, birth, mobile);
		if(id == null) {
			CommonService.Msg(name + "의 계정은 없는 계정 입니다.");
		}else {
			CommonService.Msg(name + "의 계정은" + id + "입니다.");
		}
		
		
	}
	
}
