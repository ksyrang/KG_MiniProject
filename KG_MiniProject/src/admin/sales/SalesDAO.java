package admin.sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.omg.CORBA.NVList;

import admin.exProgramMgt.ExProgramMgtDTO;
import common.CmnMemDAO;
import common.CmnMemDTO;
import common.CmnMemShipDAO;
import common.CmnMemShipDTO;
import common.CmnMemShipScheDAO;
import common.CmnMemShipScheDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnResDAO;
import common.CmnResDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesDAO {

	private Connection con;
	
	public SalesDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "KGGYM";
		String password = "oracle1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//tableView 모든 정보 리턴
	public ObservableList<SalesDTO> getAllInfo() {
		
		String sql = "SELECT * FROM PAY_TB";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<SalesDTO> allList = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesDTO salesDto = new SalesDTO();
				salesDto.setPAY_Code(rs.getString("PAY_CODE"));
				salesDto.setPAY_Type(rs.getString("PAY_TYPE"));
				salesDto.setPAY_Date(rs.getDate("PAY_DATE"));
				
				
				//회원고유번호 등록
				String memCode = rs.getString("MEM_CODE");
				salesDto.setMEM_Code(memCode);
//				salesDto.setRES_Code(rs.getString("RES_CODE"));
				
				//null처리
				if(rs.getString("MEMSHIPSCHE_CODE") != null) {
					String memShipScheCode = rs.getString("MEMSHIPSCHE_CODE");
					salesDto.setMEMSHIPSCHE_Code(memShipScheCode);
					//헬스 회원권
					CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
					CmnMemShipScheDTO cmnMemShipScheDto = cmnMemShipScheDao.SltMemShipScheOne(memShipScheCode);
					//멤버쉽코드
					String memShipCode = cmnMemShipScheDto.getMEMSHIP_Code();
					CmnMemShipDAO cmnMemshipDao = new CmnMemShipDAO();
					CmnMemShipDTO cmnMemshipDto = cmnMemshipDao.SltMemShipOne(memShipCode);
					//맴버쉽 정보등록
					int memShipPrice = cmnMemshipDto.getMEMSHIP_Price();
					//회원권 정보등록
					String memShipType = cmnMemshipDto.getMEMSHIP_Type();
					salesDto.setMEMSHIP_Price(memShipPrice);
					salesDto.setMEMSHIP_Type(memShipType);
					
				}
				else {
					//ex프로그램
					
					String prmScheCode = rs.getString("PRMSCHE_Code");
					salesDto.setPRMSCHE_Code(prmScheCode);
					CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
					CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(prmScheCode);
					
					//prmcode 생성
					String prmCode = cmnPrmScheDto.getPRM_Code();
					CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
					CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(prmCode);
					//trainer정보생성
					String trainerCode = cmnPrmScheDto.getTRAINER_Code();
					CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
					CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(trainerCode);
					//프로그램 정보등록
					int prmschePrice = cmnPrmScheDto.getPRMSCHE_Price();
					String prmScheName = cmnPrmScheDto.getPRMSCHE_Name();
					String prmName = cmnPrmDto.getPRM_Name();
					String trainerName = cmnTrainerDto.getTRAINER_Name();
					
					salesDto.setPRMSCHE_Price(prmschePrice);
					salesDto.setPRMSCHE_Name(prmScheName);
					salesDto.setPRM_Name(prmName);
					salesDto.setTRAINER_NAME(trainerName);
				}
				allList.add(salesDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allList;
	}


	
}
