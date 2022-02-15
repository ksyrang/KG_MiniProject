package admin.exProgramMgt;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ExProgramMgtService {

	
	public void exProgramInsertProc(Parent exProgramMgtForm) {
		TextField exnameText = (TextField) exProgramMgtForm.lookup("#exnameText");
		TextField priceText = (TextField) exProgramMgtForm.lookup("#priceText");
		TextField personLimitText = (TextField) exProgramMgtForm.lookup("#personLimitText");
		RadioButton amRadioButton = (RadioButton)exProgramMgtForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton)exProgramMgtForm.lookup("#pmRadioButton");
		ListView<String> programListView = (ListView<String>) exProgramMgtForm.lookup("#programListView");
		
		String gender ="";
		
		if(amRadioButton.isSelected())
			gender = "남";
		else if(pmRadioButton.isSelected())
			gender = "여";
		String exName= exnameText.getText();
		int price = Integer.parseInt(priceText.getText());
		int personLimit = Integer.parseInt(personLimitText.getText());
		String PRM_Code = exName+"1";
		ExProgramMgtDAO exprogramDao = new ExProgramMgtDAO();
		ExProgramMgtDTO exprogramDto = exprogramDao.selectExProgram(exName);
		if(exprogramDto == null) {
			exprogramDto = new ExProgramMgtDTO();
			exprogramDto.setPRM_Code(PRM_Code);
			exprogramDto.setPRM_Name(exName);
			exprogramDto.setPRM_Price(price);
			if(exprogramDao.insertExProgram(exprogramDto) == 1)
				CommonService.Msg("EX프로그램 등록 완료");
			else 
				CommonService.Msg("EX프로그램 등록 실패");
		} else {
			CommonService.Msg("이미 등록된 EX프로그램 입니다.");
		}
	}

	public void exProgramModifyProc(Parent exProgramMgtForm) {
		// TODO Auto-generated method stub
		
	}

	public void exProgramDeleteProc(Parent exProgramMgtForm) {
		// TODO Auto-generated method stub
		
	}

}
