package admin.memberMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
	
	public ArrayList<MemberMgtDTO> getAllMemberList() {
		String sql = "SELECT mem_code, mem_name FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<MemberMgtDTO> member = new ArrayList<MemberMgtDTO>();
		//List<MemberMgtDTO> data = new ArrayList<MemberMgtDTO>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				member.add(memberMgtDto);
				//data.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	

}
