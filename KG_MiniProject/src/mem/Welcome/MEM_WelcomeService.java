package mem.Welcome;

import java.io.IOException;

import Main.login.LoginController;
import Main.main.Controller;
import common.CommonService;
import common.LogOut;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import mem.Mgt.MgtDAO;
import mem.Mgt.MgtDTO;

public class MEM_WelcomeService {
	private MEM_WelcomeController memWelcomeController;
	private MEM_WelcomeMgtTable selectTable;
	private String id;

	public void setMEM_WelcomeController(MEM_WelcomeController memWelcomeController) {
		this.memWelcomeController = memWelcomeController;
	}
	//헬스권 클릭 시
	public void healthProgramBuyingProc(String membCode, Parent healthProgramBuyingForm2) {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/mem/HelthProgramBuying/KG_MEM_FX_HealthProgramBuying.fxml"));
		Parent healthProgramBuyingForm;
		try {
			healthProgramBuyingForm = loader.load();
			memWelcomeController.setHealthProgramBuyingForm(healthProgramBuyingForm);
			memWelcomeController.setHealthPrmBuyingController(loader.getController());
			memWelcomeController.getHealthPrmBuyingController().setHealthPrmBuyingForm(healthProgramBuyingForm);
			memWelcomeController.getHealthPrmBuyingController().setMembCode(memWelcomeController.getMembCode());
			memWelcomeController.getHealthPrmBuyingController().setWelcomForm(memWelcomeController.getMemWelcomeForm());
			
			
			// 상단 이름
			Label titleUserName = (Label) healthProgramBuyingForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			//System.out.println(titleUserName);
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");
			
			ComboBox<String> combo = (ComboBox<String>) healthProgramBuyingForm.lookup("#memshipComboBox");
			combo.setValue("0");

			Scene scene = new Scene(healthProgramBuyingForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("MEM_HealthProgramBuyingForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ex프로그램 버튼 클릭 시
	public void exProgramBuyingOpen(String membCode, Parent exProgramBuyingForm) {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/mem/EXProgramBuying/KG_MEM_FX_EXProgramMgt.fxml"));

		try {
			exProgramBuyingForm = loader.load();
			memWelcomeController.setExProgramBuyingForm(exProgramBuyingForm);
			memWelcomeController.setExPrmBuyingController(loader.getController());
			memWelcomeController.getExPrmBuyingController().setExProgramBuyingForm(exProgramBuyingForm);
			memWelcomeController.getExPrmBuyingController().setMembCode(memWelcomeController.getMembCode());
			memWelcomeController.getExPrmBuyingController().setWelcomForm(memWelcomeController.getMemWelcomeForm());
			

			// 상단 이름
			Label titleUserName = (Label) exProgramBuyingForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");

			Scene scene = new Scene(exProgramBuyingForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("MEM_EXProgramBuyingForm");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 회원정보페이지
	public void memMgtOpen(Parent memWelcomeForm, String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Mgt/KG_MEM_FX_Mgt.fxml"));
		Parent memMgtForm;
		try {
			memMgtForm = loader.load();
			memWelcomeController.setMemMgtForm(memMgtForm);
			memWelcomeController.setMgtController(loader.getController());
			memWelcomeController.getMgtController().setMemberMgtForm(memWelcomeForm);
			memWelcomeController.getMgtController().setMembCode(membCode);
			memWelcomeController.getMgtController().setMemWelcomeForm(memWelcomeController.getMemWelcomeForm());

			// 회원 정보 get
			// title sector set
			Label titleUserName = (Label) memMgtForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");
			// 초기 표시 설정
			TextField IDField = (TextField) memMgtForm.lookup("#MemIDField");// 기존 아이디 표시
			TextField NameField = (TextField) memMgtForm.lookup("#MemNameField");// 기존 이름 표시
			PasswordField PWField = (PasswordField) memMgtForm.lookup("#MemPWField");// 기존 비밀번호 표시
			PasswordField PWCField = (PasswordField) memMgtForm.lookup("#MemPWCField");// 기존 비밀번호확인 표시
			TextField BirthField = (TextField) memMgtForm.lookup("#MemBirthField");// 기존 생일 표시
			TextField MobileField = (TextField) memMgtForm.lookup("#MemMobileField");// 기존 전번 표시
			RadioButton maleBtn = (RadioButton) memMgtForm.lookup("#MaleRabtn");// 기존 남성버튼
			RadioButton FeMaleBtn = (RadioButton) memMgtForm.lookup("#FeMaleRabtn");// 기존 여자버튼
			TextField Addr1Field = (TextField) memMgtForm.lookup("#MemAddr1");// 기존 주소 표시
			TextField Addr2Field = (TextField) memMgtForm.lookup("#MemAddr2");// 기존 주소 표시
			//이전 전화 번호 기억
			memWelcomeController.getMgtController().setFormerMobileNum("0"+Integer.toString(tmpMemDto.getMEM_Mobile()));
			// ID Sector
			IDField.setText(tmpMemDto.getMEM_ID());
			IDField.setEditable(false);// false:입력 불가
			// Name Sector
			NameField.setText(tmpMemDto.getMEM_Name());
			// PW Sector :초기 미표시
			// Birth Sector
			BirthField.setText(Integer.toString(tmpMemDto.getMEM_Birth()));
			// Mobile Sector
			MobileField.setText("0"+Integer.toString(tmpMemDto.getMEM_Mobile()));
			// Gender Sector
			ToggleGroup group = new ToggleGroup();
			maleBtn.setToggleGroup(group);
			FeMaleBtn.setToggleGroup(group);
			if (tmpMemDto.getMEM_Gender() != null) {
				if (tmpMemDto.getMEM_Gender().equals("남성"))
					maleBtn.setSelected(true);
				else if (tmpMemDto.getMEM_Gender().equals("여성"))
					FeMaleBtn.setSelected(true);
			} else {
				maleBtn.setSelected(false);
				FeMaleBtn.setSelected(false);
			}
			// Addr Sector
			String[] tmpAddr = tmpMemDto.getMEM_Addr().split("/");
			if(!tmpAddr[1].isEmpty()) {
				Addr1Field.setText(tmpAddr[0]);
				Addr2Field.setText(tmpAddr[1]);
			}else {
				Addr1Field.setText(tmpMemDto.getMEM_Addr());
			}
			

			Stage stage = new Stage();
			stage.setScene(new Scene(memMgtForm));
			stage.setTitle("memMgt");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void LogOut(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/main/KG_COM_FX_Main.fxml"));
		try {
			Parent mainForm = loader.load();
			Controller controller = new Controller();
			controller.setLoginController(loader.getController());
//			controller.getLoginController().setmainForm(mainForm);
			LoginController loginController = controller.getLoginController();
			loginController.setmainForm(mainForm);
			controller.setLogOut(new LogOut());
			
			Stage stage = new Stage();
			Scene scene = new Scene(mainForm);
			
			stage.setScene(scene);
			stage.setTitle("new login");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// selectTable 세팅

	public void setSelectTable(MEM_WelcomeMgtTable selectTable) {
		this.selectTable = selectTable;

	}

	

	public void cancelProc(Parent memWelcomeForm) {
		CommonService.WindowClose(memWelcomeForm);

	}

	public void setId(String mem_ID) {
		this.id = mem_ID;

	}

}