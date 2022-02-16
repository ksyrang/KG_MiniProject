package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnTrainerDAO {
	//TB명 : TRAINER_TB
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnTrainerDAO() {
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
	public int IstTrn(CmnTrainerDTO DTO) {
		int result = 0;
		sql = "INSERT INTO TRAINER_TB "+
				"(TRAINER_Code, TRAINER_Name, TRAINER_ID, TRAINER_PW, "+
				"TRAINER_Gender, TRAINER_Birth, TRAINER_Mobile, "+
				"TRAINER_Career, TRAINER_Addr) "+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getTRAINER_Code());
			ps.setString(2, DTO.getTRAINER_Name());
			ps.setString(3, DTO.getTRAINER_ID());
			ps.setString(4, DTO.getTRAINER_PW());
			ps.setString(5, DTO.getTRAINER_Gender());
			ps.setInt(6, DTO.getTRAINER_Birth());
			ps.setInt(7, DTO.getTRAINER_Mobile());
			ps.setInt(8, DTO.getTRAINER_Career());
			ps.setString(9, DTO.getTRAINER_Addr());
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
	public CmnTrainerDTO SltTrnOne(String TRAINER_Code) {
		CmnTrainerDTO tmpdata = null;
		sql = "SELECT * FROM TRAINER_TB WHERE TRAINER_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, TRAINER_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnTrainerDTO(
					rs.getString("TRAINER_Code"),
					rs.getString("TRAINER_Name"),
					rs.getString("TRAINER_ID"),
					rs.getString("TRAINER_PW"),
					rs.getString("TRAINER_Gender"),
					rs.getInt("TRAINER_Birth"),
					rs.getInt("TRAINER_Mobile"),
					rs.getInt("TRAINER_Career"),
					rs.getString("TRAINER_Addr")
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
	
	public CmnTrainerDTO SltTrnId(String TRAINER_ID) {
		CmnTrainerDTO tmpdata = null;
		sql = "SELECT * FROM TRAINER_TB WHERE TRAINER_ID = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, TRAINER_ID);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnTrainerDTO(
					rs.getString("TRAINER_Code"),
					rs.getString("TRAINER_Name"),
					rs.getString("TRAINER_ID"),
					rs.getString("TRAINER_PW"),
					rs.getString("TRAINER_Gender"),
					rs.getInt("TRAINER_Birth"),
					rs.getInt("TRAINER_Mobile"),
					rs.getInt("TRAINER_Career"),
					rs.getString("TRAINER_Addr")
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
	
	public ArrayList<CmnTrainerDTO> SltTrnAll(){
		ArrayList<CmnTrainerDTO> Datalist = new ArrayList<>();
		CmnTrainerDTO tmpdata = null;
		sql = "SELECT * FROM TRAINER_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnTrainerDTO(
						rs.getString("TRAINER_Code"),
						rs.getString("TRAINER_Name"),
						rs.getString("TRAINER_ID"),
						rs.getString("TRAINER_PW"),
						rs.getString("TRAINER_Gender"),
						rs.getInt("TRAINER_Birth"),
						rs.getInt("TRAINER_Mobile"),
						rs.getInt("TRAINER_Career"),
						rs.getString("TRAINER_Addr")
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
