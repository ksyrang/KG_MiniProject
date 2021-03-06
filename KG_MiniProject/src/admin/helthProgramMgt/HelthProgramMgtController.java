package admin.helthProgramMgt;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class HelthProgramMgtController implements Initializable{
	private Parent helthProgramMgtForm;
	private HelthProgramMgtService healthSvc;
	
	@FXML private TableView<HelthProTable> memshipTable;
	@FXML private TableColumn<HelthProTable, String> colCode;
	@FXML private TableColumn<HelthProTable, String> colType;
	@FXML private TableColumn<HelthProTable, String> colPrice;
	
	@FXML private TextField memshipType, memshipPrice;
	
	ObservableList<HelthProTable> obserList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		healthSvc = new HelthProgramMgtService();
		initSetting();
		
		memshipType.textProperty().addListener((attribute,before, after) -> {
			memshipType.setText(CommonService.getLengthLimit(5, memshipType.getText()));
	      });

		memshipPrice.textProperty().addListener((attribute, before, after) -> {
			memshipPrice.setText(CommonService.getLengthLimit(8, memshipPrice.getText()));
		});
		
	}
	
	public void setHelthMgtForm(Parent helthProgramMgtForm) {
		this.helthProgramMgtForm = helthProgramMgtForm;
	}
	
	//초기세팅
	public void initSetting()  {
		// 테이블뷰
		HelthProgramMgtDAO helthProMgtDao = new HelthProgramMgtDAO();
		ObservableList<HelthProgramMgtDTO> helthProMgtDto = helthProMgtDao.getAllPro();

		obserList = FXCollections.observableArrayList();

		colCode.setCellValueFactory(new PropertyValueFactory<>("colCode"));
		colType.setCellValueFactory(new PropertyValueFactory<>("colType"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));

		for (HelthProgramMgtDTO m : helthProMgtDto) {
			String price = CommonService.priceFormat(m.getMemship_price());
			obserList.add(new HelthProTable(m.getMemship_code(), "헬스 회원권 " + m.getMemship_type() + "개월",
					price));
		}

		memshipTable.setItems(obserList);
	}
	// 테이블 뷰 클릭 시 
	public void tableClick() {
		memshipTable.setOnMouseClicked((MouseEvent e) -> {
			try {
				HelthProTable ht = memshipTable.getSelectionModel().getSelectedItem();
				healthSvc.cellClick(helthProgramMgtForm, ht.getColCode());
			} catch (NullPointerException e2) {
				CommonService.Msg("회원권을 선택해주세요.");
			}
			
		});
	}
	
	// 회원권 등록 버튼 클릭 시
	public void memshipInsertProc() {
		healthSvc.memshipInsert(helthProgramMgtForm);
	}
	
	// 회원권 삭제 버튼 클릭 시
	public void memshipDeleteProc() {
		healthSvc.memshipDelete(helthProgramMgtForm);
	}
	
	// 이전 버튼 클릭 시
	public void memshipCancelProc() {
		CommonService.WindowClose(helthProgramMgtForm);
	}
	
	

}
