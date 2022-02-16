package mem.FindID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindIDDAO {
	
	private Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public FindIDDAO() {
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

/*	public FindIDDTO SelectName(String name) {
		String sql = "SELECT * FROM MEM_TB WHERE MEM_Name=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				FindIDDTO findIDDTO = new FindIDDTO();
				

				findIDDTO.setName(rs.getString("mem_Name"));
				findIDDTO.setBirth(rs.getString("mem_Birth"));
				findIDDTO.setMobile(rs.getString("mem_Mobile"));
				return findIDDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/	
	public FindIDDTO FindID(String name, String birth, String mobile) {
		String sql = "select * from mem_tb where mem_name= '?' and mem_birth= ? and mem_mobile = ?;";
		FindIDDTO findIDDTO = null;
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, birth);
			ps.setString(3, mobile);
			rs = ps.executeQuery();
			if (rs.next()) {
				findIDDTO = new FindIDDTO();
				findIDDTO.setName(name);
				findIDDTO.setBirth(rs.getInt("mem_birth"));
				findIDDTO.setMobile(rs.getInt("mem_mobile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findIDDTO;
	}

}
