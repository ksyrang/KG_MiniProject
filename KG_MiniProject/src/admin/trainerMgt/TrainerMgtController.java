package admin.trainerMgt;

import java.net.URL;
import java.util.ResourceBundle;

import admin.trainerEnroll.TrainerEnrollController;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TrainerMgtController implements Initializable{
	
	private TrainerMgtController TrainerMgtController;
	private Parent trainerMgtForm;
	private TrainerMgtService trainerMgtSvc;
	// 강사 등록
	private Parent trainerEnrollForm;
	private TrainerEnrollController trainerEnrollController;
	
	
	
	@FXML private TextField trnIdTxt, trnNameTxt, trnPwTxt, trnMobileTxt, trnAddrTxt1, trnAddrTxt2, trnBirthTxt, trnCareerTxt;
	@FXML private TableView<TrainerMgtTable> trnTable;
	@FXML private TableColumn<TrainerMgtTable, String> colTrnCode;
	@FXML private TableColumn<TrainerMgtTable, String> colTrnName;
	@FXML private TableColumn<TrainerMgtTable, Integer> colTrnMobile;
	
	ObservableList<TrainerMgtTable> obserList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trainerMgtSvc = new TrainerMgtService();
		trainerMgtSvc.setTrainerMgtController(this);
		initSetting();
		
		// 길이제한
		trnNameTxt.textProperty().addListener((attribute,before, after) -> {
			trnNameTxt.setText(CommonService.getLengthLimit(5, trnNameTxt.getText()));
		});
		trnPwTxt.textProperty().addListener((attribute,before, after) -> {
			trnPwTxt.setText(CommonService.getLengthLimit(20, trnPwTxt.getText()));
		});
		trnMobileTxt.textProperty().addListener((attribute,before, after) -> {
			trnMobileTxt.setText(CommonService.getLengthLimit(11, trnMobileTxt.getText()));
		});
		trnAddrTxt1.textProperty().addListener((attribute,before, after) -> {
			trnAddrTxt1.setText(CommonService.getLengthLimit(200, trnAddrTxt1.getText()));
		});
		trnAddrTxt2.textProperty().addListener((attribute,before, after) -> {
			trnAddrTxt2.setText(CommonService.getLengthLimit(100, trnAddrTxt2.getText()));
		});
		trnBirthTxt.textProperty().addListener((attribute,before, after) -> {
			trnBirthTxt.setText(CommonService.getLengthLimit(8, trnBirthTxt.getText()));
		});
		trnCareerTxt.textProperty().addListener((attribute,before, after) -> {
			trnCareerTxt.setText(CommonService.getLengthLimit(2, trnCareerTxt.getText()));
		});
	}
	
	public TrainerMgtController getTrainerMgtController() {
		return TrainerMgtController;
	}
	
	public void setTrainerMgtController(TrainerMgtController trainerMgtController) {
		this.TrainerMgtController = trainerMgtController;
	}

	public void setTrainerMgtForm(Parent trainerMgtForm) {
		this.trainerMgtForm = trainerMgtForm;
	}
	
	public Parent getTrainerMgtForm() {
		return trainerMgtForm;
	}

	//초기세팅
	public void initSetting() {
		// id textfield 입력 불가
		trnIdTxt.setEditable(false);

		// trnTable
		CmnTrainerDAO dao = new CmnTrainerDAO();
		ObservableList<CmnTrainerDTO> dto = dao.OLSltTrnAll();

		obserList = FXCollections.observableArrayList();

		colTrnCode.setCellValueFactory(new PropertyValueFactory<>("colTrnCode"));
		colTrnName.setCellValueFactory(new PropertyValueFactory<>("colTrnName"));
		colTrnMobile.setCellValueFactory(new PropertyValueFactory<>("colTrnMobile"));
		for (CmnTrainerDTO t : dto) {
			obserList.add(new TrainerMgtTable(t.getTRAINER_Code(), t.getTRAINER_Name(), t.getTRAINER_Mobile()));
		}

		trnTable.setItems(obserList);
	}
	
	//  set 강사 등록 폼
	public void setTrainerEnrollForm(Parent trainerEnrollForm) {
		this.trainerEnrollForm = trainerEnrollForm;
	}
	
	// get 강사 등록 폼
	public Parent getTrainerEnrollForm() {
		return trainerEnrollForm;
	}
	
	// set 강사 등록 컨트롤러
	public void setTrainerEnrollController(TrainerEnrollController trainerEnrollController) {
		this.trainerEnrollController = trainerEnrollController;
	}
	
	public TrainerEnrollController getTrainerEnrollController() {
		return trainerEnrollController;
	}

	public void settingTrainerEnroll() {
		this.trainerEnrollController.setTrainerEnrollForm(this.trainerEnrollForm);
	}
	
	// 새로고침 버튼 클릭 시
	public void refreshProc() {
		trainerMgtSvc.refreshTable(trainerMgtForm);
	}
	
	// 강사 등록 버튼 클릭 시
	public void trnInsertProc() {
		trainerMgtSvc.trnInsertProc(trainerMgtForm);
	}
	
	// 강사 테이블뷰 셀 클릭 시
	public void trnCellClick() {
		trnTable.setOnMouseClicked((MouseEvent e) -> {
			try {
				TrainerMgtTable tt = trnTable.getSelectionModel().getSelectedItem();
				trainerMgtSvc.trnCellClick(trainerMgtForm, tt.getColTrnCode());
			} catch (NullPointerException e2) {
				CommonService.Msg("강사를 선택해주세요.");
			}
		});
	}
	
	// 강사 수정 버튼 클릭 시
	public void trnUpdateProc() {
		trainerMgtSvc.trnUpdateProc(trainerMgtForm);
	}
	
	
	
	// 강사 삭제 버튼 클릭 시
	public void trnDeleteProc() {
		trainerMgtSvc.trnDeleteProc(trainerMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void trnCloseProc() {
		CommonService.WindowClose(trainerMgtForm);
	}


}
