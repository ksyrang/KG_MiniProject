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
	
	private int price;
	private String programName;
	private String programType;
	private String trainerName;

	ObservableList<SalesDTO> allList;
	
	//실행 시 테이블뷰 업(전체 매출)
	public void tableUp(TableView<SalesTable> salesTableView) {
		SalesDAO salesDao = new SalesDAO();
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		this.allList = salesDao.getAllInfo();
		for(SalesDTO i : allList) {
			
			
			//null처리
			if(i.getPRMSCHE_Code() != null){
				//ex프로그램임
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				trainerName = i.getTRAINER_NAME();
			}else {
				//헬스 회원권임
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권 " + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				trainerName = "KGGYM";
			}
			tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
					price, i.getPAY_Type(), i.getPAY_Date(), trainerName));
		}
		salesTableView.setItems(tableItems);
	}
	
	//전체 매출 선택 시
	public void allSalesTable(TableView<SalesTable> salesTableView) {
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();

		for(SalesDTO i : this.allList) {
			
			
			//null처리
			if(i.getPRMSCHE_Code() != null){
				//ex프로그램임
				price = i.getPRMSCHE_Price();
				programName = i.getPRMSCHE_Name();
				programType = i.getPRM_Name();
				trainerName = i.getTRAINER_NAME();
			}else {
				//헬스 회원권임
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권 " + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				trainerName = "KGGYM";
			}
			tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
					price, i.getPAY_Type(), i.getPAY_Date(), trainerName));
		}
		salesTableView.setItems(tableItems);
	}

	
	//헬스 회원권 매출 선택 시 테이블뷰 로딩
	public void memSalesTableUp(TableView<SalesTable> salesTableView) {
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		for(SalesDTO i : this.allList) {
			//null처리
			if(i.getPRMSCHE_Code() != null){
			}else {
				//헬스 회원권
				price = i.getMEMSHIP_Price();
				programName = "헬스 회원권" + i.getMEMSHIP_Type() + " 개월";
				programType = "헬스 회원권";
				trainerName = "KGGYM";
				tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
						price, i.getPAY_Type(), i.getPAY_Date(), trainerName));
			}
			
		}
		salesTableView.setItems(tableItems);
	}
		

	//EXProgram매출 선택 시 테이블뷰 로딩
	public void exProgramSalesTableUp(TableView<SalesTable> salesTableView) {
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		for(SalesDTO i : this.allList) {
			//null처리
			if(i.getPRMSCHE_Code() != null){
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
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		for(SalesDTO i : this.allList) {
			String programType = exProgramType;
			//null처리
			if(i.getPRMSCHE_Code() != null && programType != null){
				if(programType.equals(i.getPRM_Name())){
					price = i.getPRMSCHE_Price();
					programName = i.getPRMSCHE_Name();
					tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
							price, i.getPAY_Type(), i.getPAY_Date(), i.getTRAINER_NAME()));
				}
			}
			
		}
		salesTableView.setItems(tableItems);
	}

	
	//EXprogram 강사별 매출 선택 시 테이블뷰 로딩
	public void trainerTypeTableUp(TableView<SalesTable> salesTableView, String TrainerName) {
		ObservableList<SalesTable> tableItems = FXCollections.observableArrayList();
		for(SalesDTO i : this.allList) {
			String trainerName = TrainerName;
			//null처리
			if(i.getPRMSCHE_Code() != null && trainerName != null){
				if(trainerName.equals(i.getTRAINER_NAME())){
					price = i.getPRMSCHE_Price();
					programName = i.getPRMSCHE_Name();
					programType = i.getPRM_Name();
					tableItems.add(new SalesTable(i.getMEM_Code(), programName, programType,
							price, i.getPAY_Type(), i.getPAY_Date(),i.getTRAINER_NAME()));
				}
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

	public int seles(String select) {
		int allPrice= 0;
		if(select.equals("전체 매출")) {
			for(SalesDTO i : this.allList) {
				//null처리
				if(i.getPRMSCHE_Code() != null){
					//ExProgram
					allPrice += i.getPRMSCHE_Price();
				}else {
					//헬스 회원권
					allPrice += i.getMEMSHIP_Price();
				}
			}
		}else if(select.equals("헬스 회원권 전체 매출")) {
			for(SalesDTO i : this.allList) {
				//null처리
				if(i.getPRMSCHE_Code() != null){
				}else {
					//헬스 회원권
					allPrice += i.getMEMSHIP_Price();
				}
			}
		}else if(select.equals("EX 프로그램 전체 매출")) {
			for(SalesDTO i : this.allList) {
				//null처리
				if(i.getPRMSCHE_Code() != null){
					//ExProgram
					allPrice += i.getPRMSCHE_Price();
				}
			}
		}else if(select.equals("EX 프로그램 종류별 매출")) {
					allPrice = 0;
			
		}else if(select.equals("강사별 매출")) {
					allPrice = 0;
		}
		return allPrice;
	}
	
	
	
	public int seles(String select, String name) {
		int allPrice= 0;
		if(select.equals("EX 프로그램 종류별 매출")) {
			for(SalesDTO i : this.allList) {
				String programType = name;
				//null처리
				if(i.getPRMSCHE_Code() != null && programType != null){
					if(programType.equals(i.getPRM_Name())){
						allPrice += i.getPRMSCHE_Price();

					}
				}
			}
		}else if(select.equals("강사별 매출")) {
			for(SalesDTO i : this.allList) {
				String trainerName = name;
				//null처리
				if(i.getPRMSCHE_Code() != null && trainerName != null){
					if(trainerName.equals(i.getTRAINER_NAME())){
						allPrice += i.getPRMSCHE_Price();
					}
				}
			}
		}
		
		
		return allPrice;
	}
	

	
	

}
