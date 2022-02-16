package mem.FindPW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mem.FindID.FindIDDTO;

public class FindPWDAO {
	
	private Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public FindPWDAO() {
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


	public FindPWDTO FindPW(String pw, String id, String name, int birth, int mobile) {
		String sql = "select mem_pw from mem_tb where mem_id= ? and mem_name= ? and mem_birth= ? and mem_mobile = ?";
		FindPWDTO findPWDTO = null;
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, birth);
			ps.setInt(4, mobile);
			rs = ps.executeQuery();
			if (rs.next()) {
				findPWDTO = new FindPWDTO();
				
				findPWDTO.setPw(rs.getString("mem_pw"));
				findPWDTO.setId(id);
				findPWDTO.setName(name);
				findPWDTO.setBirth(birth);
				findPWDTO.setMobile(mobile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findPWDTO;
	}

}
