package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CmnMemShipDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnMemShipDAO() {
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
	
	
	public CmnMemShipDTO SltMemShipOne(String MEMSHIP_Code) {
		CmnMemShipDTO tmpdata = null;
		sql = "SELECT * FROM MEMSHIP_TB WHERE MEMSHIP_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEMSHIP_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemShipDTO(
				rs.getString("MEMSHIP_CODE"),
				rs.getString("MEMSHIP_TYPE"),
				rs.getInt("MEMSHIP_PRICE"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	// 회원권 종류로 모두 불러오기
	public CmnMemShipDTO SltMemShipAll(String MEMSHIP_Type) {
		CmnMemShipDTO tmpdata = null;
		sql = "SELECT * FROM MEMSHIP_TB WHERE MEMSHIP_Type = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEMSHIP_Type);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemShipDTO(
				rs.getString("MEMSHIP_CODE"),
				rs.getString("MEMSHIP_TYPE"),
				rs.getInt("MEMSHIP_PRICE"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	// 회원권 종류 불러오기
	public ObservableList<String> GETMemshipType() {
		ObservableList<String> type = FXCollections.observableArrayList();
		String sql = "SELECT MEMSHIP_Type FROM MEMSHIP_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				type.add(rs.getString("MEMSHIP_Type"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return type;
	}

	
	
}
