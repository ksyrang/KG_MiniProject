package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnMemDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnMemDAO() {
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
	public int IstMem(CmnMemDTO DTO) {
		int result = 0;
		sql = "INSERT INTO MEM_TB "+
				"(MEM_Code, MEM_ID, MEM_PW, MEM_Name, MEM_Gender, MEM_Birth, MEM_Mobile, MEM_Addr, PRMSCHE_Code, MEMSHIPSCHE_Code, MEM_Approve)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getMEM_Code());
			ps.setString(2, DTO.getMEM_ID());
			ps.setString(3, DTO.getMEM_PW());
			ps.setString(4, DTO.getMEM_Name());
			ps.setString(5, DTO.getMEM_Gender());
			ps.setInt(6, DTO.getMEM_Birth());
			ps.setInt(7, DTO.getMEM_Mobile());
			ps.setString(8, DTO.getMEM_Addr());
			ps.setString(9, DTO.getPRMSCHE_Code());
			ps.setString(10, DTO.getMEMSHIPSCHE_Code());
			ps.setString(11, DTO.getMEM_Approve());
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
	
	public CmnMemDTO SltMemOne(String MEM_Code) {
		CmnMemDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE MEM_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEM_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemDTO(
					rs.getString("MEM_Code"),
					rs.getString("MEM_ID"),
					rs.getString("MEM_PW"),
					rs.getString("MEM_Name"),
					rs.getString("MEM_Gender"),
					rs.getInt("MEM_Birth"),
					rs.getInt("MEM_Mobile"),
					rs.getString("MEM_Addr"),
					rs.getString("PRMSCHE_Code"),
					rs.getString("MEMSHIPSCHE_Code"),
					rs.getString("MEM_Approve")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return tmpdata;		
	}
	
	public int UptMem(CmnMemDTO DTO) {
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
	
	
	public ArrayList<CmnMemDTO> SltMemAll(){
		ArrayList<CmnMemDTO> Datalist = new ArrayList<>();
		CmnMemDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnMemDTO(
					rs.getString("MEM_Code"),
					rs.getString("MEM_ID"),
					rs.getString("MEM_PW"),
					rs.getString("MEM_Name"),
					rs.getString("MEM_Gender"),
					rs.getInt("MEM_Birth"),
					rs.getInt("MEM_Mobile"),
					rs.getString("MEM_Addr"),
					rs.getString("PRMSCHE_Code"),
					rs.getString("MEMSHIPSCHE_Code"),
					rs.getString("MEM_Approve")
				);
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return Datalist;
	}
	
	public ArrayList<CmnMemDTO> SltMemAllbyPrmSche(String PRMSCHE_Code){
		ArrayList<CmnMemDTO> Datalist = new ArrayList<>();
		CmnMemDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE PRMSCHE_Code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRMSCHE_Code);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnMemDTO(
					rs.getString("MEM_Code"),
					rs.getString("MEM_ID"),
					rs.getString("MEM_PW"),
					rs.getString("MEM_Name"),
					rs.getString("MEM_Gender"),
					rs.getInt("MEM_Birth"),
					rs.getInt("MEM_Mobile"),
					rs.getString("MEM_Addr"),
					rs.getString("PRMSCHE_Code"),
					rs.getString("MEMSHIPSCHE_Code"),
					rs.getString("MEM_Approve")
				);
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return Datalist;
	}
	public ArrayList<CmnMemDTO> SltMemAllbyMemshipSche(String MEMSHIPSCHE_Code){
		ArrayList<CmnMemDTO> Datalist = new ArrayList<>();
		CmnMemDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE PRMSCHE_Code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEMSHIPSCHE_Code);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnMemDTO(
					rs.getString("MEM_Code"),
					rs.getString("MEM_ID"),
					rs.getString("MEM_PW"),
					rs.getString("MEM_Name"),
					rs.getString("MEM_Gender"),
					rs.getInt("MEM_Birth"),
					rs.getInt("MEM_Mobile"),
					rs.getString("MEM_Addr"),
					rs.getString("PRMSCHE_Code"),
					rs.getString("MEMSHIPSCHE_Code"),
					rs.getString("MEM_Approve")
				);
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return Datalist;
	}
	
	
}
