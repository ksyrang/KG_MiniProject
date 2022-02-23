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
	private ExProTable codeTableDTO;
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
	
	//테이블 선택 시
	public void SltExPProc() {
		//선택된 테이블 리스트 get
		TableView<ExProTable> TableView = (TableView<ExProTable>)exPrmBuyingController.getExProgramBuyingForm().lookup("#exProgramTableView");
		//정보를 가져오고 calss변수에 저장
		try {//선택 오류로 인한 null값 입력 시 대처
			codeTableDTO = TableView.getSelectionModel().getSelectedItem();
		} catch (NullPointerException e) {
			return;
		}
		if(codeTableDTO.getCurrentPerson() >= codeTableDTO.getLimtPerson()) {
			CommonService.Msg("선택한 프로그램의 정원이 마감되었습니다.");
			return;
		}		
	}

	
	
	//ex프로그램 종류 등록
	public void paymentProc(Parent exProgramBuyingForm, String membCode) {
		//가져오 데이터를 DB에 입력하기 위한 가공
		/*	ExProTable내 변수
		 *  private String programName;
		 * 	private String code; //프로그램스케줄고유번호 prmschecode
		 * 	private String trainerName;
		 * 	private int limtPerson;
		 * 	private int currentPerson;
		 * 	private Date strDate;
		 * 	private Date endDate;
		 * 	private int price;
		 * 	private String timeC; 
		 */
		//코드 번호 개설
		CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
		CmnPrmScheDTO cmnPrmScheDto = new CmnPrmScheDTO();
		
		//프로그램별로 프로그램스케쥴 최대 값 분리, DB에서 번호의 최대 값을 가지고 와서 +1 해줘서 넣어줘야 함
		String sltPrmCode = cmnPrmScheDao.SltPrmScheOne(codeTableDTO.getCode()).getPRM_Code();
		int InputCodeNum = cmnPrmScheDao.PrmScheMaxCodeNumbyPrm(sltPrmCode)+1;
		
		//SCHE 입력 사항
		String PRMSCHE_CODE = codeTableDTO.getCode();
		int PRMSCHE_CURRENTP = codeTableDTO.getCurrentPerson()+1;
		int PRMSCHE_LIMITP = codeTableDTO.getLimtPerson();
		int  PRMSCHE_PRICE = codeTableDTO.getPrice();
		String  PRMSCHE_NAME = codeTableDTO.getProgramName();
		Date  PRMSCHE_STRDATE = codeTableDTO.getStrDate();
		Date  PRMSCHE_ENDDATE = codeTableDTO.getEndDate();
		String PRMSCHE_TIME = codeTableDTO.getTimeC();

		String TRAINER_CODE = new CmnTrainerDAO().SltCode(codeTableDTO.getTrainerName()).getTRAINER_Code();
		
		cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(PRMSCHE_CODE);	
		
		CmnPrmScheDTO cmnPrmScheDtoPass = new CmnPrmScheDTO(PRMSCHE_CODE, InputCodeNum, PRMSCHE_STRDATE, PRMSCHE_ENDDATE, 
				PRMSCHE_TIME, PRMSCHE_LIMITP, PRMSCHE_CURRENTP, PRMSCHE_PRICE, sltPrmCode, TRAINER_CODE, PRMSCHE_NAME);

		exPrmBuyingController.setCmnPrmScheDto(cmnPrmScheDtoPass);
		

		//화면 표시
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		
		Parent buyingTypeForm;
		
		try {
			buyingTypeForm = loader.load();
			//System.out.println(buyingTypeForm);
			exPrmBuyingController.setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			//결제 화면 정보 set
			exPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(buyingTypeForm);
			//유저코드 set
			exPrmBuyingController.getMEM_BuyingTypeController().setUserCode(exPrmBuyingController.getMembCode());
			//buying form set
			exPrmBuyingController.getMEM_BuyingTypeController().setPreviousForm(exProgramBuyingForm);
			//welcome form set
			exPrmBuyingController.getMEM_BuyingTypeController().setMemWelcomeForm(exPrmBuyingController.getWelcomForm());
			
			//현재 프로그램 리스트 show
			TableView<ExProTable> exProgramTableView = (TableView<ExProTable>)exProgramBuyingForm.lookup("#exProgramTableView");
			ExProTable tmpData = new ExProTable();
			tmpData = exProgramTableView.getSelectionModel().getSelectedItem();
			if(tmpData == null) {
				CommonService.Msg("프로그램 선택 후 다시 하세요."); return;
			}
			
			//표시 데이터 인스턴스
			// 상단 이름
			Label titleUserName = (Label) buyingTypeForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");
			
			//결제 정보
			Text ScheNameLabel = (Text)buyingTypeForm.lookup("#ScheNameLabel");//스케쥴 이름
			Text SchePriceLabel = (Text)buyingTypeForm.lookup("#SchePriceLabel");//스케쥴 가격
			Text PayDateLabel = (Text)buyingTypeForm.lookup("#PayDateLabel");//결제 날짜
			
			//form proc
			ScheNameLabel.setText(codeTableDTO.getProgramName());
			SchePriceLabel.setText(Integer.toString(codeTableDTO.getPrice()));
			PayDateLabel.setText(CommonService.getNowDatetoString());
		
			exPrmBuyingController.getMEM_BuyingTypeController().setPrmScheName(tmpData.getProgramName());
			
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
