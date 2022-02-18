package mem.Mgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.memberMgt.MemberMgtDTO;
import common.CmnMemDTO;


public class MgtDAO {
		private String sql = "";
		private Connection con = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;
		
		public MgtDAO() {
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
	
	
		public MgtDTO selectCode(String mem_code) {
			String sql = "SELECT * FROM mem_tb WHERE mem_code= ?";
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, mem_code);
				rs = ps.executeQuery();
				MgtDTO memberMgtDto = new MgtDTO();
				if(rs.next()) {
					
					memberMgtDto.setMEM_ID(rs.getString("mem_id"));
					memberMgtDto.setMEM_Name(rs.getString("mem_name"));
					memberMgtDto.setMEM_PW(rs.getString("mem_pw"));
					memberMgtDto.setMEM_Gender(rs.getString("mem_gender"));
					memberMgtDto.setMEM_Birth(rs.getInt("mem_birth"));
					memberMgtDto.setMEM_Mobile(rs.getInt("mem_mobile"));
					
					memberMgtDto.setMEM_Addr(rs.getString("mem_addr"));
					memberMgtDto.setMEM_Approve(rs.getString("mem_approve"));
					
					return memberMgtDto;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public int UptMem(MgtDTO DTO) {
			int result = 0;

			sql = "UPDATE MEM_TB SET MEM_ID=?, MEM_PW=?, "
					+ "MEM_Name=?, MEM_Gender=?, MEM_Birth=?, "
					+ "MEM_Mobile=?, MEM_Addr=?, PRMSCHE_Code=?, "
					+ "MEMSHIPSCHE_Code=?, MEM_Approve=?"
					+ "WHERE MEM_Code =? ";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, DTO.getMEM_ID());
				ps.setString(2, DTO.getMEM_PW());
				ps.setString(3, DTO.getMEM_Name());
				ps.setString(4, DTO.getMEM_Gender());
				ps.setInt(5, DTO.getMEM_Birth());
				ps.setInt(6, DTO.getMEM_Mobile());
				ps.setString(7, DTO.getMEM_Addr());
				ps.setString(8, DTO.getPRMSCHE_Code());
				ps.setString(9, DTO.getMEMSHIPSCHE_Code());
				ps.setString(10, DTO.getMEM_Approve());
				ps.setString(11, DTO.getMEM_Code());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try { 
					if(ps != null) ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}
		
		
}
	
	

