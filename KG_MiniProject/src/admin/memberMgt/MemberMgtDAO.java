package admin.memberMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMgtDAO {
	private Connection con;
	
	public MemberMgtDAO() {
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
	
	public MemberMgtDTO allView() {
		String sql = "SELECT mem_code, mem_name FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		MemberMgtDTO memberMgtDto = new MemberMgtDTO();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				memberMgtDto.setMemCode(rs.getString("MemCode"));
				memberMgtDto.setID(rs.getString("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberMgtDto;
	}
	

}
