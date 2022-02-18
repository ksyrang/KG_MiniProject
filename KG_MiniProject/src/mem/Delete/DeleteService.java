package mem.Delete;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mem.Enroll.EnrollDTO;

public class DeleteService {

	private DeleteDAO deleteDAO;

	public void BackDeleteProc(Parent deleteForm) {
		CommonService.WindowClose(deleteForm);
	}
/*
	public void DeleteProc(Parent deleteForm, String membCode) {
		PasswordField pwTxt = (PasswordField) deleteForm.lookup("#pwTxt");
		String pw = pwTxt.getText();
		
		if (pw.isEmpty()) {
			CommonService.Msg("비밀번호를 입력해주세요.");
		
		
		}else {
			if(pw == null) {
				CommonService.Msg("비밀번호를 다시 입력해주세요");
			}else {
				deleteDAO = new DeleteDAO();
				DeleteDTO deleteDTO = deleteDAO.delete(mem_Code);
				deleteDAO.delete(deleteDTO);
				CommonService.Msg(membCode + "아이디는 탈퇴 되셨습니다.");
		
			}
		}
	}
*/
}
