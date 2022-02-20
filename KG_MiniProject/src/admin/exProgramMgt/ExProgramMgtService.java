package admin.exProgramMgt;

import java.sql.Date;
import java.time.LocalDate;

import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExProgramMgtService {
	private ExProgramMgtDTO exprogramDto;
	private ExProgramMgtDAO exprogramDao;
	private ListView<String> programListView;
	private TableView<ExProTable> exProgramTableView;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	private String selectData;

	// 실행 시 리스트뷰 업
	public void listUp(ListView<String> programListView) {
		this.programListView = programListView;
		exprogramDao = new ExProgramMgtDAO();
		this.allProgram = exprogramDao.getAllProgram();
		programListView.getItems().addAll(allProgram);
	}

	// 실행 시 테이블뷰 업
	public void tableUp(TableView<ExProTable> exProgramTableView) {
		this.exProgramTableView = exProgramTableView;
		ObservableList<ExProTable> tableItems = FXCollections.observableArrayList();
		ObservableList<ExProgramMgtDTO> allList = exprogramDao.getAllInfo();
		for (ExProgramMgtDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRM_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(), i.getPRMSCHE_Enddate(),
					i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		exProgramTableView.setItems(tableItems);
	}

	// ex프로그램 종류 등록
	public void insertProc(Parent exProgramMgtForm) {
		ListView<String> listView = this.programListView;
		TextField addProgramText = (TextField) exProgramMgtForm.lookup("#addProgramText");
		String addProgram = addProgramText.getText();

		// ex프로그램 종류 중복 체크
		if (addProgram.length() > 0) {
			ExProgramMgtDAO exprogramDao = new ExProgramMgtDAO();
			exprogramDto = exprogramDao.selectExProgram(addProgram);
			if (exprogramDto.getPRM_Name() != null) {
				CommonService.Msg("이미 등록된 EX프로그램 입니다.");
			} else {
				ExProgramMgtDTO exprogramDto = new ExProgramMgtDTO();
				exprogramDto.setPRM_Name(addProgram);
				exprogramDto.setPRM_Code(addProgram + 1); //PRM_Code 임의로생성
			}
			if (exprogramDao.insertExProgram(exprogramDto) == 1) {
				listView.getItems().addAll(addProgram);
				CommonService.Msg("EX프로그램 등록 완료");
			} else {
				CommonService.Msg("EX프로그램 등록 실패");
			}
			addProgramText.clear();
		} else {
			CommonService.Msg("추가 프로그램을 입력하시오");
		}
	}

	// ex프로그램 종류 삭제
	public void deleteProc(Parent exProgramMgtForm) {
		exprogramDao = new ExProgramMgtDAO();
		if (exprogramDao.selectDelete(this.selectData) == 1) {
			CommonService.Msg("EX프로그램 삭제 완료");
		} else {
			CommonService.Msg("EX프로그램 삭제 실패");
		}
		programListView.getItems().remove(this.selectData);

	}

	// 테이블뷰 항목 클릭 시 세부사항 업데이트
	public void modifyTableUp(Parent exProgramMgtForm) {
		Label exnameText = (Label) exProgramMgtForm.lookup("#exnameText");
		Label exPrmNameText = (Label) exProgramMgtForm.lookup("#exPrmNameText");
		TextField priceText = (TextField) exProgramMgtForm.lookup("#priceText");
		TextField personLimitText = (TextField) exProgramMgtForm.lookup("#personLimitText");
		Label currnentDateText = (Label) exProgramMgtForm.lookup("#currnentDateText");
		RadioButton amRadioButton = (RadioButton) exProgramMgtForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton) exProgramMgtForm.lookup("#pmRadioButton");
		DatePicker startDatePicker = (DatePicker) exProgramMgtForm.lookup("#startDatePicker");
		DatePicker endDatePicker = (DatePicker) exProgramMgtForm.lookup("#endDatePicker");
		
		
		exPrmNameText.setText(" : " + codeTable.getProgramName());
		
		exnameText.setText(" : " + codeTable.getProgramName() + "_" +codeTable.getTrainerName()+ "_" + codeTable.getTimeC());

		currnentDateText.setText("현재기간 : " + codeTable.getStrDate() + " ~ " + codeTable.getEndDate());
		Date strDate = codeTable.getStrDate();
		LocalDate localStr = CommonService.DateCnvt(strDate);
		Date endDate = codeTable.getEndDate();
		LocalDate localEnd = CommonService.DateCnvt(endDate);

		startDatePicker.setValue(localStr);
		endDatePicker.setValue(localEnd);

		if (codeTable.getTimeC().equals("오전")) {
			amRadioButton.setSelected(true);
		} else {
			pmRadioButton.setSelected(true);
		}

		priceText.setText(Integer.toString(codeTable.getPrice()));
		personLimitText.setText(Integer.toString(codeTable.getLimtPerson()));

	}

	
	// 상세정보 수정 클릭시
	public void exProgramModifyProc(Parent exProgramMgtForm) {
		Label exnameText = (Label) exProgramMgtForm.lookup("#exnameText");
		Label exPrmNameText = (Label) exProgramMgtForm.lookup("#exPrmNameText");
		TextField priceText = (TextField) exProgramMgtForm.lookup("#priceText");
		TextField personLimitText = (TextField) exProgramMgtForm.lookup("#personLimitText");
		Label currnentDateText = (Label) exProgramMgtForm.lookup("#currnentDateText");
		DatePicker startDatePicker = (DatePicker) exProgramMgtForm.lookup("#startDatePicker");
		DatePicker endDatePicker = (DatePicker) exProgramMgtForm.lookup("#endDatePicker");
		RadioButton amRadioButton = (RadioButton) exProgramMgtForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton) exProgramMgtForm.lookup("#pmRadioButton");

		exPrmNameText.setText(" :");
		exnameText.setText(" :");
		currnentDateText.setText("현재 기간");

		LocalDate lStrDate = startDatePicker.getValue();
		LocalDate lEndDate = endDatePicker.getValue();
		Date strDate = CommonService.LocalDateCnvt(lStrDate);
		Date endDate = CommonService.LocalDateCnvt(lEndDate);

		String timeC = "";
		if (amRadioButton.isSelected())
			timeC = "오전";
		else if (pmRadioButton.isSelected())
			timeC = "오후";
		int price = Integer.parseInt(priceText.getText());
		int personLimit = Integer.parseInt(personLimitText.getText());

		// 클릭한 PRMSCHE_TB 데이터 코드
		String prmScheCode = this.codeTable.getCode();
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(prmScheCode);
		
		String trainerCode = cmnPrmScheDto.getTRAINER_Code();
		CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
		CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(trainerCode);
		String trainerName = cmnTrainerDto.getTRAINER_Name();
		
		String prmCode = cmnPrmScheDto.getPRM_Code();
		CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
		CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(prmCode);
		String prmName = cmnPrmDto.getPRM_Name();
		

		String prmScheName = prmName + "_" + trainerName + "_" + timeC; 
		
		ExProgramMgtDAO exProgramMgtDao = new ExProgramMgtDAO();
		ExProgramMgtDTO exProgramMgtDto = new ExProgramMgtDTO();
		
		exProgramMgtDto.setPRM_Code(prmCode);
		exProgramMgtDto.setPRMSCHE_Strdate(strDate);
		exProgramMgtDto.setPRMSCHE_Enddate(endDate);
		exProgramMgtDto.setPRMSCHE_Time(timeC);
		exProgramMgtDto.setPRMSCHE_Price(price);
		exProgramMgtDto.setPRMSCHE_LimitP(personLimit);
		exProgramMgtDto.setPRMSCHE_Code(prmScheCode);
		exProgramMgtDto.setTRAINER_Code(trainerCode);
		exProgramMgtDto.setTRAINER_Name(trainerName);
		exProgramMgtDto.setPRMSHE_Name(prmScheName);
		
		ExProgramMgtDTO exProgramMgtDto2 = new ExProgramMgtDTO();
		exProgramMgtDto2.setPRM_Code(prmCode);
		

		int result = exProgramMgtDao.selectModifyExProgram(exProgramMgtDto);
		if (result != 1) {
			if(exProgramMgtDao.setProgramModify(exProgramMgtDto) == 1) {
				CommonService.Msg("수정 성공");
			}else
				CommonService.Msg("수정 실패");
		}else {
			CommonService.Msg("수정 실패: 모든사항이 중복됨");
		}
		// 테이블 리로드
		this.tableUp(this.exProgramTableView);
	}

	// 프로그램 세부삭제
	public void exProgramDeleteProc(Parent exProgramMgtForm) {
		String code = this.codeTable.getCode();
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(codeTable.getCode());
		ExProgramMgtDAO exProgramMgtDao = new ExProgramMgtDAO();
		exProgramMgtDao.exProgramDeleteProc(cmnPrmScheDto);

		// 테이블 리로드
		this.tableUp(this.exProgramTableView);

	}

	public void setSelectData(String selectData) {
		this.selectData = selectData;

	}

	public void setCodeTable(ExProTable codeTable) {
		this.codeTable = codeTable;

	}

	public ObservableList<String> getAllProgram() {
		exprogramDao = new ExProgramMgtDAO();
		this.allProgram = exprogramDao.getAllProgram();
		return this.allProgram;
	}

}
