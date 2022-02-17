package admin.sales;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;


public class SalesService {
	
	private TableView<SalesTable> salesTableView;
	private SalesDAO salesDao;
	private SalesDTO salesDto;
	
	
	//실행 및 전체선택 시 테이블뷰 업(전체 매출)
	public void tableUp(TableView<SalesTable> salesTableView) {
		
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			if(i.getPRM_Name().equals(null)){
				//헬스 회원권임
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권" + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
			}else {
				//ex프로그램임
				price = i.getPRMSCHE_PRICE();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
			}
			tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
					price, i.getPAY_Type(), i.getPAY_Date()));
		}
		salesTableView.setItems(tableItems);
	}

	
	//헬스 회원권 매출 선택 시 테이블뷰 로딩
	public void memSalesTableUp(TableView<SalesTable> salesTableView) {
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			if(i.getPRM_Name().equals(null)){
				//헬스 회원권
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권" + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date()));
			}else {
				continue;
			}
		}
		salesTableView.setItems(tableItems);
	}
		

	//EXProgram매출 선택 시 테이블뷰 로딩
	public void exProgramSalesTableUp(TableView<SalesTable> salesTableView) {
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			if(i.getPRM_Name().equals(null)){
				continue;
			}else {
				//ExProgram
				price = i.getPRMSCHE_PRICE();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date()));
			}
		}
		salesTableView.setItems(tableItems);
	}
		

	//각 EXprogram 종류 매출 선택 시 테이블뷰 로딩
	public void exProgramTypeSalesTableUp(TableView<SalesTable> salesTableView) {
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			if(i.getPRM_Name().equals(null)){
			}else {
				//ExProgram
				price = i.getPRMSCHE_PRICE();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date()));
			}
		}
		salesTableView.setItems(tableItems);
	}

	//EXprogram 강사별 매출 선택 시 테이블뷰 로딩
	public void trainerTypeTableUp(TableView<SalesTable> salesTableView) {
		// TODO Auto-generated method stub
		
	}

	//DetailComboBox setting

	public void detailComboSetting(String string, ComboBox<String> detailCombo) {
//		detailCombo.
//		detailCombo.setValue(string);
		
	}
	



	
	
	
	
	
	

}
