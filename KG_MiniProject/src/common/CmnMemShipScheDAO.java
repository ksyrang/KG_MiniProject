package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnMemShipScheDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnMemShipScheDAO() {
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
	
	// 회원권스케줄 갯수
	public ArrayList<CmnMemShipScheDTO> SltMemShipScheAll() {
		ArrayList<CmnMemShipScheDTO> Datalist = new ArrayList<>();
		sql = "SELECT MEMSHIPSCHE_Code FROM MEMSHIPSCHE_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CmnMemShipScheDTO tmpdata = new CmnMemShipScheDTO();
				tmpdata.setMEMSHIPSCHE_Code(rs.getString("MEMSHIPSCHE_Code"));
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return Datalist;
	}
	
	public CmnMemShipScheDTO SltMemShipScheOne(String MEMSHIPSCHE_Code) {
		CmnMemShipScheDTO tmpdata = null;
		sql = "SELECT * FROM MEMSHIPSCHE_TB WHERE MEMSHIPSCHE_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEMSHIPSCHE_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemShipScheDTO(
				rs.getString("MEMSHIPSCHE_Code"),
				rs.getDate("MEMSHIPSCHE_Strdate"),
				rs.getDate("MEMSHIPSCHE_Enddate"),
				rs.getString("MEMSHIP_Code"),
				rs.getString("MEM_Code")
				);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return tmpdata;		
	}
	
	
	//

}

