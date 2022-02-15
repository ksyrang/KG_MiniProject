package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnPayDAO {
	//TB명 : PAY_TB
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnPayDAO() {
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
	
	public CmnPayDTO SltPayOne(String PAY_Code) {
		CmnPayDTO tmpdata = null;
		sql = "SELECT * FROM PAY_TB WHERE PAY_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PAY_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnPayDTO(rs.getString("PAY_Code"), rs.getString("PAY_Type"), 
						rs.getDate("PAY_Date"), rs.getString("MEMSHIPSCHE_Code"), rs.getString("RES_Code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public ArrayList<CmnPayDTO> SltPayAll(){
		ArrayList<CmnPayDTO> Datalist = new ArrayList<>();
		CmnPayDTO tmpdata = null;
		sql = "SELECT * FROM PAY_TB" ;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPayDTO(rs.getString("PAY_Code"), rs.getString("PAY_Type"), 
						rs.getDate("PAY_Date"), rs.getString("MEMSHIPSCHE_Code"), rs.getString("RES_Code"));
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return Datalist;
	}
}
