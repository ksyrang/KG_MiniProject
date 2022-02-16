package admin.trainerMgt;

import java.io.IOException;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TrainerMgtService {
	private TrainerMgtController trainerMgtController;
	
	@FXML private TableView<TrainerMgtTable> trnTable;
	@FXML private TableColumn<TrainerMgtTable, String> colTrnCode;
	@FXML private TableColumn<TrainerMgtTable, String> colTrnName;
	@FXML private TableColumn<TrainerMgtTable, Integer> colTrnMobile;
	
	ObservableList<TrainerMgtTable> obserList;
	
	public void setTrainerMgtController(TrainerMgtController trainerMgtController) {
		this.trainerMgtController = trainerMgtController;
		
		// trnTable
		CmnTrainerDAO dao = new CmnTrainerDAO();
		ObservableList<CmnTrainerDTO> dto = (ObservableList<CmnTrainerDTO>) dao.SltTrnAll();
		
		obserList = FXCollections.observableArrayList();
		
		colTrnCode.setCellValueFactory(new PropertyValueFactory<>("colTrnCode"));
		colTrnName.setCellValueFactory(new PropertyValueFactory<>("colTrnName"));
		colTrnMobile.setCellValueFactory(new PropertyValueFactory<>("colTrnMobile"));
	}
	
	// 강사 등록
	public void trnInsertProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerEnroll/KG_ADM_FX_TrainerEnroll.fxml"));
		Parent trainerEnrollForm;
		try {
			trainerEnrollForm = loader.load();
			trainerMgtController.setTrainerEnrollController(loader.getController());
			trainerMgtController.setTrainerEnrollForm(trainerEnrollForm);
			trainerMgtController.settingTrainerEnroll();
			
			Scene scene = new Scene(trainerEnrollForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("trainerEnrollForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 강사 테이블뷰 셀
	public void trnCellClick(Parent trainerMgtForm) {
		
	}
	
	// 강사 수정
	public void trnUpdateProc(Parent trainerMgtForm) {
		
	}
	
	// 강사 삭제
	public void trnDeleteProc(Parent trainerMgtForm) {
		
	}



}
