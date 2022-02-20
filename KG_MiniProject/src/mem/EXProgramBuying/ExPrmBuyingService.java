package mem.EXProgramBuying;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import admin.exProgramMgt.ExProTable;
import admin.exProgramMgt.ExProgramMgtDAO;
import admin.exProgramMgt.ExProgramMgtDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mem.BuyingType.MEM_BuyingTypeController;


public class ExPrmBuyingService {
	private ExPrmBuyingDTO ExPrmBuyingDto;
	private EXPrmBuyingDAO ExPrmBuyingDao;
	private ExPrmBuyingTable exPrmBuyingTable;
	private ObservableList<String> allProgram;
	private String selectData;
	private ListView<String> programListView;
	private TableView<ExPrmBuyingTable> ExPrmBuyingTableView;
	private ExPrmBuyingController exPrmBuyingController;
	

	
	public void setExPrmBuyingController(ExPrmBuyingController exPrmBuyingController) {
		this.exPrmBuyingController = exPrmBuyingController;
	}

	// 실행 시 리스트뷰 업
		public void listUp(ListView<String> programListView) {
			this.programListView = programListView;
			ExPrmBuyingDao = new EXPrmBuyingDAO();
			this.allProgram = ExPrmBuyingDao.getAllProgram();
			programListView.getItems().addAll(allProgram);
		}

		// 실행 시 테이블뷰 업
		public void tableUp(TableView<ExPrmBuyingTable> exProgramTableView) {
			this.ExPrmBuyingTableView = exProgramTableView;
			ObservableList<ExPrmBuyingTable> tableItems = FXCollections.observableArrayList();
			ObservableList<ExPrmBuyingDTO> allList = ExPrmBuyingDao.getAllInfo();
			for (ExPrmBuyingDTO i : allList) {
				tableItems.add(new ExPrmBuyingTable(i.getPRM_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
						i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(), i.getPRMSCHE_Enddate(),
						i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
			}
			exProgramTableView.setItems(tableItems);
		}
	
	//ex프로그램 종류 등록
	public void paymentProc(Parent buyingTypeForm, String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));

		try {
			buyingTypeForm = loader.load();
			System.out.println(buyingTypeForm);
			exPrmBuyingController.setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			exPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.getMEM_BuyingTypeController().setMembCode(exPrmBuyingController.getMembCode());
			
			Scene scene = new Scene(buyingTypeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("BuyingType(결제)Form");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelProc(Parent exProgramBuyingForm) {
		CommonService.WindowClose(exProgramBuyingForm);
	}
	
	public void logoutProc(Parent exProgramBuyingForm) {
	      CommonService.Msg("로그아웃 되셨습니다.");
	      CommonService.WindowClose(exProgramBuyingForm);
	      
	      
	   }
	
	
	public void insertProc(Parent exProgramBuyingForm) {
		ListView<String> listView = this.programListView;
		TextField addProgramText = (TextField) exProgramBuyingForm.lookup("#addProgramText");
		String addProgram= addProgramText.getText();
		
		//ex프로그램 종류 중복 체크
		if(addProgram.length()>0) {
			ExPrmBuyingDao = new EXPrmBuyingDAO();
			ExPrmBuyingDto = ExPrmBuyingDao.selectExProgram(addProgram);
			if(ExPrmBuyingDto == null) {
				ExPrmBuyingDto = new ExPrmBuyingDTO();
				String PRM_Code = addProgram+"1";
				ExPrmBuyingDto.setPRM_Code(PRM_Code);
				ExPrmBuyingDto.setPRM_Name(addProgram);
				if(ExPrmBuyingDao.insertHealthPrmBuying(ExPrmBuyingDto) == 1) {
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
	
	//ex프로그램 종류 삭제
	public void deleteProc(Parent exProgramBuyingForm) {
		ExPrmBuyingDao = new EXPrmBuyingDAO();
		if(ExPrmBuyingDao.selectDelete(this.selectData)==1) {
			CommonService.Msg("EX프로그램 삭제 완료");
		}else {
			CommonService.Msg("EX프로그램 삭제 실패");
		}
		programListView.getItems().remove(this.selectData);
		
	}

	
	//테이블뷰 항목 클릭 시 테이블 내용 업데이트
	public void modifyTableUp(Parent exProgramBuyingForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>)exProgramBuyingForm.lookup("#kindComboBox");
		Label exnameText = (Label) exProgramBuyingForm.lookup("#exnameText");
		TextField priceText = (TextField) exProgramBuyingForm.lookup("#priceText");
		TextField personLimitText = (TextField) exProgramBuyingForm.lookup("#personLimitText");
		Label currnentDateText = (Label) exProgramBuyingForm.lookup("#currnentDateText");
		RadioButton amRadioButton = (RadioButton)exProgramBuyingForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton)exProgramBuyingForm.lookup("#pmRadioButton");
		
		memshipComboBox.setValue(exPrmBuyingTable.getProgramName());
		exnameText.setText(": "+exPrmBuyingTable.getProgramName()+" - "+exPrmBuyingTable.getTimeC()+"반");
		currnentDateText.setText("현재기간 : " + exPrmBuyingTable.getStrDate() + " ~ " 
												+ exPrmBuyingTable.getEndDate());
		
		if(exPrmBuyingTable.getTimeC().equals("오전")) {
			amRadioButton.setSelected(true);
		}else {
			pmRadioButton.setSelected(true);
		}
		
		priceText.setText(Integer.toString(exPrmBuyingTable.getPrice()));
		personLimitText.setText(Integer.toString(exPrmBuyingTable.getLimtPerson()));

		
	}
	
	
	//상세정보 수정
	public void ExPrmBuyingModifyProc(Parent exProgramBuyingForm) {
		Label exnameText = (Label) exProgramBuyingForm.lookup("#exnameText");
		TextField priceText = (TextField) exProgramBuyingForm.lookup("#priceText");
		TextField personLimitText = (TextField) exProgramBuyingForm.lookup("#personLimitText");
		Label currnentDateText = (Label) exProgramBuyingForm.lookup("#currnentDateText");
		DatePicker startDatePicker = (DatePicker) exProgramBuyingForm.lookup("#startDatePicker");
		DatePicker endDatePicker = (DatePicker) exProgramBuyingForm.lookup("#endDatePicker");
		RadioButton amRadioButton = (RadioButton)exProgramBuyingForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton)exProgramBuyingForm.lookup("#pmRadioButton");
		ComboBox<String> memshipComboBox = (ComboBox<String>)exProgramBuyingForm.lookup("#kindComboBox");
		
		String kind = memshipComboBox.getValue();
		exnameText.setText(": 프로그램명");
		currnentDateText.setText("현재 기간");
		
		LocalDate lStrDate = startDatePicker.getValue();
		LocalDate lEndDate = endDatePicker.getValue();
		Date strDate = (Date)CommonService.LocalDateCnvt(lStrDate);
		Date endDate = (Date)CommonService.LocalDateCnvt(lEndDate);
		
	
		String timeC ="";
		if(amRadioButton.isSelected())
			timeC = "오전";
		else if(pmRadioButton.isSelected());
			timeC = "오후";	
		int price = Integer.parseInt(priceText.getText());
		int personLimit = Integer.parseInt(personLimitText.getText());
		
		//클릭한 PRMSCHE_TB 데이터 코드
		String code = this.exPrmBuyingTable.getCode();
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(exPrmBuyingTable.getCode());

		ExPrmBuyingDTO ExPrmBuyingDto = new ExPrmBuyingDTO();
		ExPrmBuyingDto.setPRM_Name(kind);
		ExPrmBuyingDto.setPRMSCHE_Strdate(strDate);
		ExPrmBuyingDto.setPRMSCHE_Enddate(endDate);
		ExPrmBuyingDto.setPRMSCHE_Time(timeC);
		ExPrmBuyingDto.setPRMSCHE_Price(price);
		ExPrmBuyingDto.setPRMSCHE_LimitP(personLimit);
		ExPrmBuyingDto.setPRMSCHE_Code(code);
		
		EXPrmBuyingDAO ExPrmBuyingDao = new EXPrmBuyingDAO();
		int result = ExPrmBuyingDao.selectModifyHealthPrmBuying(ExPrmBuyingDto, cmnPrmScheDto);
		
		if(result==1) {
			ExPrmBuyingDao.setHealthPrmBuyingModify(ExPrmBuyingDto);
		}else {
			CommonService.Msg("수정 실패: 모든사항이 중복됨");
		}
		
	}
	

	
	public void setSelectData(String selectData) {
		this.selectData = selectData;
		
	}


	public void setExPrmBuyingTable(ExPrmBuyingTable exPrmBuyingTable) {
		this.exPrmBuyingTable = exPrmBuyingTable;
		
	}

	public ObservableList<String> getAllProgram() {
		ExPrmBuyingDao = new EXPrmBuyingDAO();
		this.allProgram = ExPrmBuyingDao.getAllProgram();
		return this.allProgram;
	}
	

}
