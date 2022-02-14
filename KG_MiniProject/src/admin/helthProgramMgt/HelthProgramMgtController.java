package admin.helthProgramMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class HelthProgramMgtController implements Initializable{
	private Parent helthProgramMgtForm;
	private HelthProgramMgtService healthSvc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		healthSvc = new HelthProgramMgtService();
	}
	
	public void setHelthMgtForm(Parent helthProgramMgtForm) {
		this.helthProgramMgtForm = helthProgramMgtForm;
	}
	
	// 회원권 등록 버튼 클릭 시
	public void memshipInsertProc() {
		System.out.println("회원권 등록");
		healthSvc.memshipInsert(helthProgramMgtForm);
	}
	
	// 회원권 삭제 버튼 클릭 시
	public void memshipDeleteProc() {
		System.out.println("회원권 삭제");
		healthSvc.memshipDelete(helthProgramMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void memshipCancelProc() {
		System.out.println("이전 버튼");
		
	}

}
