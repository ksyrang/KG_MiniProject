package mem.Enroll;


import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;




public class EnrollService {

	EnrollDAO enrollDAO;
	
	//전화번호 중복 체크
	public void mobileConfirmProc(Parent memberJoinForm) {
		TextField mobileTxt = (TextField) memberJoinForm.lookup("#mobileTxt");
		String mobile = mobileTxt.getText();
		if(mobile.isEmpty()) {
			CommonService.Msg("전화번호를 입력해주세요.");
		} else {
			enrollDAO = new EnrollDAO();
			
			if(enrollDAO.SelectMobile(Integer.parseInt(mobile)) == null) {
				CommonService.Msg(mobile + " 은(는) 사용 가능한 전화번호입니다.");
			}else {
				CommonService.Msg(mobile + " 은(는) 이미 사용하고 있는 전화번호입니다.");
				mobileTxt.setText(null);
			}
		}
	}
	
	//아이디 중복 체크
		public void idConfirmProc(Parent memberJoinForm) {
			TextField idTxt = (TextField) memberJoinForm.lookup("#idTxt");
			String id = idTxt.getText();
			if(id.isEmpty()) {
				CommonService.Msg("ID를 입력해주세요.");
			} else {
				enrollDAO = new EnrollDAO();
				
				if(enrollDAO.SelectId(id) == null) {
					CommonService.Msg(id + " 은(는) 사용 가능한 ID입니다.");
				}else {
					CommonService.Msg(id + " 은(는) 이미 사용하고 있는 ID입니다.");
					idTxt.setText(null);
				}
			}
		}
	
	public void insert(Parent memberJoinForm) {
		// 화면에 입력한 데이터 출력하기.
		// System.out.println();
		TextField nameTxt = (TextField) memberJoinForm.lookup("#nameTxt");
		TextField idTxt = (TextField) memberJoinForm.lookup("#idTxt");
		PasswordField pwTxt = (PasswordField) memberJoinForm.lookup("#pwTxt");
		PasswordField confirmTxt = (PasswordField) memberJoinForm.lookup("#confirmTxt");
		TextField birthTxt = (TextField) memberJoinForm.lookup("#birthTxt");
		TextField mobileTxt = (TextField) memberJoinForm.lookup("#mobileTxt");
		TextField addrTxt1 = (TextField) memberJoinForm.lookup("#addrTxt1");
		TextField addrTxt2 = (TextField) memberJoinForm.lookup("#addrTxt2");
		RadioButton manRadio = (RadioButton) memberJoinForm.lookup("#manRadio");
		RadioButton womanRadio = (RadioButton) memberJoinForm.lookup("#womanRadio");
//		Text labelTxt1 = (Text) memberJoinForm.lookup("#labelTxt1");
//		Label colorLabel1 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel1");
//		Label colorLabel2 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel2");
		
		String name = nameTxt.getText();
		String id = idTxt.getText();
		String pw = pwTxt.getText();
		String confirm = confirmTxt.getText();
		String addr = addrTxt1.getText() + "/" + addrTxt2.getText();
		String approve = "false";
//		 label1 = labelTxt1.getTextFill;
//		colorLabel1.setTextFill(Color.RED);
//		colorLabel2.setTextFill(Color.RED);
//		Paint label = colorLabel.getTextFill(#e10b0b);
		
		int birth;
		if (birthTxt.getText().isEmpty()) {
			birth = 0;
		} else {
			birth = Integer.parseInt(birthTxt.getText());
		}

		int mobile;
		if (mobileTxt.getText().isEmpty()) {
			mobile = 0;
		} else {
			mobile = Integer.parseInt(mobileTxt.getText());
		}	
		
		String Code = "Mem_" + id;
		String gender = null;
		if (manRadio.isSelected()) {
			gender += "남";
		}else if (womanRadio.isSelected()) {
			gender += "여"; 
		}else {
			gender = null;
		}
		
		if(name.isEmpty() || id.isEmpty() || pw.isEmpty() || confirm.isEmpty()) {
			CommonService.Msg("필수 데이터 입니다.");
			
		}
		
		
//		if(enrollDTOC.getMobile().equals(mobile)) {
//			colorLabel2.setTextFill(Color.RED);
//		}
		
		if(pw.equals(confirm)) {	
			enrollDAO = new EnrollDAO();
			EnrollDTO enrollDTO = enrollDAO.SelectId(id);
//			System.out.println(enrollDTO);
			if(enrollDTO == null) {
				enrollDTO = new EnrollDTO();
				enrollDTO.setMEM_Code("mem" + id);
				enrollDTO.setMEM_ID(id);
				enrollDTO.setMEM_PW(pw);
				enrollDTO.setMEM_Name(name);
				enrollDTO.setMEM_Gender(gender);
				enrollDTO.setMEM_Birth(birth);
				enrollDTO.setMEM_Mobile(mobile);
				enrollDTO.setMEM_Addr(addr);
				enrollDTO.setMEM_Approve(approve);
				
				enrollDAO.insert(enrollDTO);
				CommonService.Msg(id + " 계정이 등록되었습니다.");			
			}else {
				CommonService.Msg(id + "는/은 등록된 계정입니다.");
				}
		}else {
//			colorLabel1.setTextFill(Color.RED);
			CommonService.Msg("비밀번호가 일치하지 않습니다.");
		}
	}


}