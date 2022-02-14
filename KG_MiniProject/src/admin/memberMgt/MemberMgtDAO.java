package admin.memberMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
	public List<MemberMgtDTO> getAllMemberList() {
		String sql = "SELECT mem_code, mem_name FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		List<MemberMgtDTO> data = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code("mem_code");
				memberMgtDto.setMem_id("mem_id");
				
				data.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;

	}
	

}
