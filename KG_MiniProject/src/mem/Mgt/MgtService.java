package mem.Mgt;


import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;




public class MgtService {

	MgtDAO mgtDAO;
	
	//전화번호 중복 체크
	public void mobileConfirmProc(Parent memberMgtForm) {
		TextField mobileTxt = (TextField) memberMgtForm.lookup("#mobileTxt");
		String mobile = mobileTxt.getText();
		if(mobile.isEmpty()) {
			CommonService.Msg("전화번호를 입력해주세요.");
		} else {
			mgtDAO = new MgtDAO();
			
			if(mgtDAO.SelectMobile(Integer.parseInt(mobile)) == null) {
				CommonService.Msg(mobile + " 은(는) 사용 가능한 전화번호입니다.");
			}else {
				CommonService.Msg(mobile + " 은(는) 이미 사용하고 있는 전화번호입니다.");
				mobileTxt.setText(null);
			}
		}
	}
	
	//아이디 중복 체크
		public void idConfirmProc(Parent memberMgtForm) {
			TextField idTxt = (TextField) memberMgtForm.lookup("#idTxt");
			String id = idTxt.getText();
			if(id.isEmpty()) {
				CommonService.Msg("ID를 입력해주세요.");
			} else {
				mgtDAO = new MgtDAO();
				
				if(mgtDAO.SelectId(id) == null) {
					CommonService.Msg(id + " 은(는) 사용 가능한 ID입니다.");
				}else {
					CommonService.Msg(id + " 은(는) 이미 사용하고 있는 ID입니다.");
					idTxt.setText(null);
				}
			}
		}
	
	public void insert(Parent memberMgtForm) {
		// 화면에 입력한 데이터 출력하기.
		// System.out.println();
		TextField nameTxt = (TextField) memberMgtForm.lookup("#nameTxt");
		TextField idTxt = (TextField) memberMgtForm.lookup("#idTxt");
		PasswordField pwTxt = (PasswordField) memberMgtForm.lookup("#pwTxt");
		PasswordField confirmTxt = (PasswordField) memberMgtForm.lookup("#confirmTxt");
		TextField birthTxt = (TextField) memberMgtForm.lookup("#birthTxt");
		TextField mobileTxt = (TextField) memberMgtForm.lookup("#mobileTxt");
		TextField addrTxt = (TextField) memberMgtForm.lookup("#addrTxt");
//		Text labelTxt1 = (Text) memberJoinForm.lookup("#labelTxt1");
//		Label colorLabel1 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel1");
//		Label colorLabel2 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel2");
		
		String name = nameTxt.getText();
		String id = idTxt.getText();
		String pw = pwTxt.getText();
		String confirm = confirmTxt.getText();
		String birth = birthTxt.getText();
		String mobile = mobileTxt.getText();
		String addr = addrTxt.getText();
		String approve = "false";
//		 label1 = labelTxt1.getTextFill;
//		colorLabel1.setTextFill(Color.RED);
//		colorLabel2.setTextFill(Color.RED);
//		Paint label = colorLabel.getTextFill(#e10b0b);
		
		
		RadioButton manRadio = (RadioButton) memberMgtForm.lookup("#manRadio");
		RadioButton womanRadio = (RadioButton) memberMgtForm.lookup("#womanRadio");

		String gender = "";
		if (manRadio.isSelected())
			gender += "남";
		else if (womanRadio.isSelected())
			gender += "여";

		if(name.isEmpty() || id.isEmpty() || pw.isEmpty() || confirm.isEmpty() || birth.isEmpty() || mobile.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}
		
		
//		if(enrollDTOC.getMobile().equals(mobile)) {
//			colorLabel2.setTextFill(Color.RED);
//		}
		
		if(pw.equals(confirm)) {	
			MgtDAO mgtDAO = new MgtDAO();
			MgtDTO mgtDTO = mgtDAO.SelectId(id);
//			System.out.println(enrollDTO);
			if(mgtDTO == null) {
				mgtDTO = new MgtDTO();
				mgtDTO.setMEM_Code("mem" + id);
				mgtDTO.setID(id);
				mgtDTO.setPW(pw);
				mgtDTO.setName(name);
				mgtDTO.setGender(gender);
				mgtDTO.setBirth(Integer.parseInt(birth));
				mgtDTO.setMobile(Integer.parseInt(mobile));
				mgtDTO.setAddr(addr);
				mgtDTO.setApprove(approve);
				
				mgtDAO.insert(mgtDTO);
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