package mem.Enroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Parent;

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
	
	public EnrollDTO SelectMobile(int mobile) {
		String sql = "SELECT * FROM MEM_TB WHERE MEM_MOBILE=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, mobile);
			rs = ps.executeQuery();
			if(rs.next()) {
				EnrollDTO enrDTO = new EnrollDTO();
				

				enrDTO.setMEM_ID(rs.getString("mem_ID"));
				enrDTO.setMEM_PW(rs.getString("mem_PW"));
				enrDTO.setMEM_Name(rs.getString("mem_Name"));
				enrDTO.setMEM_Gender(rs.getString("mem_gender"));
				enrDTO.setMEM_Birth(rs.getInt("mem_Birth"));
				enrDTO.setMEM_Mobile(rs.getInt("mem_Mobile"));
				enrDTO.setMEM_Addr(rs.getString("mem_Addr"));
				enrDTO.setMEM_Approve(rs.getString("mem_approve"));
				return enrDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EnrollDTO SelectId(String id) {
		String sql = "SELECT * FROM MEM_TB WHERE MEM_ID=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				EnrollDTO enrDTO = new EnrollDTO();
				

				enrDTO.setMEM_ID(rs.getString("mem_ID"));
				enrDTO.setMEM_PW(rs.getString("mem_PW"));
				enrDTO.setMEM_Name(rs.getString("mem_Name"));
				enrDTO.setMEM_Gender(rs.getString("mem_gender"));
				enrDTO.setMEM_Birth(rs.getInt("mem_Birth"));
				enrDTO.setMEM_Mobile(rs.getInt("mem_Mobile"));
				enrDTO.setMEM_Addr(rs.getString("mem_Addr"));
				enrDTO.setMEM_Approve(rs.getString("mem_approve"));
				return enrDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int insert(EnrollDTO enrDTO) {
		String sql = "INSERT INTO MEM_TB VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, enrDTO.getMEM_Code());
			ps.setString(2, enrDTO.getMEM_ID());
			ps.setString(3, enrDTO.getMEM_PW());
			ps.setString(4, enrDTO.getMEM_Name());
			ps.setString(5, enrDTO.getMEM_Gender());
			ps.setLong(6, enrDTO.getMEM_Birth());
			ps.setLong(7, enrDTO.getMEM_Mobile());
			ps.setString(8, enrDTO.getMEM_Addr());
			ps.setString(9, enrDTO.getPRMSCHE_Code());
			ps.setString(10, enrDTO.getMEMSHIPSCHE_Code());
			ps.setString(11, enrDTO.getMEM_Approve());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}