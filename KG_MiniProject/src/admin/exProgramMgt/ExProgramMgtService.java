package admin.exProgramMgt;

import java.util.ArrayList;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ExProgramMgtService {
	ExProgramMgtDAO exprogramDao;
	ListView<String> programListView;
	private String selectData;
	
	//실행 시 리스트 업
	public void listUp(ListView<String> programListView) {
		this.programListView = programListView;
		exprogramDao = new ExProgramMgtDAO();
		ObservableList<String> allProgram = exprogramDao.allProgram();
//		ArrayList<String> allProgram = exprogramDao.allProgram();
		programListView.getItems().addAll(allProgram);
	}
	
	//등록
	public void insertProc(Parent exProgramMgtForm) {
		
		ListView<String> listView = this.programListView;
		TextField addProgramText = (TextField) exProgramMgtForm.lookup("#addProgramText");
		String addProgram= addProgramText.getText();
		
		//프로그램 중복 체크
		exprogramDao = new ExProgramMgtDAO();
		ExProgramMgtDTO exprogramDto = exprogramDao.selectExProgram(addProgram);
		if(exprogramDto == null) {
			exprogramDto = new ExProgramMgtDTO();
			String PRM_Code = addProgram+"1";
			exprogramDto.setPRM_Code(PRM_Code);
			exprogramDto.setPRM_Name(addProgram);
			if(exprogramDao.insertExProgram(exprogramDto) == 1) {
				listView.getItems().addAll(addProgram);
				CommonService.Msg("EX프로그램 등록 완료");
			}else 
				CommonService.Msg("EX프로그램 등록 실패");
		}else
			CommonService.Msg("이미 등록된 EX프로그램 입니다.");
		
	}
	
	
	public void deleteProc(Parent exProgramMgtForm) {

		exprogramDao = new ExProgramMgtDAO();
		if(exprogramDao.selectDelete(this.selectData)==1) {
			CommonService.Msg("EX프로그램 삭제 완료");
		}else {
			CommonService.Msg("EX프로그램 삭제 실패");
		}
		
		exprogramDao = new ExProgramMgtDAO();
		ObservableList<String> allProgram = exprogramDao.allProgram();
		ListView<String> listView = (ListView<String>) exProgramMgtForm.lookup("#programListView");
		listView.getItems().addAll(allProgram);
		
	}
	

	//수정
	public void exProgramModifyProc(Parent exProgramMgtForm) {
//		TextField exnameText = (TextField) exProgramMgtForm.lookup("#exnameText");
//		TextField priceText = (TextField) exProgramMgtForm.lookup("#priceText");
//		TextField personLimitText = (TextField) exProgramMgtForm.lookup("#personLimitText");
//		RadioButton amRadioButton = (RadioButton)exProgramMgtForm.lookup("#amRadioButton");
//		RadioButton pmRadioButton = (RadioButton)exProgramMgtForm.lookup("#pmRadioButton");
//
//		
//		String gender ="";
//		
//		if(amRadioButton.isSelected())
//			gender = "남";
//		else if(pmRadioButton.isSelected())
//			gender = "여";
//		String exName= exnameText.getText();
//		int price = Integer.parseInt(priceText.getText());
//		int personLimit = Integer.parseInt(personLimitText.getText());
//		

		

	}

	public void exProgramDeleteProc(Parent exProgramMgtForm) {
		// TODO Auto-generated method stub
		
	}


	public void setSelectData(String selectData) {
		this.selectData = selectData;
		
	}




}
