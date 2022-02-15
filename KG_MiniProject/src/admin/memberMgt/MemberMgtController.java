package admin.memberMgt;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

public class MemberMgtController implements Initializable{
	private Parent memberMgtForm;
	private MemberMgtService memberMgtSvc;
	
	@FXML private TextField idtxt;
	@FXML private TextField nametxt;
	@FXML private ComboBox<String> filterCombo; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberMgtSvc = new MemberMgtService();
		
		//TextField 편집 붉가능
		idtxt.setEditable(false);
		nametxt.setEditable(false);
		
		
		
	}
	
	public void setMemberMgtForm(Parent memberMgtForm) {
		this.memberMgtForm = memberMgtForm;
	}
	
	// 필터 콤보 박스
	public void memberMgtFilterCombo() {
		System.out.println("콤보 박스");
		String combo = filterCombo.getValue();
		System.out.println(combo);
		
		memberMgtSvc.filter(combo);
	}
	
	// 가입 승인 버튼 클리 시
	public void memberMgtApproveProc() {
		System.out.println("가입 승인 버튼 클릭");
		memberMgtSvc.approve(memberMgtForm);
	}
	// 회원 수정 버튼 클릭 시
	public void memberMgtUpdateProc() {
		System.out.println("회원 수정 버튼 클릭");
		memberMgtSvc.update(memberMgtForm);
	}
	
	//회원 삭제 버튼 클릭 시
	public void memberMgtDeleteProc() {
		System.out.println("회원 삭제 버튼 클릭");
		memberMgtSvc.delete(memberMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void memberMgtCancelProc() {
		System.out.println("이전 버튼 클릭");
		CommonService.WindowClose(memberMgtForm);
	}

}
