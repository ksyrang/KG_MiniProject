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

	private Parent memMgtForm;
	

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

	public Parent getMemMgtForm() {
		return memMgtForm;
	}

	public void setMemMgtForm(Parent memMgtForm) {
		this.memMgtForm = memMgtForm;
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
	//??????????????? ???????????? ??????
	public void MemClickProc() {
		memWelcomeSvc.memMgtOpen(memWelcomeForm, membCode);
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

		// ????????? ????????? ??? ????????? ??????????????? ???????????? ?????? ????????? ??????
		memProgramTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectTable = memProgramTable.getSelectionModel().getSelectedItem();
				memWelcomeSvc.setSelectTable(selectTable);
				String memberStr = null;

				// ????????? ???????????? ?????? ??????
				if (selectTable != null) {
					memberStr = selectTable.getColPrmName();
					String[] memberStr2 = memberStr.split("_"); // ?????????
					String memberStr3 = memberStr2[0];
					if (memberStr3.equals("?????????")) {
						// ????????????
						// ?????? ??????
						String prmType = selectTable.getColPrmName();
						prm_nameTxtFld.setText(prmType);

						// ??????
						int price = selectTable.getColPrmschePrice();
						String strPrice = Integer.toString(price);
						price_TxtFld.setText(strPrice);

						// ??????
						String time = selectTable.getColPrmscheTime();
						prmsche_timeTxtFld.setText(time);

						// ????????? ?????????
						Date strDate = selectTable.getColPrmscheStrdate();
						LocalDate LstrDate = CommonService.DateCnvt(strDate);
						String Sstrdate = LstrDate.toString();
						prmsche_strdateTxtFld.setText(Sstrdate);
						Date endDate = selectTable.getColPrmscheEnddate();
						LocalDate LendDate = CommonService.DateCnvt(endDate);
						String Senddate = LendDate.toString();

						prmsche_enddateTxtFld.setText(Senddate);

						// ?????? ??????
						String trainerName = selectTable.getColTrainerName();
						trainer_nameTxtFld.setText(trainerName);

					} else {
						// ex???????????????

						// ?????? ??????
						String prmName = selectTable.getColPrmName();
						prm_nameTxtFld.setText(prmName);

						// ?????? ??????
						CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
						CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmNameOne(prmName);
						String prmCode = cmnPrmDto.getPRM_Code();

						// ??????
						int price = selectTable.getColPrmschePrice();
						String strPrice = Integer.toString(price);
						price_TxtFld.setText(strPrice);

						// ??????
						String time = selectTable.getColPrmscheTime();
						prmsche_timeTxtFld.setText(time);

						// ????????? ?????????
						Date strDate = selectTable.getColPrmscheStrdate();
						LocalDate LstrDate = CommonService.DateCnvt(strDate);
						String Sstrdate = LstrDate.toString();
						prmsche_strdateTxtFld.setText(Sstrdate);
						Date endDate = selectTable.getColPrmscheEnddate();
						LocalDate LendDate = CommonService.DateCnvt(endDate);
						String Senddate = LendDate.toString();

						// ?????? ??????
						String trainerName = selectTable.getColTrainerName();
						trainer_nameTxtFld.setText(trainerName);

						prmsche_enddateTxtFld.setText(Senddate);
					}
				}

			}
		});

	}

	// ????????? ??????
	public void healthProgramBuyingProc() {
		memWelcomeSvc.healthProgramBuyingProc(membCode, healthProgramBuyingForm);
	}

	// ex???????????? ??????
	public void exProgramBuyingProc() {
		memWelcomeSvc.exProgramBuyingOpen(membCode, exProgramBuyingForm);
	}

	// ????????????
	public void logoutProc() {
		CommonService.WindowClose(memWelcomeForm);
		memWelcomeSvc.LogOut();

	}

	// ??????
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