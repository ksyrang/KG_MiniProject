package mem.Welcome;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Main.login.LoginService;
import Main.main.Controller;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mem.EXProgramBuying.ExPrmBuyingController;
import mem.HelthProgramBuying.HealthPrmBuyingController;
import mem.Mgt.MgtController;
import mem.ProgramMgt.ProgramMgtController;

public class MEM_WelcomeController implements Initializable {

	private Parent healthProgramBuyingForm;
	private Parent exProgramBuyingForm;

	private ExPrmBuyingController exPrmBuyingController;
	private HealthPrmBuyingController healthPrmBuyingController;
	private MEM_WelcomeController memWelcomeController;
	private MgtController memMgtController;
	private ProgramMgtController programMgtController;

	private MEM_WelcomeService memWelcomeSvc;
	private Parent memWelcomeForm;
	private Parent programMgtForm;
	private String membCode;

	private MEM_WelcomeMgtTable selectTable;

	@FXML
	private TextField prm_nameTxtFld;
	@FXML
	private TextField trainer_nameTxtFld;
	@FXML
	private TextField prmsche_strdateTxtFld;
	@FXML
	private TextField prmsche_enddateTxtFld;
	@FXML
	private TextField prmsche_timeTxtFld;
	@FXML
	private TextField price_TxtFld;

	

	@FXML
	private TableView<MEM_WelcomeMgtTable> memProgramTable;
	@FXML
	private TableColumn<MEM_WelcomeMgtTable, String> colPrmName;
	@FXML
	private TableColumn<MEM_WelcomeMgtTable, String> colPrmscheTime;
	@FXML
	private TableColumn<MEM_WelcomeMgtTable, Integer> colPrmschePrice;
	@FXML
	private TableColumn<MEM_WelcomeMgtTable, Date> colPrmscheStrdate;
	@FXML
	private TableColumn<MEM_WelcomeMgtTable, Date> colPrmscheEnddate;

	ObservableList<MEM_WelcomeMgtTable> obserList;

	public MEM_WelcomeController() {
		memWelcomeSvc = new MEM_WelcomeService();
		memWelcomeSvc.setMEM_WelcomeController(this);
	}

	public MEM_WelcomeController getMem_WelcomeController() {
		return memWelcomeController;
	}

	public void setMem_WelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}

	////
	public String getMembCode() {
		return this.membCode;
	}

	public void setMembCode(String membCode) {
		this.membCode = membCode;
	}

	
	public MgtController getMgtController() {
		return memMgtController;
	}

	public void setMgtController(MgtController memMgtController) {
		this.memMgtController = memMgtController;
	}

	public void setMemWelcomeForm(Parent memWelcomeForm) {
		this.memWelcomeForm = memWelcomeForm;
	}

	public Parent getMemWelcomeForm() {
		return memWelcomeForm;
	}

	public void MemClickProc() {
		memWelcomeSvc.memMgtOpen(membCode);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//		MEM_WelcomeDAO memWelcomeDao = new MEM_WelcomeDAO();
//		obserList = FXCollections.observableArrayList();

		colPrmName.setCellValueFactory(new PropertyValueFactory<>("colPrmName"));
		colPrmscheTime.setCellValueFactory(new PropertyValueFactory<>("colPrmscheTime"));
		colPrmschePrice.setCellValueFactory(new PropertyValueFactory<>("colPrmschePrice"));
		colPrmscheStrdate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheStrdate"));
		colPrmscheEnddate.setCellValueFactory(new PropertyValueFactory<>("colPrmscheEnddate"));

//		Controller controller = new Controller();
//		this.id = controller.getid();
//		

//		ObservableList<MEM_WelcomeDTO> memWelcomeDto = memWelcomeDao.selectMemAllProgram("admin");
//
//		for (MEM_WelcomeDTO m : memWelcomeDto) {
//			// String prm_name 얻는 과정
//			String prm_Code = m.getPrm_code();
//			CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
//			CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(prm_Code);
//			String prm_name = cmnPrmDto.getPRM_Name();
//
//			String prmsche_time = m.getPrmsche_time();
//			int prmsche_price = m.getPrmsche_price();
//			Date prmsche_strdate = m.getPrmsche_strdate();
//			Date prmsche_enddate = m.getPrmsche_enddate();
//			obserList.add(
//					new MEM_WelcomeMgtTable(prm_name, prmsche_time, prmsche_price, prmsche_strdate, prmsche_enddate));
//		}
//		memProgramTable.setItems(obserList);

//회색 버튼 의 핸들러 부분 여기를 누르면 테이블뷰의 리스트가 출력됩니다.
//    @FXML
//    void sel_bt_view(ActionEvent event) {
//   }

//테이블 뷰에서 각 항목을 선택했을때 출력창에 표시 해주는 부분
		memProgramTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectTable = memProgramTable.getSelectionModel().getSelectedItem();
				memWelcomeSvc.setSelectTable(selectTable);
				
				// 강의 종류
				String prmName = selectTable.getColPrmName();
				prm_nameTxtFld.setText(prmName);

				// 강의 코드
				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmNameOne(prmName);
				String prmCode = cmnPrmDto.getPRM_Code();

				// 가격
				int price = selectTable.getColPrmschePrice();
				String strPrice = Integer.toString(price);
				price_TxtFld.setText(strPrice);

				// 시간
				String time = selectTable.getColPrmscheTime();
				prmsche_timeTxtFld.setText(time);

				// 시작일 종료일
				Date strDate = selectTable.getColPrmscheStrdate();
				LocalDate LstrDate = CommonService.DateCnvt(strDate);
				String Sstrdate = LstrDate.toString();
				prmsche_strdateTxtFld.setText(Sstrdate);
				Date endDate = selectTable.getColPrmscheEnddate();
				LocalDate LendDate = CommonService.DateCnvt(endDate);
				String Senddate = LendDate.toString();
				prmsche_enddateTxtFld.setText(Senddate);

//				// 강사코드, 강사명 꼭 넣어야하나요..?
//				String trainerCode = null;
//				CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
//				ArrayList<CmnPrmScheDTO> cmnPrmScheDto = cmnPrmScheDao.SltPrmScheAll();
//				for (CmnPrmScheDTO i : cmnPrmScheDto) {
//					if (i.getPRM_Code().equals(prmCode) & i.getPRMSCHE_Price() == price
//							& i.getPRMSCHE_Time().equals(time) & i.getPRMSCHE_Strdate().equals(strDate)
//							& i.getPRMSCHE_Enddate().equals(endDate)) {
//						trainerCode = i.getTRAINER_Code();
//					}
//				}
//				CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
//				CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(trainerCode);
//				String trainerName = cmnTrainerDto.getTRAINER_Name();
//				trainer_nameTxtFld.setText(trainerName);
//				int trinerCareer = cmnTrainerDto.getTRAINER_Career();
//				String strTrinerCareer = Integer.toString(trinerCareer);
//				trainer_careerTxtFld.setText(strTrinerCareer);

				// 수정창으로 넘겨야할 정보
				// 1.프로그램 명
				// 2.가격
				// 3.1 회원권 시작날짜 종료 날짜
				// 3.2 ex프로그램 시작날짜

			}
		});

	}

	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc(membCode, healthProgramBuyingForm);
	}

	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingOpen(membCode, exProgramBuyingForm);
	}

	// 수정 버튼 클릭시
	public void deleteProc() {
		memWelcomeSvc.deleteProc(memWelcomeForm);

	}

	public void logoutProc() {
		memWelcomeSvc.logoutProc(memWelcomeForm);
		CommonService.WindowClose(memWelcomeForm);

	}

	public void cancelProc() {
		memWelcomeSvc.cancelProc(memWelcomeForm);
	}

	public void setHealthProgramBuyingForm(Parent healthProgramBuyingForm) {
		this.healthProgramBuyingForm = healthProgramBuyingForm;
	}

	public void setExProgramBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
	}

	public void setExPrmBuyingController(ExPrmBuyingController exPrmBuyingController) {
		this.exPrmBuyingController = exPrmBuyingController;

	}

	public ExPrmBuyingController getExPrmBuyingController() {
		return exPrmBuyingController;
	}

	public void setMemExpBuyingForm(Parent exProgramBuyingForm) {
		this.exProgramBuyingForm = exProgramBuyingForm;
	}

	public HealthPrmBuyingController getHealthPrmBuyingController() {
		return healthPrmBuyingController;
	}

	public void setHealthPrmBuyingController(HealthPrmBuyingController healthPrmBuyingController) {
		this.healthPrmBuyingController = healthPrmBuyingController;
	}

	public void setProgramMgtController(ProgramMgtController programMgtController) {
		this.programMgtController = programMgtController;

	}

	public void setProgramMgtForm(Parent programMgtForm) {
		this.programMgtForm = programMgtForm;

	}

	public void settingProgramMgt() {
		this.programMgtController.setProgramMgtForm(this.programMgtForm);
	}

	


}