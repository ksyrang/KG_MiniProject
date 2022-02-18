package mem.Delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mem.Enroll.EnrollDTO;

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
	
	public DeleteDTO SelectPW(String password) {
		String sql = "SELECT * FROM MEM_TB WHERE MEM_PW=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				DeleteDTO deleteDTO = new DeleteDTO();
				
				deleteDTO.setMEM_Code(rs.getString("mem_Code"));
				deleteDTO.setMEM_ID(rs.getString("mem_ID"));
				deleteDTO.setMEM_PW(rs.getString("mem_PW"));
				deleteDTO.setMEM_Name(rs.getString("mem_Name"));
				deleteDTO.setMEM_Gender(rs.getString("mem_gender"));
				deleteDTO.setMEM_Birth(rs.getInt("mem_Birth"));
				deleteDTO.setMEM_Mobile(rs.getInt("mem_Mobile"));
				deleteDTO.setMEM_Addr(rs.getString("mem_Addr"));
				deleteDTO.setPRMSCHE_Code(rs.getString("prmsche_Code"));
				deleteDTO.setMEMSHIPSCHE_Code(rs.getString("memshipsche_Code"));
				deleteDTO.setMEM_Approve(rs.getString("mem_approve"));
				return deleteDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int delete(String memCode) {
		 sql = "DELETE FROM MEM_TB WHERE MEM_Code=?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memCode);
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