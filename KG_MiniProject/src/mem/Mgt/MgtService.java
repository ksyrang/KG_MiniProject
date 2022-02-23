package mem.Mgt;

import java.io.IOException;
import java.util.ArrayList;

import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MgtService {

	private MgtController mgtcontroller;
	private boolean MBCheckFlag;

	public void setMgtController(MgtController mgtcontroller) {
		this.mgtcontroller = mgtcontroller;
	}

	public void MemModifyProc(Parent memMgtForm, String membCode) {
		if(!MBCheckFlag) {
			CommonService.Msg("전화번호 중복체크를 확인 하세요");
			return;
		}
		PasswordField PWField = (PasswordField) memMgtForm.lookup("#MemPWField");
		PasswordField PWCField = (PasswordField) memMgtForm.lookup("#MemPWCField");
		TextField NameField = (TextField) memMgtForm.lookup("#MemNameField");// 변경 이름
		TextField BirthField = (TextField) memMgtForm.lookup("#MemBirthField");// 변경 생일
		TextField MobileField = (TextField) memMgtForm.lookup("#MemMobileField");// 변경 전번
		RadioButton MaleRtn = (RadioButton) memMgtForm.lookup("#MaleRabtn");// 남자버튼
		RadioButton FeMaleRtn = (RadioButton) memMgtForm.lookup("#FeMaleRabtn");// 여자버튼
		TextField Addr1Field = (TextField) memMgtForm.lookup("#MemAddr1");// 변경 주소
		TextField Addr2Field = (TextField) memMgtForm.lookup("#MemAddr2");// 변경 주소

		// 입력 사항의 형식 확인
		try {
			int tmpMB = Integer.parseInt(MobileField.getText());
			int tmpBD = Integer.parseInt(BirthField.getText());
		} catch (NumberFormatException e) {
			CommonService.Msg("숫자만 입력 해주세요.");
			return;
		}
		// 입력 사항 최소 길이 확인
		if (MobileField.getText().length() < 11) {
			CommonService.Msg("11자리의 전화번호를 입력 해주세요.");
			return;
		} else if (BirthField.getText().length() < 8) {
			CommonService.Msg("8자리의 생년월일를 입력 해주세요.");
			return;
		}
		
		MgtDAO tmpDAO = new MgtDAO();
		MgtDTO tmpDto = new MgtDTO(tmpDAO.selectCode(membCode));

		// 변경사항 업데이트
		if (!PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {// 정상 입력 조건
			if (PWField.getText().equals(PWCField.getText())) {// 입력 값이 동일하다는 조건
				tmpDto.setMEM_Name(NameField.getText());
				tmpDto.setMEM_PW(PWField.getText());
				if (MaleRtn.isSelected()) {
					tmpDto.setMEM_Gender("남");
				}
				else {
					tmpDto.setMEM_Gender("여");
				}
				tmpDto.setMEM_Birth(Integer.parseInt(BirthField.getText()));
				tmpDto.setMEM_Mobile(Integer.parseInt(MobileField.getText()));
				if(!Addr2Field.getText().isEmpty()) {
					tmpDto.setMEM_Addr(Addr1Field.getText() + "/" + Addr2Field.getText());	
				}else {
					tmpDto.setMEM_Addr(Addr1Field.getText()+"/");	
				}
				
				int result = tmpDAO.UptMem(tmpDto);
				if (result == 1) {
					CommonService.Msg("수정완료");
					CommonService.WindowClose(memMgtForm);
				} else
					CommonService.Msg("이상 발생");
			} else {
				CommonService.Msg("비밀번호가 서로 다릅니다");
				PWField.clear();
				PWCField.clear();
				PWField.requestFocus();
			}
		} else if (PWField.getText().isEmpty() && !PWCField.getText().isEmpty()) {
			CommonService.Msg("비밀번호칸을 채워주세요");
		} else if (!PWField.getText().isEmpty() && PWCField.getText().isEmpty()) {
			CommonService.Msg("비밀번호비교칸을 채워주세요");
		} else {
			CommonService.Msg("비밀번호칸들을 채워 주세요");
		}
	}
	
	public void MBCheckProc(Parent memMgtForm, String formerMobileNum) {
		TextField MobileField = (TextField) memMgtForm.lookup("#MemMobileField");// 변경 전번
		try {
			int tmpMB = Integer.parseInt(MobileField.getText());
		} catch (NumberFormatException e) {
			CommonService.Msg("숫자만 입력 해주세요.");
			return;
		}
		if (MobileField.getText().length() < 11) {
			CommonService.Msg("11자리의 전화번호를 입력 해주세요.");
			return;
		}
		//전화 번호 중복 확인
		if(!formerMobileNum.equals(MobileField.getText())) {
			ArrayList<Integer> checkMB = new CmnMemDAO().SltMBAll();
			for (int E : checkMB) {
				if (E == Integer.parseInt(MobileField.getText())) {
					CommonService.Msg("중복된 번호 입니다.");
					MobileField.requestFocus();
					MBCheckFlag = false;
					return;
				}
			}
			CommonService.Msg("중복되지 않는 번호 입니다.");
			MBCheckFlag = true;	
			return;
		}
		CommonService.Msg("정상 입력 입니다.");
		MBCheckFlag = true;		
	}
	

	public void BackMgtProc(Parent memMgtForm) {
		CommonService.WindowClose(memMgtForm);
	}

	//탈퇴 버튼 클릭 시
	public void MemDeleteOpen(Parent MemMgtForm, String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Delete/KG_MEM_FX_Delete.fxml"));
		try {
			Parent DltForm = loader.load();
			mgtcontroller.setDeleteController(loader.getController());
			mgtcontroller.getDeleteController().setDeleteForm(DltForm);
			mgtcontroller.getDeleteController().setMembCode(membCode);
			System.out.println("MemMgtForm :" + MemMgtForm);
			mgtcontroller.getDeleteController().setMemMgtFrom(MemMgtForm);
			System.out.println("mgtcontroller.getMemWlcForm() : "+mgtcontroller.getMemWlcForm());
			mgtcontroller.getDeleteController().setMemWelcomeForm(mgtcontroller.getMemWlcForm());

			Scene scene = new Scene(DltForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("memberDelete");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
