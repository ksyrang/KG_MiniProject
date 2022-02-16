package mem.FindPW;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class FindPWService {
	
	
	
	public void FindPW(Parent memberFindPWForm) {
		
		TextField idTxt2 = (TextField) memberFindPWForm.lookup("#idTxt2");
		TextField nameTxt2 = (TextField) memberFindPWForm.lookup("#nameTxt2");
		TextField birthTxt2 = (TextField) memberFindPWForm.lookup("#birthTxt2");
		TextField mobileTxt2 = (TextField) memberFindPWForm.lookup("#mobileTxt2");
		
		String pw = null;
		String id = idTxt2.getText();
		String name = nameTxt2.getText();
		String birth = birthTxt2.getText();
		String mobile = mobileTxt2.getText();
		
		if(id.isEmpty() || name.isEmpty() || birth.isEmpty() || mobile.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}else {
			FindPWDAO findPWDAO = new FindPWDAO();
			FindPWDTO findPWDTO = findPWDAO.FindPW(pw, id, name, Integer.parseInt(birth), Integer.parseInt(mobile));
			if(findPWDTO == null) {
				CommonService.Msg(name + "의 계정은 없는 계정 입니다.");
			}else {
				String Pw = findPWDTO.getPw();
				CommonService.Msg(name + "의 계정의 비밀번호는" + Pw + "입니다.");
			}
		}
		
	}
	
}
