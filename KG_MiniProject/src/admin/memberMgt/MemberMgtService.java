package admin.memberMgt;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberMgtService {
	
	@FXML private TableView<MemberMgtDTO> memberMgtTabel;
	@FXML private TableColumn<MemberMgtDTO, String> colCode;
	@FXML private TableColumn<MemberMgtDTO, String> colName;
	
	
	
	// 필터
	public void filter(String combo) {
		MemberMgtDAO memberMgtDao = new MemberMgtDAO();
		//MemberMgtDTO memberMgtDto = new MemberMgtDTO();
		ObservableList<MemberMgtDTO> data;
		if(combo.equals("전체보기")) {
			System.out.println("전체보기");
			
			//colCode.setCellValueFactory(new PropertyValueFactory<MemberMgtDTO, String>("mem_code"));
			//colCode.setCellValueFactory(new PropertyValueFactory<MemberMgtDTO, String>("mem_name"));
			
			List<MemberMgtDTO> memList = memberMgtDao.getAllMemberList();
			data = FXCollections.observableArrayList(memList);
			
			memberMgtTabel.setItems(data);
			
		}else if(combo.equals("승인여부")) {
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
