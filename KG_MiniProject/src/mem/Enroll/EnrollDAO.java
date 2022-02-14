package mem.Enroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import Main.login.LoginDTO;

public class EnrollDAO {
	private Connection con;
	public EnrollDAO() {
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
	
	public EnrollDTO SelectId(String id) {
		String sql = "SELECT * FROM MEM_TB WHERE ID=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				EnrollDTO enrDTO = new EnrollDTO();
				
				enrDTO.setID(rs.getString("ID"));
				enrDTO.setPW(rs.getString("PW"));
				enrDTO.setName(rs.getString("Name"));
				enrDTO.setGender(rs.getString("gender"));
				enrDTO.setBirth(rs.getString("Birth"));
				enrDTO.setMobile(rs.getString("Mobile"));
				enrDTO.setAddr(rs.getString("Addr"));
				return enrDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int insert(EnrollDTO enrDTO) {
		String sql = "INSERT INTO MEM_TB VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, enrDTO.getID());
			ps.setString(2, enrDTO.getPW());
			ps.setString(3, enrDTO.getName());
			ps.setString(4, enrDTO.getGender());
			ps.setString(5, enrDTO.getBirth());
			ps.setString(6, enrDTO.getMobile());
			ps.setString(7, enrDTO.getAddr());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}