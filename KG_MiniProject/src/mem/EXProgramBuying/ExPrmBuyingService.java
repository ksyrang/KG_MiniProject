package mem.EXProgramBuying;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import mem.Mgt.MgtDAO;
import mem.Mgt.MgtDTO;

public class ExPrmBuyingService {

	private ExProgramBuyingDAO exprogramDao;
	private TableView<ExProTable> exProgramTableView;
	private ExProTable codeTable;
	private ObservableList<String> allProgram;
	private String selectData;
	private ExPrmBuyingController exPrmBuyingController;

	public void setExPrmBuyingController(ExPrmBuyingController exPrmBuyingController) {
		this.exPrmBuyingController = exPrmBuyingController;
	}

	// 실행 시 테이블뷰 업
	public void tableUp(TableView<ExProTable> exProgramTableView) {
		this.exProgramTableView = exProgramTableView;
		ObservableList<ExProTable> tableItems = FXCollections.observableArrayList();
		System.out.println(exprogramDao);
		exprogramDao = new ExProgramBuyingDAO();
		ObservableList<ExProgramBuyingDTO> allList = exprogramDao.getAllInfo();
		for (ExProgramBuyingDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRM_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(), i.getPRMSCHE_Enddate(),
					i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		exProgramTableView.setItems(tableItems);
	}
	
	
	// 회원정보페이지
	public void memMgtOpen(String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/Mgt/KG_MEM_FX_Mgt.fxml"));
		Parent memMgtForm;
		try {
			memMgtForm = loader.load();
			exPrmBuyingController.setMgtController(loader.getController());
			exPrmBuyingController.getMgtController().setMemberMgtForm(memMgtForm);
			exPrmBuyingController.getMgtController().setMembCode(membCode);

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

			// ID Sector
			IDField.setText(tmpMemDto.getMEM_ID());
			IDField.setEditable(false);// false:입력 불가
			// Name Sector
			NameField.setText(tmpMemDto.getMEM_Name());
			// PW Sector :초기 미표시
			// Birth Sector
			BirthField.setText(Integer.toString(tmpMemDto.getMEM_Birth()));
			// Mobile Sector
			MobileField.setText(Integer.toString(tmpMemDto.getMEM_Mobile()));
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
	
	//ex프로그램 종류 등록
	public void paymentProc(Parent buyingTypeForm, String membCode) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mem/BuyingType/KG_MEM_FX_BuyingType.fxml"));
		
		try {
			buyingTypeForm = loader.load();
			System.out.println(buyingTypeForm);
			exPrmBuyingController.setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.setMEM_BuyingTypeController(loader.getController());
			exPrmBuyingController.getMEM_BuyingTypeController().setBuyingTypeForm(buyingTypeForm);
			exPrmBuyingController.getMEM_BuyingTypeController().setMembCode(exPrmBuyingController.getMembCode());
			
			// 상단 이름
			Label titleUserName = (Label) buyingTypeForm.lookup("#TitleMemNameLabel");
			MgtDTO tmpMemDto = new MgtDTO(new MgtDAO().selectCode(membCode));
			titleUserName.setText(tmpMemDto.getMEM_Name() + " 회원님");

			
			Scene scene = new Scene(buyingTypeForm);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("BuyingType(결제)Form");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//취소 버튼
	public void cancelProc(Parent exProgramMgtForm) {
		CommonService.WindowClose(exProgramMgtForm);
	}
	//로그아웃 버튼
	public void logoutProc(Parent exProgramMgtForm) {
		CommonService.Msg("로그아웃 되셨습니다.");
		CommonService.WindowClose(exProgramMgtForm);
		
		
	}

	public void setCodeTable(ExProTable codeTable) {
		this.codeTable = codeTable;

	}

	
	
}
