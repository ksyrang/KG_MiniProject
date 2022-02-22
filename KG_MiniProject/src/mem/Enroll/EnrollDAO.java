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
				
				enrDTO.setMEM_Code(rs.getString("mem_Code"));
				enrDTO.setMEM_ID(rs.getString("mem_ID"));
				enrDTO.setMEM_PW(rs.getString("mem_PW"));
				enrDTO.setMEM_Name(rs.getString("mem_Name"));
				enrDTO.setMEM_Gender(rs.getString("mem_gender"));
				enrDTO.setMEM_Birth(rs.getInt("mem_Birth"));
				enrDTO.setMEM_Mobile(rs.getInt("mem_Mobile"));
				enrDTO.setMEM_Addr(rs.getString("mem_Addr"));
			//	enrDTO.setPRMSCHE_Code(rs.getString("prmsche_Code"));
			//	enrDTO.setMEMSHIPSCHE_Code(rs.getString("memshipsche_Code"));
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
				
				enrDTO.setMEM_Code(rs.getString("MEM_Code"));
				enrDTO.setMEM_ID(rs.getString("MEM_ID"));
				enrDTO.setMEM_PW(rs.getString("MEM_PW"));
				enrDTO.setMEM_Name(rs.getString("MEM_Name"));
				enrDTO.setMEM_Gender(rs.getString("MEM_Gender"));
				enrDTO.setMEM_Birth(rs.getInt("MEM_Birth"));
				enrDTO.setMEM_Mobile(rs.getInt("MEM_Mobile"));
				enrDTO.setMEM_Addr(rs.getString("MEM_Addr"));
			//	System.out.println(rs.getString("PRMSCHE_Code"));
			//	enrDTO.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));
			//	enrDTO.setMEMSHIPSCHE_Code(rs.getString("MEMSHIPSCHE_Code"));
				enrDTO.setMEM_Approve(rs.getString("MEM_Approve"));
				return enrDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int insert(EnrollDTO enrDTO) {
		String sql = "INSERT INTO MEM_TB(MEM_Code, MEM_ID, MEM_PW, MEM_Name, MEM_Gender, MEM_Birth, MEM_Mobile, MEM_Addr, MEM_Approve) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		//	ps.setString(10, enrDTO.getPRMSCHE_Code());
		//	ps.setString(11, enrDTO.getMEMSHIPSCHE_Code());
			ps.setString(9, enrDTO.getMEM_Approve());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}