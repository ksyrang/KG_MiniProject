package admin.exProgramMgt;

import java.util.ArrayList;

import admin.helthProgramMgt.HelthProTable;
import admin.helthProgramMgt.HelthProgramMgtDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ExProgramMgtService {
	ExProgramMgtDTO exprogramDto;
	ExProgramMgtDAO exprogramDao;
	ListView<String> programListView;
	TableView<ExProTable> exProgramTableView;
	
	
	private String selectData;
	
	//실행 시 리스트뷰 업
	public void listUp(ListView<String> programListView) {
		this.programListView = programListView;
		exprogramDao = new ExProgramMgtDAO();
		ObservableList<String> allProgram = exprogramDao.getAllProgram();
		programListView.getItems().addAll(allProgram);
	}
	
	
	public void tableUp(TableView<ExProTable> exProgramTableView) {
		this.exProgramTableView = exProgramTableView;
		ObservableList<ExProTable> tableItems = FXCollections.observableArrayList();
		ObservableList<ExProgramMgtDTO> allList = exprogramDao.getAllInfo();
		String trainerName = "김코치";//강사 코드로 get해야함
		String programName = "필라테스";//프로그램 코드로 get 해야함
		for(ExProgramMgtDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRM_Code(), programName, trainerName,
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(),
					i.getPRMSCHE_Enddate(), i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		this.exProgramTableView.setItems(tableItems);
	}
	
	
	//등록
	public void insertProc(Parent exProgramMgtForm) {
		ListView<String> listView = this.programListView;
		TextField addProgramText = (TextField) exProgramMgtForm.lookup("#addProgramText");
		String addProgram= addProgramText.getText();
		
		
		//프로그램 중복 체크
		if(addProgram.length()>0) {
			exprogramDao = new ExProgramMgtDAO();
			exprogramDto = exprogramDao.selectExProgram(addProgram);
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
			addProgramText.clear();
		}else
			CommonService.Msg("추가 프로그램을 입력하시오");
			

	}
	
	//프로그램 삭제
	public void deleteProc(Parent exProgramMgtForm) {
		exprogramDao = new ExProgramMgtDAO();
		if(exprogramDao.selectDelete(this.selectData)==1) {
			CommonService.Msg("EX프로그램 삭제 완료");
		}else {
			CommonService.Msg("EX프로그램 삭제 실패");
		}
		programListView.getItems().remove(this.selectData);
		
		
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
	//프로그램 세부삭제
	public void exProgramDeleteProc(Parent exProgramMgtForm) {
		// TODO Auto-generated method stub
		
	}


	public void setSelectData(String selectData) {
		this.selectData = selectData;
		
	}




}
