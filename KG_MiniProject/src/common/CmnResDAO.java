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
	
	public int IstRes(CmnResDTO cmnResDTO) {
		int result = 0;
		sql = "INSERT INTO PAY_TB "+
				"(RES_Code, MEM_Code,PRMSCHE_Code, MEMSHIPSCHE_Code)"+
				"VALUES(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cmnResDTO.getRES_Code());
			ps.setString(2, cmnResDTO.getMEM_Code());
			ps.setString(3, cmnResDTO.getPRMSCHE_Code());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { //사용한 메소드만 닫아준다 -> 해당 메소드 이외에는 공통으로 사용하기 위해 선언해 놨기 때문 -> 마지막 종료 전에 나머지(rs,con) 닫으면됨
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return result;
		
	}
	
	
	public CmnResDTO SltResOne(String RES_Code) {
		CmnResDTO tmpdata = null;
		sql = "SELECT * FROM RES_TB WHERE RES_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, RES_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), 
						rs.getString("MEM_Code"), 
						rs.getString("PRMSCHE_Code"), 
						rs.getString("MEMSHIPSCHE_Code"));
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
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), 
						rs.getString("MEM_Code"), 
						rs.getString("PRMSCHE_Code"), 
						rs.getString("MEMSHIPSCHE_Code"));
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
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), 
						rs.getString("MEM_Code"), 
						rs.getString("PRMSCHE_Code"), 
						rs.getString("MEMSHIPSCHE_Code"));
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
				tmpdata = new CmnResDTO(rs.getString("RES_Code"), 
						rs.getString("MEM_Code"), 
						rs.getString("PRMSCHE_Code"), 
						rs.getString("MEMSHIPSCHE_Code"));
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
