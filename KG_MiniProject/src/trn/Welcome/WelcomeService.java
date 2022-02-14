package trn.Welcome;

import java.io.IOException;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trn.TrnMgt.MgtDTO;
import trn.TrnMgt.TrnMgtController;
import trn.TrnMgt.TrnMgtService;

public class WelcomeService {

	private TrnController trncontroller;
	
	
	private TrnMgtService trnMgtService;
	private String trnCode;
	private Parent WelcomeForm;
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	
	public void setTrnController(TrnController trncontroller) {
		this.trncontroller = trncontroller;
	}
	
	//강사정보페이지
	public void TrnMgtOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/TrnMgt/KG_TRN_FX_Mgt.fxml"));
		Parent trnMgtForm;
		try {
			trnMgtForm = loader.load();
			trncontroller.setTrnMgtController(loader.getController());
			trncontroller.getTrnMgtController().setTrnMgtForm(trnMgtForm);
			trncontroller.getTrnMgtController().setTrnCode(trnCode);
			
			//초기 표시 설정
//			Label IDDis = (Label)trnMgtForm.lookup("#IDDisplay");//아이디표시
//			IDDis.setText(trnMgtService.getTrnInfo(trnCode).getTRAINER_ID());
//			TextField NameField = (TextField)trnMgtForm.lookup("#TrnNameField");//기존 이름 표시
//			NameField.setText(trnMgtService.getTrnInfo(trnCode).getTRAINER_Name());
//			TextField BirthField = (TextField)trnMgtForm.lookup("#TrnBirthField");//기존 생일 표시
//			BirthField.setText(Integer.toString(trnMgtService.getTrnInfo(trnCode).getTRAINER_Birth()));
//			TextField MobileField = (TextField)trnMgtForm.lookup("#TrnMobileField");//기존 전번 표시
//			MobileField.setText(Integer.toString(trnMgtService.getTrnInfo(trnCode).getTRAINER_Mobile()));
//			TextField AddrField = (TextField)trnMgtForm.lookup("#TrnAddr1");//기존 주소 표시
//			AddrField.setText(trnMgtService.getTrnInfo(trnCode).getTRAINER_Addr());
//			TextField CareerField = (TextField)trnMgtForm.lookup("#TrnCareer");//기존 커리어 표시
//			CareerField.setText(Integer.toString(trnMgtService.getTrnInfo(trnCode).getTRAINER_Career()));

			Stage stage = new Stage();
			stage.setScene(new Scene(trnMgtForm));
			stage.setTitle("강사 정보수정 페이지");
			stage.show();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void ExPEnrollOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPEnrollFrom;
		try {
			trnExPEnrollFrom= loader.load();
			trncontroller.setTrnExpEnrollController(loader.getController());
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPEnrollFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	//
	public void ExPMgtOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPMgtFrom;
		try {
			trnExPMgtFrom = loader.load();
			trncontroller.setTrnExpMgtController(loader.getController());
			
			
			
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPMgtFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}
	
	
	
	public void backClose(Parent back) {
		CommonService.WindowClose(back);
	}
	

	


}
