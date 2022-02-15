package mem.Enroll;

import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import common.CommonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;



public class EnrollService {

	
	public void insert(Parent KG_MEM_FX_Enroll) {
		// 화면에 입력한 데이터 출력하기.
		// System.out.println();
		TextField nameTxt = (TextField) KG_MEM_FX_Enroll.lookup("#nameTxt");
		TextField idTxt = (TextField) KG_MEM_FX_Enroll.lookup("#idTxt");
		PasswordField pwTxt = (PasswordField) KG_MEM_FX_Enroll.lookup("#pwTxt");
		PasswordField confirmTxt = (PasswordField) KG_MEM_FX_Enroll.lookup("#confirmTxt");
		TextField birthTxt = (TextField) KG_MEM_FX_Enroll.lookup("#birthTxt");
		TextField mobileTxt = (TextField) KG_MEM_FX_Enroll.lookup("#mobileTxt");
		TextField addrTxt = (TextField) KG_MEM_FX_Enroll.lookup("#addrTxt");
//		Label colorLabel1 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel1");
//		Label colorLabel2 = (Label) KG_MEM_FX_Enroll.lookup("#colorLabel2");
		
		String name = nameTxt.getText();
		String id = idTxt.getText();
		String pw = pwTxt.getText();
		String confirm = confirmTxt.getText();
		String birth = birthTxt.getText();
		String mobile = mobileTxt.getText();
		String addr = addrTxt.getText();
//		colorLabel1.setTextFill(Color.RED);
//		colorLabel2.setTextFill(Color.RED);
//		Paint label = colorLabel.getTextFill(#e10b0b);
		
		
		RadioButton manRadio = (RadioButton) KG_MEM_FX_Enroll.lookup("#manRadio");
		RadioButton womanRadio = (RadioButton) KG_MEM_FX_Enroll.lookup("#womanRadio");

		String gender = "";
		if (manRadio.isSelected())
			gender += "남";
		else if (womanRadio.isSelected())
			gender += "여";

		if(name.isEmpty() || id.isEmpty() || pw.isEmpty() || birth.isEmpty() || mobile.isEmpty() || addr.isEmpty() ) {
			CommonService.Msg("필수 데이터 입니다.");
			return;
		}
		EnrollDTO enrollDTOC = new EnrollDTO();
		if(enrollDTOC.getID().equals(id)) {
			CommonService.Msg("중복된 ID입니다.");
		}
		
//		if(enrollDTOC.getMobile().equals(mobile)) {
//			colorLabel2.setTextFill(Color.RED);
//		}
		
		if(pw.equals(confirm)) {	
			EnrollDAO enrollDAO = new EnrollDAO();
			EnrollDTO enrollDTO = enrollDAO.SelectId(id);
			if(enrollDTO == null) {
				enrollDTO = new EnrollDTO();
				enrollDTO.setID(id);
				enrollDTO.setPW(pw);
				enrollDTO.setName(name);
				enrollDTO.setGender(gender);
				enrollDTO.setBirth(birth);
				enrollDTO.setMobile(mobile);
				enrollDTO.setAddr(addr);
				
				enrollDAO.insert(enrollDTO);
				CommonService.Msg(" 계정이 등록되었습니다.");
			}
			else {
				CommonService.Msg("는/은 등록된 계정입니다.");
			}
		}
	}
}