package mem.FindID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mem.Enroll.EnrollDTO;

public class FindIDDAO {
	private Connection con;

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
	public String FindID(String name, String birth, String mobile) {
		String id = null;

		String sql = "select mem_id from mem_tb where mem_name= ? and mem_birth= ? and mem_mobile = ?;";
		PreparedStatement ps;
		ResultSet rs;

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, birth);
			ps.setString(3, mobile);
			rs = ps.executeQuery();
			if (rs.next()) {

				id = rs.getString("mem_tb.mem_id");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
