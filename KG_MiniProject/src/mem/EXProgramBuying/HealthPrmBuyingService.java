package mem.EXProgramBuying;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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


public class HealthPrmBuyingService {
	private HealthPrmBuyingDTO healthPrmBuyingDto;
	private HealthPrmBuyingDAO healthPrmBuyingDao;
	private HealthPrmBuyingTable healthPrmBuyingTable;
	private ObservableList<String> allProgram;
	private String selectData;
	private ListView<String> programListView;
	private TableView<HealthPrmBuyingTable> healthPrmBuyingTableView;
	private HealthPrmBuyingController healthPrmBuyingController;
	
	public void setHealthPrmBuyingController(HealthPrmBuyingController healthPrmBuyingController) {
		this.healthPrmBuyingController = healthPrmBuyingController;
	}

	//ex프로그램 종류 등록
	public void paymentProc(Parent healthPrmBuyingForm) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		Parent buyingTypeForm;
		try {
			buyingTypeForm = loader.load();
			System.out.println(buyingTypeForm);
//			healthPrmBuyingController.setHealthPrmBuyingForm(buyingTypeForm);
			
			Scene scene = new Scene(buyingTypeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("BuyingType(결제)Form");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProc(Parent healthPrmBuyingForm) {
		ListView<String> listView = this.programListView;
		TextField addProgramText = (TextField) healthPrmBuyingForm.lookup("#addProgramText");
		String addProgram= addProgramText.getText();
		
		//ex프로그램 종류 중복 체크
		if(addProgram.length()>0) {
			healthPrmBuyingDao = new HealthPrmBuyingDAO();
			healthPrmBuyingDto = healthPrmBuyingDao.selectExProgram(addProgram);
			if(healthPrmBuyingDto == null) {
				healthPrmBuyingDto = new HealthPrmBuyingDTO();
				String PRM_Code = addProgram+"1";
				healthPrmBuyingDto.setPRM_Code(PRM_Code);
				healthPrmBuyingDto.setPRM_Name(addProgram);
				if(healthPrmBuyingDao.insertHealthPrmBuying(healthPrmBuyingDto) == 1) {
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
	public void deleteProc(Parent healthPrmBuyingForm) {
		healthPrmBuyingDao = new HealthPrmBuyingDAO();
		if(healthPrmBuyingDao.selectDelete(this.selectData)==1) {
			CommonService.Msg("EX프로그램 삭제 완료");
		}else {
			CommonService.Msg("EX프로그램 삭제 실패");
		}
		programListView.getItems().remove(this.selectData);
		
	}

	
	//테이블뷰 항목 클릭 시 테이블 내용 업데이트
	public void modifyTableUp(Parent healthPrmBuyingForm) {
		ComboBox<String> memshipComboBox = (ComboBox<String>)healthPrmBuyingForm.lookup("#kindComboBox");
		Label exnameText = (Label) healthPrmBuyingForm.lookup("#exnameText");
		TextField priceText = (TextField) healthPrmBuyingForm.lookup("#priceText");
		TextField personLimitText = (TextField) healthPrmBuyingForm.lookup("#personLimitText");
		Label currnentDateText = (Label) healthPrmBuyingForm.lookup("#currnentDateText");
		RadioButton amRadioButton = (RadioButton)healthPrmBuyingForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton)healthPrmBuyingForm.lookup("#pmRadioButton");
		
		memshipComboBox.setValue(healthPrmBuyingTable.getProgramName());
		exnameText.setText(": "+healthPrmBuyingTable.getProgramName()+" - "+healthPrmBuyingTable.getTimeC()+"반");
		currnentDateText.setText("현재기간 : " + healthPrmBuyingTable.getStrDate() + " ~ " 
												+ healthPrmBuyingTable.getEndDate());
		
		if(healthPrmBuyingTable.getTimeC().equals("오전")) {
			amRadioButton.setSelected(true);
		}else {
			pmRadioButton.setSelected(true);
		}
		
		priceText.setText(Integer.toString(healthPrmBuyingTable.getPrice()));
		personLimitText.setText(Integer.toString(healthPrmBuyingTable.getLimtPerson()));

		
	}
	
	
	//상세정보 수정
	public void healthPrmBuyingModifyProc(Parent healthPrmBuyingForm) {
		Label exnameText = (Label) healthPrmBuyingForm.lookup("#exnameText");
		TextField priceText = (TextField) healthPrmBuyingForm.lookup("#priceText");
		TextField personLimitText = (TextField) healthPrmBuyingForm.lookup("#personLimitText");
		Label currnentDateText = (Label) healthPrmBuyingForm.lookup("#currnentDateText");
		DatePicker startDatePicker = (DatePicker) healthPrmBuyingForm.lookup("#startDatePicker");
		DatePicker endDatePicker = (DatePicker) healthPrmBuyingForm.lookup("#endDatePicker");
		RadioButton amRadioButton = (RadioButton)healthPrmBuyingForm.lookup("#amRadioButton");
		RadioButton pmRadioButton = (RadioButton)healthPrmBuyingForm.lookup("#pmRadioButton");
		ComboBox<String> memshipComboBox = (ComboBox<String>)healthPrmBuyingForm.lookup("#kindComboBox");
		
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
		String code = this.healthPrmBuyingTable.getCode();
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(healthPrmBuyingTable.getCode());

		HealthPrmBuyingDTO healthPrmBuyingDto = new HealthPrmBuyingDTO();
		healthPrmBuyingDto.setPRM_Name(kind);
		healthPrmBuyingDto.setPRMSCHE_Strdate(strDate);
		healthPrmBuyingDto.setPRMSCHE_Enddate(endDate);
		healthPrmBuyingDto.setPRMSCHE_Time(timeC);
		healthPrmBuyingDto.setPRMSCHE_Price(price);
		healthPrmBuyingDto.setPRMSCHE_LimitP(personLimit);
		healthPrmBuyingDto.setPRMSCHE_Code(code);
		
		HealthPrmBuyingDAO healthPrmBuyingDao = new HealthPrmBuyingDAO();
		int result = healthPrmBuyingDao.selectModifyHealthPrmBuying(healthPrmBuyingDto, cmnPrmScheDto);
		
		if(result==1) {
			healthPrmBuyingDao.setHealthPrmBuyingModify(healthPrmBuyingDto);
		}else {
			CommonService.Msg("수정 실패: 모든사항이 중복됨");
		}
		
	}
	
	public void setSelectData(String selectData) {
		this.selectData = selectData;
		
	}


	public void setHealthPrmBuyingTable(HealthPrmBuyingTable healthPrmBuyingTable) {
		this.healthPrmBuyingTable = healthPrmBuyingTable;
		
	}

	public ObservableList<String> getAllProgram() {
		healthPrmBuyingDao = new HealthPrmBuyingDAO();
		this.allProgram = healthPrmBuyingDao.getAllProgram();
		return this.allProgram;
	}


}
