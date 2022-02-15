package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnResDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnResDAO() {
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
	
	public CmnResDTO SltResOne(String RES_Code) {
		CmnResDTO tmpdata = null;
		sql = "SELECT * FROM RES_TB WHERE RES_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, RES_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), rs.getString("MEM_Code"), rs.getString("PRMSCHE_Code"));
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
		return tmpdata;		
	}
	
	public ArrayList<CmnResDTO> SltResAll(){
		ArrayList<CmnResDTO> Datalist = new ArrayList<>();
		CmnResDTO tmpdata = null;
		sql = "SELECT * FROM RES_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), rs.getString("MEM_Code"), rs.getString("PRMSCHE_Code"));
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
	
	public ArrayList<CmnResDTO> SltResAllbyMem(String MEM_Code){
		ArrayList<CmnResDTO> Datalist = new ArrayList<>();
		CmnResDTO tmpdata = null;
		sql = "SELECT * FROM RES_TB WHERE MEM_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEM_Code);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), rs.getString("MEM_Code"), rs.getString("PRMSCHE_Code"));
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
	
	public ArrayList<CmnResDTO> SltResAllbyPrmSche(String PRMSCHE_Code){
		ArrayList<CmnResDTO> Datalist = new ArrayList<>();
		CmnResDTO tmpdata = null;
		sql = "SELECT * FROM RES_TB WHERE PRMSCHE_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRMSCHE_Code);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), rs.getString("MEM_Code"), rs.getString("PRMSCHE_Code"));
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
