package admin.trainerMgt;

import java.io.IOException;

import org.omg.CORBA.COMM_FAILURE;

import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
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
	public void trnInsertProc(Parent trainerMgtForm) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/trainerEnroll/KG_ADM_FX_TrainerEnroll.fxml"));
		Parent trainerEnrollForm;
		try {
			trainerEnrollForm = loader.load();
			trainerMgtController.setTrainerEnrollController(loader.getController());
			trainerMgtController.setTrainerEnrollForm(trainerEnrollForm);
			trainerMgtController.getTrainerEnrollController().setTrainerMgtForm(trainerMgtForm);
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

		trnIdTxt.setText(dto.getTRAINER_ID());
		trnNameTxt.setText(dto.getTRAINER_Name());
		trnPwTxt.setText(dto.getTRAINER_PW());

		if(dto.getTRAINER_Mobile() == 0) {
			trnMobileTxt.setText("");
		}else {
			String trnMobile = Integer.toString(dto.getTRAINER_Mobile());
			trnMobileTxt.setText("0" + trnMobile);
		}
		
		if(dto.getTRAINER_Birth() == 0) {
			trnBirthTxt.setText("");
		}else {
			String trnBirth = Integer.toString(dto.getTRAINER_Birth());
			trnBirthTxt.setText(trnBirth);
		}
		
		if(dto.getTRAINER_Career() == 0) {
			trnCareerTxt.setText("");
		}else {
			String trnCarer = Integer.toString(dto.getTRAINER_Career());
			trnCareerTxt.setText(trnCarer);
		}
		
		if(dto.getTRAINER_Addr() != null) {
			String[] trnAddr = dto.getTRAINER_Addr().split("/");
			if (trnAddr.length == 1) {
				trnAddrTxt1.setText(trnAddr[0]);
				trnAddrTxt2.setText("");
			} else if (trnAddr.length == 2) {
				trnAddrTxt1.setText(trnAddr[0]);
				trnAddrTxt2.setText(trnAddr[1]);
			} else {
				trnAddrTxt1.setText("");
				trnAddrTxt2.setText("");
			}
		} else {
			trnAddrTxt1.setText("");
			trnAddrTxt2.setText("");
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
		
		String trnId = trnIdTxt.getText();
		String trnName = trnNameTxt.getText();
		String trnPw = trnPwTxt.getText();

		String mobile = null;
		int trnMobile;
		if(trnMobileTxt.getText().isEmpty()) {
			trnMobile = 0;
		} else {
			trnMobile = Integer.parseInt(trnMobileTxt.getText());
			mobile =trnMobileTxt.getText();
		}
		
		String trnAddr1 = trnAddrTxt1.getText();
		String trnAddr2 = trnAddrTxt2.getText();
		if (trnAddrTxt1.getText().isEmpty()) {
			trnAddr1 = "";
		} else if (trnAddrTxt2.getText().isEmpty()) {
			trnAddr2 ="";
		}
		
		String trnAddr = trnAddr1 + "/" + trnAddr2;
		
		String birth = null;
		int trnBirth;
		if(trnBirthTxt.getText().isEmpty()) {
			trnBirth = 0;
		} else {
			trnBirth = Integer.parseInt(trnBirthTxt.getText());
			birth = trnBirthTxt.getText();
		}
		
		int trnCareer;
		if (trnCareerTxt.getText().isEmpty()) {
			trnCareer = 0;
		} else {
			trnCareer = Integer.parseInt(trnCareerTxt.getText());
		}
		
		String trnGender;
		if(trnMenRadio.isSelected()) {
			trnGender = "남";
		} else if (trnWomenRadio.isSelected()){
			trnGender = "여";
		} else {
			trnGender = null;
		}
	
		
		try {
			if (trnName.isEmpty() || trnPw.isEmpty()) {
				CommonService.Msg(" * 필수입력란을 입력해주세요.");
			} else {
				if(trnMobile == 0 || mobile.length() == 11) {
					if(trnBirth == 0 || birth.length() == 8) {
						CmnTrainerDTO dto = dao.SltTrnId(trnId);
						if (dto != null) {
							dto.setTRAINER_ID(trnId);
							dto.setTRAINER_Name(trnName);
							dto.setTRAINER_PW(trnPw);
							dto.setTRAINER_Gender(trnGender);
							dto.setTRAINER_Birth(trnBirth);
							dto.setTRAINER_Mobile(trnMobile);
							dto.setTRAINER_Career(trnCareer);
							dto.setTRAINER_Addr(trnAddr);
							if (dao.UptTrnId(dto) == 1) {
								CommonService.Msg(trnId + " 강사 수정 완료");
								refreshTable(trainerMgtForm);
							} else {
								CommonService.Msg(trnId + " 강사 수정 실패");
							}
						} else {
							CommonService.Msg("강사를 선택해주세요.");
						}
					}else {
						CommonService.Msg("생년월일을 정확하게 입력해주세요.");
						trnBirthTxt.requestFocus();
					}
				}else {
					CommonService.Msg("전화번호를 정확하게 입력주세요.");
					trnMobileTxt.requestFocus();
				}
				
			}

		} catch (NullPointerException e) {
			CommonService.Msg("강사를 선택해주세요.");
		}
	}

	// 강사 삭제
	public void trnDeleteProc(Parent trainerMgtForm) {
		TextField trnIdTxt = (TextField) trainerMgtForm.lookup("#trnIdTxt");
		String trnId = trnIdTxt.getText();
		
		try {
			CmnTrainerDTO dto = dao.SltTrnId(trnId);
			if(dto != null) {
				if(dao.DelTrnId(trnId) == 1) {
					CommonService.Msg(trnId + " 강사 삭제 완료");
					refreshTable(trainerMgtForm);
				}else {
					CommonService.Msg(trnId + " 강사 삭제 실패");
				}
			}else {
				CommonService.Msg(" 강사를 선택해주세요.");
			}
		} catch (NullPointerException e) {
			CommonService.Msg(" 강사를 선택해주세요.");
		}
	}

	// 테이블 뷰 불러오기
	public void refreshTable(Parent trainerMgtForm) {
		ObservableList<TrainerMgtTable> tableView = FXCollections.observableArrayList();
		TableView<TrainerMgtTable> newTable = (TableView<TrainerMgtTable>) trainerMgtForm.lookup("#trnTable");
		try {
			ObservableList<CmnTrainerDTO> list = dao.OLSltTrnAll();
			for(CmnTrainerDTO t : list) {
				tableView.add(new TrainerMgtTable(t.getTRAINER_Code(), t.getTRAINER_Name(), t.getTRAINER_Mobile()));
			}
			newTable.setItems(tableView);
			removeTxt(trainerMgtForm);
		} catch (NullPointerException e) {
			CommonService.Msg("테이블 클릭 후 새로고침해주세요.");
		}
	}
	
	// 텍스트 필드 비우기
	public void removeTxt(Parent trainerMgtForm) {
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
		
		trnIdTxt.clear();
		trnNameTxt.clear();
		trnPwTxt.clear();
		trnMobileTxt.clear();
		trnAddrTxt1.clear();
		trnAddrTxt2.clear();
		trnBirthTxt.clear();
		trnCareerTxt.clear();
		trnMenRadio.setSelected(false);
		trnWomenRadio.setSelected(false);
	}

}
