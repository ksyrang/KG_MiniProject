package mem.Welcome;

import java.io.IOException;

import common.CommonService;
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
   
   public void setMEM_WelcomeController(MEM_WelcomeController memWelcomeController) {
      this.memWelcomeController = memWelcomeController;
   }
   
   public void healthProgramBuyingProc() {
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/HelthProgramBuying/KG_MEM_FX_HealthProgramBuying.fxml"));
	   Parent healthProgramBuyingForm;
	   try {
	     healthProgramBuyingForm = loader.load();
	     memWelcomeController.setHealthProgramBuyingForm(healthProgramBuyingForm);
	     memWelcomeController.setHealthPrmBuyingController(loader.getController());
	     memWelcomeController.getHealthPrmBuyingController().setHealthPrmBuyingForm(healthProgramBuyingForm);
	     memWelcomeController.getHealthPrmBuyingController().setMembCode(memWelcomeController.getMembCode());
	     ComboBox<String> combo = (ComboBox<String>)healthProgramBuyingForm.lookup("#memshipComboBox");
	     combo.setValue("기본");
	//         if(combo != null) {
	//            combo.getItems().addAll("전체보기", "승인여부");
	//         }
	 
		Scene scene = new Scene(healthProgramBuyingForm);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("MEM_HealthProgramBuyingForm");
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
   }
   //ex프로그램 버튼 클릭 시
   public void exProgramBuyingOpen(String membCode,  Parent exProgramBuyingForm ) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/EXProgramBuying/KG_MEM_FX_EXProgramBuying.fxml"));
      
      try {
        exProgramBuyingForm = loader.load();
         memWelcomeController.setExProgramBuyingForm(exProgramBuyingForm);
         memWelcomeController.setExPrmBuyingController(loader.getController());
         memWelcomeController.getExPrmBuyingController().setExProgramBuyingForm(exProgramBuyingForm);
         memWelcomeController.getExPrmBuyingController().setMembCode(memWelcomeController.getMembCode());

         //상단 이름
         Label titleUserName = (Label)exProgramBuyingForm.lookup("#TitleMemNameLabel");
         MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
         titleUserName.setText(tmpMemDto.getMEM_Name()+" 회원님");
         
         
         
         Scene scene = new Scene(exProgramBuyingForm);
         Stage primaryStage = new Stage();
         primaryStage.setTitle("MEM_EXProgramBuyingForm");
         primaryStage.setScene(scene);
         primaryStage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   //회원정보페이지
   public void memMgtOpen(String membCode) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Mgt/KG_MEM_FX_Mgt.fxml"));
      Parent memMgtForm;
      try {
         memMgtForm = loader.load();
         memWelcomeController.setMgtController(loader.getController());
         memWelcomeController.getMgtController().setMemberMgtForm(memMgtForm);
         memWelcomeController.getMgtController().setMembCode(membCode);
         
      
         //회원 정보 get
         //title sector set
         Label titleUserName = (Label)memMgtForm.lookup("#TitleMemNameLabel");
         MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
         titleUserName.setText(tmpMemDto.getMEM_Name()+" 회원님");
         //초기 표시 설정
         TextField IDField = (TextField)memMgtForm.lookup("#MemIDField");//기존 아이디 표시
         TextField NameField = (TextField)memMgtForm.lookup("#MemNameField");//기존 이름 표시
         PasswordField PWField = (PasswordField)memMgtForm.lookup("#MemPWField");//기존 비밀번호 표시
         PasswordField PWCField = (PasswordField)memMgtForm.lookup("#MemPWCField");//기존 비밀번호확인 표시
         TextField BirthField = (TextField)memMgtForm.lookup("#MemBirthField");//기존 생일 표시
         TextField MobileField = (TextField)memMgtForm.lookup("#MemMobileField");//기존 전번 표시
         RadioButton maleBtn = (RadioButton)memMgtForm.lookup("#MaleRabtn");//기존 남성버튼
         RadioButton FeMaleBtn = (RadioButton)memMgtForm.lookup("#FeMaleRabtn");//기존 여자버튼
         TextField Addr1Field = (TextField)memMgtForm.lookup("#MemAddr1");//기존 주소 표시
         TextField Addr2Field = (TextField)memMgtForm.lookup("#MemAddr2");//기존 주소 표시
         
      
         //ID Sector
         IDField.setText(tmpMemDto.getMEM_ID());
         IDField.setEditable(false);//false:입력 불가
         //Name Sector         
         NameField.setText(tmpMemDto.getMEM_Name());
         //PW Sector :초기 미표시
         //Birth Sector
         BirthField.setText(Integer.toString(tmpMemDto.getMEM_Birth()));
         //Mobile Sector
         MobileField.setText(Integer.toString(tmpMemDto.getMEM_Mobile()));
         //Gender Sector
         ToggleGroup group = new ToggleGroup();
         maleBtn.setToggleGroup(group);
         FeMaleBtn.setToggleGroup(group);
         if(tmpMemDto.getMEM_Gender() != null) {
            if(tmpMemDto.getMEM_Gender().equals("남성")) maleBtn.setSelected(true);
            else if(tmpMemDto.getMEM_Gender().equals("여성")) FeMaleBtn.setSelected(true);
         } else {
            maleBtn.setSelected(false);
            FeMaleBtn.setSelected(false);
         }
         //Addr Sector
         Addr1Field.setText(tmpMemDto.getMEM_Addr());
         Addr2Field.setText(tmpMemDto.getMEM_Addr());
         
      
         
         Stage stage = new Stage();
         stage.setScene(new Scene(memMgtForm));
         stage.setTitle("memMgt");
         stage.show();      
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   
   public void logoutProc(Parent memWelcomeForm) {
      CommonService.Msg("로그아웃 되셨습니다.");
      CommonService.WindowClose(memWelcomeForm);
      
      
   }

   public void cancelProc(Parent memWelcomeForm) {
	    CommonService.WindowClose(memWelcomeForm);
      
   }

   
}