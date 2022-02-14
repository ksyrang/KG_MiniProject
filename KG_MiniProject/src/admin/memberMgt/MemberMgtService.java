package admin.memberMgt;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MemberMgtService {

	@FXML
	private TableView<MemberMgtDTO> memberMgtTabel;
	@FXML
	private TableColumn colCode;
	@FXML
	private TableColumn colName;

	// 필터
	public void filter(String combo) {
		ArrayList<MemberMgtDTO> member = new ArrayList<MemberMgtDTO>();
		MemberMgtDAO memberMgtDao = new MemberMgtDAO();
		
		if (combo.equals("전체보기")) {
			System.out.println("전체보기");
			member = memberMgtDao.getAllMemberList();
			for(MemberMgtDTO m : member) {
				System.out.println(m.getMem_code() + "/" + m.getMem_name());
			}
		} else if (combo.equals("승인여부")) {
			System.out.println("승인여부");
		}
	}

	// 가입 승인
	public void approve(Parent memberMgtForm) {

	}

	// 회원 수정
	public void update(Parent memberMgtForm) {

	}

	// 회원 삭제
	public void delete(Parent memberMgtForm) {

	}

}
