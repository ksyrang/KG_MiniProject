package mem.Delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public DeleteDAO() {
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
	
	public int delete(String MEM_Code) {
		 sql = "DELETE FROM MEM_TB WHERE MEM_CODE=?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEM_Code);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) 
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}