package mem.EXProgramBuying;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mem.BuyingType.MEM_BuyingTypeService;
import mem.Mgt.MgtDAO;
import mem.Mgt.MgtDTO;

public class ExPrmBuyingService {

	private ExProgramBuyingDAO exprogramDao;
	private TableView<ExProTable> exProgramTableView;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	private String selectData;
	private ExPrmBuyingController exPrmBuyingController;
	

	public void setExPrmBuyingController(ExPrmBuyingController exPrmBuyingController) {
		this.exPrmBuyingController = exPrmBuyingController;
	}

	// 실행 시 테이블뷰 업
	public void tableUp(TableView<ExProTable> exProgramTableView) {
		this.exProgramTableView = exProgramTableView;
		ObservableList<ExProTable> tableItems = FXCollections.observableArrayList();
		exprogramDao = new ExProgramBuyingDAO();
		ObservableList<ExProgramBuyingDTO> allList = exprogramDao.getAllInfo();
		for (ExProgramBuyingDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRMSCHE_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(), i.getPRMSCHE_Enddate(),
					i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		exProgramTableView.setItems(tableItems);
	}
	
	//ex프로그램 종류 등록
	public void paymentProc(Parent exProgramBuyingForm, String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = new CmnPrmScheDTO();
		Parent buyingTypeForm;
		
		try {
			buyingTypeForm = loader.load();
			//System.out.println(buyingTypeForm);
			exPrmBuyingController.setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			exPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(buyingTypeForm);
//			System.out.println("exPrmBuyingController.getMembCode(): "+exPrmBuyingController.getMembCode());
			exPrmBuyingController.getMEM_BuyingTypeController().setUserCode(exPrmBuyingController.getMembCode());
//			System.out.println("getMEM_BuyingTypeController.getMembCode(): "+exPrmBuyingController.getMEM_BuyingTypeController().getUserCode());
			exPrmBuyingController.getMEM_BuyingTypeController().setMemWelcomeForm(exPrmBuyingController.getWelcomForm());
			
			
			TableView<ExProTable> exProgramTableView = (TableView<ExProTable>)exProgramBuyingForm.lookup("#exProgramTableView");
			ExProTable tmpData = new ExProTable();
			tmpData = exProgramTableView.getSelectionModel().getSelectedItem();
			if(tmpData == null) {
				CommonService.Msg("프로그램 선택 후 다시 하세요."); return;
			}
			
			// 상단 이름
			Label titleUserName = (Label) buyingTypeForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");
			
			//결제 회원권 이름
			Text ScheNameLabel = (Text)buyingTypeForm.lookup("#ScheNameLabel");
			Text SchePriceLabel = (Text)buyingTypeForm.lookup("#SchePriceLabel");
			Text PayDateLabel = (Text)buyingTypeForm.lookup("#PayDateLabel");
//			
//			//데이터 대입
//			System.out.println(ScheNameLabel.getText());
			
			ScheNameLabel.setText(tmpData.getProgramName());
			SchePriceLabel.setText(Integer.toString(tmpData.getPrice()));
			PayDateLabel.setText(CommonService.getNowDatetoString());

			exPrmBuyingController.getMEM_BuyingTypeController().setPrmScheName(tmpData.getProgramName());
			
			
			
			//코드 번호 개설
			//DB에서 번호의 최대 값을 가지고 와서 +1 해줘서 넣어줘야 함
			int InputCodeNum = cmnPrmScheDao.PrmScheMaxCodeNum()+1;
			cmnPrmScheDto = new CmnPrmScheDTO();
			
			
			String PRMSCHE_CODE = tmpData.getCode();;
			int PRMSCHE_CURRENTP = tmpData.getCurrentPerson()+1;
			int PRMSCHE_LIMITP = tmpData.getLimtPerson();
			int  PRMSCHE_PRICE = tmpData.getPrice();
			String  PRMSCHE_NAME = tmpData.getProgramName();
			Date  PRMSCHE_STRDATE = tmpData.getStrDate();
			Date  PRMSCHE_ENDDATE = tmpData.getEndDate();
			String PRMSCHE_TIME = tmpData.getTimeC();

			CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
			CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltCode(tmpData.getTrainerName());
			String TRAINER_CODE = cmnTrainerDto.getTRAINER_Code();

			cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(PRMSCHE_CODE);	
			String PRM_CODE =cmnPrmScheDto.getPRM_Code();
			
			
			CmnPrmScheDTO cmnPrmScheDtoPass = new CmnPrmScheDTO(PRMSCHE_CODE, InputCodeNum, PRMSCHE_STRDATE, PRMSCHE_ENDDATE, 
					PRMSCHE_TIME, PRMSCHE_LIMITP, PRMSCHE_CURRENTP, PRMSCHE_PRICE, PRM_CODE, TRAINER_CODE, PRMSCHE_NAME);

			exPrmBuyingController.setCmnPrmScheDto(cmnPrmScheDtoPass);
			
			Scene scene = new Scene(buyingTypeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("BuyingType(결제)Form");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//취소 버튼
	public void cancelProc(Parent exProgramMgtForm) {
		CommonService.WindowClose(exProgramMgtForm);
	}
	
	

	public void setCodeTable(ExProTable codeTable) {
		this.codeTable = codeTable;

	}

	
	
}
