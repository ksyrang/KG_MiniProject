package admin.sales;


import java.util.ArrayList;

import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;


public class SalesService {
	
	private TableView<SalesTable> salesTableView;
	

	
	
	//실행 및 전체선택 시 테이블뷰 업(전체 매출)
	public void tableUp(TableView<SalesTable> salesTableView) {
		SalesDAO salesDao = new SalesDAO();
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		salesDao.getAllInfo();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			String trainerName;
			if(i.getPRM_Name().isEmpty()){
				//헬스 회원권임
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권" + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				trainerName = "KGGYM";
			}else {
				//ex프로그램임
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				trainerName = i.getTRAINER_NAME();
			}
			tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
					price, i.getPAY_Type(), i.getPAY_Date(), trainerName));
		}
		salesTableView.setItems(tableItems);
	}

	
	//헬스 회원권 매출 선택 시 테이블뷰 로딩
	public void memSalesTableUp(TableView<SalesTable> salesTableView) {
		SalesDAO salesDao = new SalesDAO();
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			String trainerName;
			
			
			String splitData = i.getMEMSHIP_Type().substring(0,1);
			if(splitData.equals("헬스")){
				//헬스 회원권
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권" + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				trainerName = "KGGYM";
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date(), trainerName));
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
		SalesDAO salesDao = new SalesDAO();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;

			if(i.getPRM_Name().isEmpty()){
				continue;
			}else {
				//ExProgram
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date(), i.getTRAINER_NAME()));
			}
		}
		salesTableView.setItems(tableItems);
	}
		

	//각 EXprogram 종류 매출 선택 시 테이블뷰 로딩
	public void exProgramTypeSalesTableUp(TableView<SalesTable> salesTableView, String exProgramType) {
		System.out.println("여기까지옴=========================");
		SalesDAO salesDao = new SalesDAO();
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType = exProgramType;
			if(programType.equals(i.getPRM_Name())){
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date(), i.getTRAINER_NAME()));
			}else {
				System.out.println("else오류발생");
			}
		}
		salesTableView.setItems(tableItems);
	}

	
	//EXprogram 강사별 매출 선택 시 테이블뷰 로딩
	public void trainerTypeTableUp(TableView<SalesTable> salesTableView, String TrainerName) {
		SalesDAO salesDao = new SalesDAO();
		this.salesTableView = salesTableView;
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		ObservableList<SalesDTO> allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			int price;
			String programName;
			String programType;
			String trainerName = TrainerName;
			if(trainerName.equals(i.getTRAINER_NAME())){
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date(),i.getTRAINER_NAME()));
			}
		}
		salesTableView.setItems(tableItems);
	}
		

	
	//DetailComboBox setting
	public void detailComboSetting(String string, ComboBox<String> detailCombo) {
		ObservableList<String> items = FXCollections.observableArrayList();
		
		if(string.equals("EXProgram")) {
			CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
			ArrayList<CmnPrmDTO> cmnPrmDto = cmnPrmDao.SltPrmAll();
			for(CmnPrmDTO i: cmnPrmDto) {
				items.add(i.getPRM_Name());
			}
			detailCombo.setItems(items);
		}else if(string.equals("Trainer")){
			CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
			ArrayList<CmnTrainerDTO> cmnTrainerDto = cmnTrainerDao.SltTrnAll();
			for(CmnTrainerDTO i: cmnTrainerDto) {
				items.add(i.getTRAINER_Name());	
			}
			detailCombo.setItems(items);
		}
		
	}
	



	
	
	
	
	
	

}
