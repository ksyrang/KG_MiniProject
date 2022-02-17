package admin.sales;

import admin.exProgramMgt.ExProTable;
import admin.exProgramMgt.ExProgramMgtDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


public class SalesService {
	
	private TableView<ExProTable> salesTableView;
	private SalesDAO salesDao;
	private SalesDTO salesDto;
	
	
	//실행 시 테이블뷰 업(전체 매출)
	public void tableUp(TableView<ExProTable> salesTableView) {
		
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		System.out.println("allList"+allList);
		for(SalesDTO i : allList) {
			tableItems.add(new ExProTable(i.getPRM_Name(), i.getPRMSCHE_Code(), i.getTRAINER_Name(),
					i.getPRMSCHE_LimitP(), i.getPRMSCHE_CurrentP(), i.getPRMSCHE_Strdate(),
					i.getPRMSCHE_Enddate(), i.getPRMSCHE_Price(), i.getPRMSCHE_Time()));
		}
		salesTableView.setItems(tableItems);
		
	}

}
