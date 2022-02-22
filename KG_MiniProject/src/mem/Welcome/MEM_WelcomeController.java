package mem.Welcome;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CommonService;
import common.LogOut;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	private LogOut logOut;
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

	public LogOut getLogOut() {
		return logOut;
	}

	public void setLogOut(LogOut logOut) {
		this.logOut = logOut;
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

		prm_nameTxtFld.setEditable(false);
		trainer_nameTxtFld.setEditable(false);
		prmsche_strdateTxtFld.setEditable(false);
		prmsche_enddateTxtFld.setEditable(false);
		prmsche_timeTxtFld.setEditable(false);
		price_TxtFld.setEditable(false);

		// 테이블 뷰에서 각 항목을 선택했을때 출력창에 표시 해주는 부분
		memProgramTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectTable = memProgramTable.getSelectionModel().getSelectedItem();
				memWelcomeSvc.setSelectTable(selectTable);
				String memberStr = null;

				// 내용을 클릭했을 때만 실행
				if (selectTable != null) {
					memberStr = selectTable.getColPrmName();
					String[] memberStr2 = memberStr.split("_"); // 회원권
					String memberStr3 = memberStr2[0];
					if (memberStr3.equals("회원권")) {
						// 회원권임
						// 강의 종류
						String prmType = selectTable.getColPrmName();
						prm_nameTxtFld.setText(prmType);

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

						// 강사 이름
						String trainerName = selectTable.getColTrainerName();
						trainer_nameTxtFld.setText(trainerName);

					} else {
						// ex프로그램임

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

						// 강사 이름
						String trainerName = selectTable.getColTrainerName();
						trainer_nameTxtFld.setText(trainerName);

						prmsche_enddateTxtFld.setText(Senddate);
					}
				}

			}
		});

	}

	// 헬스권 구매
	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc(membCode, healthProgramBuyingForm);
	}

	// ex프로그램 구매
	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingOpen(membCode, exProgramBuyingForm);
	}

	// 로그아웃
	public void logoutProc() {
		memWelcomeSvc.LogOut();

	}

	// 취소
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