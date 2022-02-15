package trn.Welcome;

import java.io.IOException;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import trn.TrnMgt.MgtDAO;
import trn.TrnMgt.MgtDTO;
import trn.TrnMgt.TrnMgtController;
import trn.TrnMgt.TrnMgtService;

public class TrnWelcomeService {
	
	private TrnWelcomeController trnWelcomeController;
	private String trnCode;
	private TrnMgtController trnMgtController;
	private ObservableList<TrnTbVDTO> list = FXCollections.observableArrayList(
			new TrnTbVDTO("hellocode", "hellocode","hellocode"),
			new TrnTbVDTO("hellocode2", "helloname2","hellomems2")
			);
	
	public void setTrnCode(String trnCode) {
		this.trnCode = trnCode;
	}
	
	public void setTrnWelcomeController(TrnWelcomeController trnWelcomeController) {
		this.trnWelcomeController = trnWelcomeController;
	}
	public void programclickProc(Parent welcomeForm) {
	
		TableView<TrnTbVDTO> CurrentProgramTableList = (TableView<TrnTbVDTO>)welcomeForm.lookup("#CurrentProgramTableList");
//		TableColumn<TrnTableView, String> PCodeColumn;
//		TableColumn<TrnTableView, String> PNameColumn;
//		TableColumn<TrnTableView, String> MembersColumn;
		CurrentProgramTableList.setItems(list);
		

	}

	//강사정보페이지
	public void TrnMgtOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/TrnMgt/KG_TRN_FX_Mgt.fxml"));
		Parent trnMgtForm;
		try {
			trnMgtForm = loader.load();
			trnWelcomeController.setTrnMgtController(loader.getController());
			trnWelcomeController.getTrnMgtController().setTrnMgtForm(trnMgtForm);
			trnWelcomeController.getTrnMgtController().setTrnCode(trnCode);
			trnCode = "0";
			//초기 표시 설정
			MgtDTO tmpDTO = new MgtDAO().SelectTrnInfo(trnCode);
			Label IDDis = (Label)trnMgtForm.lookup("#IDDisplay");//아이디표시
//			IDDis.setText(tmpDTO.getTRAINER_ID());
//			TextField NameField = (TextField)trnMgtForm.lookup("#TrnNameField");//기존 이름 표시
//			NameField.setText(tmpDTO.getTRAINER_Name());
//			TextField BirthField = (TextField)trnMgtForm.lookup("#TrnBirthField");//기존 생일 표시
//			BirthField.setText(Integer.toString(tmpDTO.getTRAINER_Birth()));
//			TextField MobileField = (TextField)trnMgtForm.lookup("#TrnMobileField");//기존 전번 표시
//			MobileField.setText(Integer.toString(tmpDTO.getTRAINER_Mobile()));
//			TextField AddrField = (TextField)trnMgtForm.lookup("#TrnAddr1");//기존 주소 표시
//			AddrField.setText(tmpDTO.getTRAINER_Addr());
//			TextField CareerField = (TextField)trnMgtForm.lookup("#TrnCareer");//기존 커리어 표시
//			CareerField.setText(Integer.toString(tmpDTO.getTRAINER_Career()));

			Stage stage = new Stage();
			stage.setScene(new Scene(trnMgtForm));
			stage.setTitle("강사 정보수정 페이지");
			stage.show();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//프로그램 개설 페이지
	public void ExPEnrollOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/ExprogramEnroll/KG_TRN_FX_EXProgramEnroll.fxml"));
		Parent trnExPEnrollFrom;
		try {
			trnExPEnrollFrom= loader.load();
			trnWelcomeController.setTrnExpEnrollController(loader.getController());
			trnWelcomeController.getTrnExpEnrollController().setTrnExpEnrollForm(trnExPEnrollFrom);
//			trnWelcomeController.getTrnExpEnrollController().setTrnCode(trnCode);			
			Stage stage = new Stage();
			stage.setScene(new Scene(trnExPEnrollFrom));
			stage.setTitle("trnExPEnroll");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	//프로그램 관리 페이지
	public void ExPMgtOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/trn/EXProgramMgt/KG_TRN_FX_EXProgramMgt.fxml"));
		Parent trnExPMgtFrom;
		try {
			trnExPMgtFrom = loader.load();
			trnWelcomeController.setTrnExpMgtController(loader.getController());
			trnWelcomeController.getTrnExpMgtController().setTrnExProgramMgtForm(trnExPMgtFrom);
//			trnWelcomeController.getTrnExpMgtController().setTrnCode(trnCode);
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
