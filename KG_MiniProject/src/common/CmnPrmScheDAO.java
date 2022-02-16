package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnPrmScheDAO {
	//TB : PRMSCHE_TB
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnPrmScheDAO() {
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
	public int IstPrmSche(CmnPrmScheDTO DTO) {
		int result = 0;
		sql = "INSERT INTO PRMSCHE_TB "+
				"(PRMSCHE_Code, PRMSCHE_Strdate, PRMSCHE_Enddate, PRMSCHE_Time, PRMSCHE_LimitP, "+
				"PRMSCHE_CurrentP, PRMSCHE_Price, PRMSCHE_Name, "+
				"PRM_Code, TRAINER_Code)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getPRMSCHE_Code());
			ps.setDate(2, DTO.getPRMSCHE_Strdate());
			ps.setDate(3, DTO.getPRMSCHE_Enddate());
			ps.setString(4, DTO.getPRMSCHE_Time());
			ps.setInt(5, DTO.getPRMSCHE_LimitP());
			ps.setInt(6, DTO.getPRMSCHE_CurrentP());
			ps.setInt(7, DTO.getPRMSCHE_Price());
			ps.setString(8, DTO.getPRMSCHE_Name());
			ps.setString(9, DTO.getPRM_Code());
			ps.setString(10, DTO.getTRAINER_Code());
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
	
	public CmnPrmScheDTO SltPrmScheOne(String PRMSCHE_Code) {
		CmnPrmScheDTO tmpdata = null;
		sql = "SELECT * FROM PRMSCHE_TB WHERE PRMSCHE_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRMSCHE_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnPrmScheDTO(
					rs.getString("PRMSCHE_Code"),
					rs.getDate("PRMSCHE_Strdate"),
					rs.getDate("PRMSCHE_Enddate"),
					rs.getString("PRMSCHE_Time"),
					rs.getInt("PRMSCHE_LimitP"),
					rs.getInt("PRMSCHE_CurrentP"),
					rs.getInt("PRMSCHE_Price"),
					rs.getString("PRMSCHE_Name"),
					rs.getString("PRM_Code"),
					rs.getString("TRAINER_Code")
				);
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
	
	public ArrayList<CmnPrmScheDTO> SltPrmScheAll(){
		ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
		CmnPrmScheDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmScheDTO(
					rs.getString("PRMSCHE_Code"),
					rs.getDate("PRMSCHE_Strdate"),
					rs.getDate("PRMSCHE_Enddate"),
					rs.getString("PRMSCHE_Time"),
					rs.getInt("PRMSCHE_LimitP"),
					rs.getInt("PRMSCHE_CurrentP"),
					rs.getInt("PRMSCHE_Price"),
					rs.getString("PRMSCHE_Name"),
					rs.getString("PRM_Code"),
					rs.getString("TRAINER_Code")
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
	public ArrayList<CmnPrmScheDTO> SltPrmScheAllbyPrm(String PRM_Code){
		ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
		CmnPrmScheDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE PRM_Code=?";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmScheDTO(
					rs.getString("PRMSCHE_Code"),
					rs.getDate("PRMSCHE_Strdate"),
					rs.getDate("PRMSCHE_Enddate"),
					rs.getString("PRMSCHE_Time"),
					rs.getInt("PRMSCHE_LimitP"),
					rs.getInt("PRMSCHE_CurrentP"),
					rs.getInt("PRMSCHE_Price"),
					rs.getString("PRMSCHE_Name"),
					rs.getString("PRM_Code"),
					rs.getString("TRAINER_Code")
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
	
	public ArrayList<CmnPrmScheDTO> SltPrmScheAllbyTrn(String TRAINER_Code){
		ArrayList<CmnPrmScheDTO> Datalist = new ArrayList<>();
		CmnPrmScheDTO tmpdata = null;
		sql = "SELECT * FROM MEM_TB WHERE TRAINER_Code=?";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmScheDTO(
					rs.getString("PRMSCHE_Code"),
					rs.getDate("PRMSCHE_Strdate"),
					rs.getDate("PRMSCHE_Enddate"),
					rs.getString("PRMSCHE_Time"),
					rs.getInt("PRMSCHE_LimitP"),
					rs.getInt("PRMSCHE_CurrentP"),
					rs.getInt("PRMSCHE_Price"),
					rs.getString("PRMSCHE_Name"),
					rs.getString("PRM_Code"),
					rs.getString("TRAINER_Code")
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
	
}//class end