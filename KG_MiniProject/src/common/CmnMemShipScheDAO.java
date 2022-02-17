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

}

