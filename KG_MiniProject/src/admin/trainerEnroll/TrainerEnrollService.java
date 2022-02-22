package admin.trainerEnroll;

import java.util.ArrayList;

import admin.trainerMgt.TrainerMgtController;
import admin.trainerMgt.TrainerMgtTable;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
public class TrainerEnrollService {
	
	private TrainerEnrollController TrainerEnrollController;
	private TrainerMgtController TrainerMgtController;
	
	public void setTrainerEnrollController(TrainerEnrollController TrainerEnrollController) {
		this.TrainerEnrollController = TrainerEnrollController;
	}
	
		CmnTrainerDAO dao;
	
	// 중복 체크
	public void overlapProc(Parent trainerEnrollForm) {
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		String trnId = trnIdTxt.getText();
		if(trnId.isEmpty()) {
			CommonService.Msg("ID를 입력해주세요.");
		} else {
			dao = new CmnTrainerDAO();
			
			if(dao.SltTrnId(trnId) == null) {
				CommonService.Msg(trnId + " 은(는) 사용 가능한 ID입니다.");
			}else {
				CommonService.Msg(trnId + " 은(는) 이미 사용하고 있는 ID입니다.");
				trnIdTxt.clear();
				trnIdTxt.requestFocus();
			}
		}
	}
	
	// 강사 등록
	public void trnEnrollProc(Parent trainerEnrollForm) {
		
		TextField trnIdTxt = (TextField) trainerEnrollForm.lookup("#trnIdTxt");
		PasswordField trnPwTxt = (PasswordField) trainerEnrollForm.lookup("#trnPwTxt");
		PasswordField trnPwComfrimTxt = (PasswordField) trainerEnrollForm.lookup("#trnPwComfrimTxt");
		TextField trnNameTxt = (TextField) trainerEnrollForm.lookup("#trnNameTxt");
		TextField trnBirthTxt = (TextField) trainerEnrollForm.lookup("#trnBirthTxt");
		TextField trnMobileTxt = (TextField) trainerEnrollForm.lookup("#trnMobileTxt");
		TextField trnAddrTxt1 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt1");
		TextField trnAddrTxt2 = (TextField) trainerEnrollForm.lookup("#trnAddrTxt2");
		TextField trnCareerTxt = (TextField) trainerEnrollForm.lookup("#trnCareerTxt");
		RadioButton trnMenRadio = (RadioButton) trainerEnrollForm.lookup("#trnMenRadio");
		RadioButton trnWomenRadio = (RadioButton) trainerEnrollForm.lookup("#trnWomenRadio");
//		ToggleGroup group = new ToggleGroup();
//		trnMenRadio.setToggleGroup(group);
//		trnWomenRadio.setToggleGroup(group);
		
		String trnId = trnIdTxt.getText();
		String trnPw = trnPwTxt.getText();
		String trnPwComfrim = trnPwComfrimTxt.getText();
		String trnName = trnNameTxt.getText();
		String trnAaddr = trnAddrTxt1.getText() + "/" + trnAddrTxt2.getText();
		
		String birth = null;
		int trnBirth = 1;
		if (trnBirthTxt.getText().isEmpty()) {
			trnBirth = 0;
		} else {
			try {
				trnBirth = Integer.parseInt(trnBirthTxt.getText());
				birth = trnBirthTxt.getText();
			} catch (NumberFormatException e) {
				CommonService.Msg("생년월일을 정확하게 입력해주세요.");
				trnBirthTxt.requestFocus();
			}
		}

		String mobile = null;
		int trnMobile = 1;
		if (trnMobileTxt.getText().isEmpty()) {
			trnMobile = 0;
		} else {
			try {
				trnMobile = Integer.parseInt(trnMobileTxt.getText());
				mobile = trnMobileTxt.getText();
			} catch (NumberFormatException e) {
				CommonService.Msg("전화번호을 정확하게 입력해주세요.");
				trnMobileTxt.requestFocus();
			}
			
		}
		
		int trnCareer = 1;
		if (trnCareerTxt.getText().isEmpty()) {
			trnCareer = 0;
		} else {
			try {
				trnCareer = Integer.parseInt(trnCareerTxt.getText());
			} catch (Exception e) {
				CommonService.Msg("경력을 정확하게 입력해주세요.");
				trnCareerTxt.requestFocus();
			}
			
		}
		
		String trnCode = "Trn_" + trnId;
		String trnGender;
		if(trnMenRadio.isSelected()) {
			trnGender = "남";
		} else if (trnWomenRadio.isSelected()){
			trnGender = "여";
		} else {
			trnGender = null;
		}
		
		
		
		try {
			if (trnId.isEmpty() || trnPw.isEmpty() || trnPwComfrim.isEmpty() || trnName.isEmpty()) {
				CommonService.Msg(" * 필수 입력란을 입력해주세요.");
			} else {
				if (trnBirth == 0 || birth.length() == 8) {
					if (trnMobile == 0 || mobile.length() == 11) {
						if (dao.SltTrnId(trnId) == null) {
							if (trnPw.equals(trnPwComfrim)) {
								dao = new CmnTrainerDAO();
								CmnTrainerDTO dto = new CmnTrainerDTO(trnCode, trnName, trnId, trnPw, trnGender,
										trnBirth, trnMobile, trnCareer, trnAaddr);
								if (dao.IstTrn(dto) == 1) {
									CommonService.Msg(trnId + "강사 등록되었습니다.");
									CommonService.WindowClose(trainerEnrollForm);
									// Table View Refresh
									Parent TMgtForm = TrainerEnrollController.getTrainerMgtForm();
									ObservableList<TrainerMgtTable> tableView = FXCollections.observableArrayList();
									TableView<TrainerMgtTable> newTable = (TableView<TrainerMgtTable>) TMgtForm.lookup("#trnTable");
									newTable.getItems().clear();
									try {
										ObservableList<CmnTrainerDTO> list = dao.OLSltTrnAll();
										for (CmnTrainerDTO t : list) {
											tableView.add(new TrainerMgtTable(t.getTRAINER_Code(), t.getTRAINER_Name(), t.getTRAINER_Mobile()));
										}
										newTable.setItems(tableView);
									} catch (NullPointerException e) {
										CommonService.Msg("새로고침중 이상 발생");
									}
								} else {
									CommonService.Msg("강사 등록 실패");
								}
							} else {
								CommonService.Msg("비밀번호가 다릅니다.");
								trnPwComfrimTxt.clear();
								trnPwComfrimTxt.requestFocus();
							}
						} else {
							CommonService.Msg("중복 체크를 해주세요.");
						}
					} else {
						CommonService.Msg("전화번호를 정확하게 입력해주세요.");
						trnMobileTxt.requestFocus();
					}
				} else {
					CommonService.Msg("생년월일을 정확하게 입력해주세요.");
					trnBirthTxt.requestFocus();
				}
			}
		} catch (NullPointerException e) {
			CommonService.Msg("중복 체크를 해주세요.");
		}
	
	}
	
}
