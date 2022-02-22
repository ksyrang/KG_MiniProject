package mem.Delete;

import java.io.IOException;

import Main.login.LoginController;
import Main.main.Controller;
import common.CommonService;
import common.LogOut;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;




public class DeleteService {

	
	private DeleteDAO deleteDAO;
	private DeleteController DltCtrller;
	
	public void setDeleteController(DeleteController DltCtrller) {
		this.DltCtrller = DltCtrller;
	}
	
	public void BackDeleteProc(Parent deleteForm) {
		CommonService.WindowClose(deleteForm);
	}
/*
	public void DeleteProc(Parent deleteForm, String membCode) {
		PasswordField pwTxt = (PasswordField) deleteForm.lookup("#pwTxt");
		String pw = pwTxt.getText();
		String id = membCode.toString();
		
		if (pw.isEmpty()) {
			CommonService.Msg("비밀번호를 입력해주세요.");
		}else {
			if(pw == null) {
				CommonService.Msg("비밀번호를 다시 입력해주세요");
				pwTxt.setText(null);
			}else {
				deleteDAO = new DeleteDAO();
				DeleteDTO deleteDTO = deleteDAO.delete(id);
				
				CommonService.Msg(id + "아이디는 탈퇴 되셨습니다.");
		
			}
		}
	}
	*/
	
	
	public void deleteProc(Parent deleteForm,  String membCode) {
		// 계정이 존재한다면 패스워드가 일치하면 삭제
		PasswordField pwTxt = (PasswordField) deleteForm.lookup("#pwTxt");
		String pw = pwTxt.getText();
		System.out.println(membCode);
		if (pw.isEmpty() || pw == null) {
			CommonService.Msg("비밀번호를 제대로 입력해주세요.");
		}else {
			deleteDAO = new DeleteDAO();
			
			if(deleteDAO.SelectPW(pw) != null) {
				int deleteDTO = deleteDAO.delete(membCode);
				CommonService.WindowClose(deleteForm);
				CommonService.WindowClose(DltCtrller.getMemMgtFrom());
				CommonService.WindowClose(DltCtrller.getMemWelcomeForm());
				
				LogOut logout = new LogOut();
				logout.LogOut();
				CommonService.Msg("탈퇴가 완료되었습니다.");
				
			}else {
				CommonService.Msg("비밀번호가 틀렸습니다.");

			}
			
		}
	}
}
