package admin.trainerMgt;

import java.io.IOException;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class TrainerMgtService {
	private TrainerMgtController trainerMgtController;
	private CmnTrainerDAO dao;

	public void setTrainerMgtController(TrainerMgtController trainerMgtController) {
		this.trainerMgtController = trainerMgtController;
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
	public void trnCellClick(Parent trainerMgtForm, String colTrnCode) {
		dao = new CmnTrainerDAO();
		CmnTrainerDTO dto = dao.SltTrnOne(colTrnCode);

		TextField trnIdTxt = (TextField) trainerMgtForm.lookup("#trnIdTxt");
		TextField trnNameTxt = (TextField) trainerMgtForm.lookup("#trnNameTxt");
		TextField trnPwTxt = (TextField) trainerMgtForm.lookup("#trnPwTxt");
		TextField trnMobileTxt = (TextField) trainerMgtForm.lookup("#trnMobileTxt");
		TextField trnAddrTxt1 = (TextField) trainerMgtForm.lookup("#trnAddrTxt1");
		TextField trnAddrTxt2 = (TextField) trainerMgtForm.lookup("#trnAddrTxt2");
		TextField trnBirthTxt = (TextField) trainerMgtForm.lookup("#trnBirthTxt");
		TextField trnCareerTxt = (TextField) trainerMgtForm.lookup("#trnCareerTxt");
		RadioButton trnMenRadio = (RadioButton) trainerMgtForm.lookup("#trnMenRadio");
		RadioButton trnWomenRadio = (RadioButton) trainerMgtForm.lookup("#trnWomenRadio");
		ToggleGroup group = new ToggleGroup();
		trnMenRadio.setToggleGroup(group);
		trnWomenRadio.setToggleGroup(group);

		trnIdTxt.setText(dto.getTRAINER_ID());
		trnNameTxt.setText(dto.getTRAINER_Name());
		trnPwTxt.setText(dto.getTRAINER_PW());

		String trnMobile = Integer.toString(dto.getTRAINER_Mobile());
		trnMobileTxt.setText(trnMobile);
		String trnBirth = Integer.toString(dto.getTRAINER_Birth());
		trnBirthTxt.setText(trnBirth);
		String trnCarer = Integer.toString(dto.getTRAINER_Career());
		trnCareerTxt.setText(trnCarer);
		
		String[] trnAddr = dto.getTRAINER_Addr().split("/");
		for (int i = 0; i < trnAddr.length; i++) {
			System.out.println(trnAddr[i]);
		}
		if (trnAddr.length == 1) {
			trnAddrTxt1.setText(trnAddr[0]);
			trnAddrTxt2.setText(null);
		} else if (trnAddr.length == 2) {
			trnAddrTxt1.setText(trnAddr[0]);
			trnAddrTxt2.setText(trnAddr[1]);
		} else {
			trnAddrTxt1.setText(null);
			trnAddrTxt2.setText(null);
		}
		
		if(dto.getTRAINER_Gender() != null) {
			if(dto.getTRAINER_Gender().equals("남")) {
				trnMenRadio.setSelected(true);
			}else {
				trnWomenRadio.setSelected(true);
			}
		}else {
			trnMenRadio.setSelected(false);
			trnWomenRadio.setSelected(false);
		}
		
	}


	// 강사 수정
	public void trnUpdateProc(Parent trainerMgtForm) {

	}

	// 강사 삭제
	public void trnDeleteProc(Parent trainerMgtForm) {

	}

}
