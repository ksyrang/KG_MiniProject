package Main.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	private Connection con;
	
	public LoginDAO() {
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
	
	public LoginDTO SelectMemberId(String id) {
		String sql = "SELECT * FROM MEM_TB WHERE MEM_ID=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				LoginDTO loginDto = new LoginDTO();	
				loginDto.setMEM_Code(rs.getString("MEM_Code"));
				loginDto.setMEM_ID(rs.getString("MEM_ID"));
				loginDto.setMEM_PW(rs.getString("MEM_PW"));
				return loginDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public LoginDTO SelectTrainerId(String id) {
		String sql = "SELECT * FROM TRAINER_TB WHERE TRAINER_ID=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				LoginDTO loginDto = new LoginDTO();	
				loginDto.setTRAINER_Code(rs.getString("TRAINER_Code"));
				loginDto.setTRAINER_ID(rs.getString("TRAINER_ID"));
				loginDto.setTRAINER_PW(rs.getString("TRAINER_PW"));
				return loginDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
