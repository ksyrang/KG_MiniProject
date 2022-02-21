package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnMemScheDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnMemScheDAO() {
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
	
	
	
	public int IstMem(CmnMemScheDTO DTO) {
		int result = 0;
		sql = "INSERT INTO MEMSCHE_TB "+
				"(MEMSCHE_Code, MEM_CODE, MEMSHIPSCHE_CODE)"+
				"VALUES(?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getMEMSCHE_Code());
			ps.setString(2, DTO.getMEM_Code());
			ps.setString(3, DTO.getMEMSHIPSCHE_Code());
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { 
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public int IstPro(CmnMemScheDTO DTO) {
		int result = 0;
		sql = "INSERT INTO MEMSCHE_TB "+
				"(MEMSCHE_Code, MEM_CODE, PRMSCHE_Code)"+
				"VALUES(?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getMEMSCHE_Code());
			ps.setString(2, DTO.getMEM_Code());
			ps.setString(3, DTO.getPRMSCHE_Code());
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { 
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public CmnMemScheDTO SltMemOne(String MEM_Code) {
		CmnMemScheDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE MEM_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEM_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemScheDTO(
					rs.getString("MEMSche_Code"),
					rs.getString("MEM_Code"),
					rs.getString("PRMSCHE_Code"),
					rs.getString("MEMSHIPSCHE_Code")
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
	
	
	public ArrayList<CmnMemScheDTO> SltMemAll(){
		ArrayList<CmnMemScheDTO> Datalist = new ArrayList<>();
		CmnMemScheDTO tmpdata = null;
		sql = "SELECT * FROM MEMSche_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnMemScheDTO(
						rs.getString("MEMSche_Code"),
						rs.getString("MEM_ID"),
						rs.getString("PRMSCHE_Code"),
						rs.getString("MEMSHIPSCHE_Code")
				);
				Datalist.add(tmpdata);
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
		return Datalist;
	}
	
}
