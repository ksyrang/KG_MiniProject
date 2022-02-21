package mem.EXProgramBuying;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExPrmBuyingService {
	private ExProgramMgtDTO exprogramDto;
	private ExProgramMgtDAO exprogramDao;
	private ListView<String> programListView;
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
		ObservableList<ExProgramMgtDTO> allList = exprogramDao.getAllInfo();
		for (ExProgramMgtDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRM_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(), i.getPRMSCHE_Enddate(),
					i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		exProgramTableView.setItems(tableItems);
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
	public void cancelProc(Parent exProgramBuyingForm) {
		CommonService.WindowClose(exProgramBuyingForm);
	}
	//로그아웃 버튼
	public void logoutProc(Parent exProgramBuyingForm) {
		CommonService.Msg("로그아웃 되셨습니다.");
		CommonService.WindowClose(exProgramBuyingForm);
		
		
	}

	public void setSelectData(String selectData) {
		this.selectData = selectData;

	}

	public void setCodeTable(ExProTable codeTable) {
		this.codeTable = codeTable;

	}

	public ObservableList<String> getAllProgram() {
		exprogramDao = new ExProgramMgtDAO();
		this.allProgram = exprogramDao.getAllProgram();
		return this.allProgram;
	}
	
}
