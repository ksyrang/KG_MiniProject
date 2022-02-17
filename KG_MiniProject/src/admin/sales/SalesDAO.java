package admin.sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				salesDto.setPAY_Type(rs.getString("PAY_Type"));
				salesDto.setPAY_Date(rs.getDate("PAY_DATE"));
				
				//FK
				salesDto.setRES_Code(rs.getString("RES_Code"));
				salesDto.setMEMSHIPSCHE_Code(rs.getString("MEMSHIPSCHE_CODE"));
				salesDto.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));

				//가져올데이터
				CmnPrmScheDAO cmnPrmScheDao = new CmnPrmScheDAO();
				CmnPrmScheDTO cmnPrmScheDto = cmnPrmScheDao.SltPrmScheOne(rs.getString("PRMSCHE_Code"));
				salesDto.setPRMSCHE_PRICE(cmnPrmScheDto.getPRMSCHE_Price());
				salesDto.setPRMSCHE_Name(cmnPrmScheDto.getPRMSCHE_Name());
				
				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(cmnPrmScheDto.getPRM_Code());
				salesDto.setPRM_Name(cmnPrmDto.getPRM_Name());
				
				CmnMemShipScheDAO cmnMemShipScheDao = new CmnMemShipScheDAO();
				CmnMemShipScheDTO cmnMemShipScheDto = cmnMemShipScheDao.SltMemShipScheOne(rs.getString("MEMSHIPSCHE_CODE"));
				salesDto.setMEM_Code(cmnMemShipScheDto.getMEM_Code());
				
				
				CmnMemShipDAO cmnMemshipDao = new CmnMemShipDAO();
				CmnMemShipDTO cmnMemshipDto = cmnMemshipDao.SltMemShipOne(cmnMemShipScheDto.getMEMSHIP_Code());
				salesDto.setMEMSHIP_Price(cmnMemshipDto.getMEMSHIP_Price());
				salesDto.setMEMSHIP_Type("헬스 회원권" + cmnMemshipDto.getMEMSHIP_Type());

				allList.add(salesDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allList;
	}
	
	
	
}
