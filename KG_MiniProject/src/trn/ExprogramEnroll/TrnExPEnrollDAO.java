package trn.ExprogramEnroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trn.DTO.ExPDTO;

public class TrnExPEnrollDAO {
	
	private Connection con;

	public TrnExPEnrollDAO() {
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
	
	public int ExPInsert(ExPDTO ExP) {
		int result = 0;
		String sql = "INSERT INTO PRM_TB VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ExP.getPRM_Code());
			ps.setString(2, ExP.getPRM_Name());
			ps.setInt(3, ExP.getPRM_Price());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public ExPDTO ExPSelect(String Code) {
		ExPDTO tmpExP = null;
		String sql = "SELECT * FROM PRM_TB WHERE PRM_Code = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpExP = new ExPDTO(rs.getString("PRM_Code")
						, rs.getString("getPRM_Name")
						, rs.getInt("getPRM_Price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return tmpExP;
	}
	public int ExPUpdate(ExPDTO ExP) {
		int result = 0;
		String sql = "UPDATE FROM PRM_TB SET"
			+"PRM_Name=?, PRM_Price=? "
			+"WHERE PRM_Code = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ExP.getPRM_Name());
			ps.setInt(2, ExP.getPRM_Price());
			ps.setString(3, ExP.getPRM_Code());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int ExPDelete(String Code) {
		int result = 0;
		String sql = "DELETE FROM PRM_TB WHERE PRM_Code = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Code);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
