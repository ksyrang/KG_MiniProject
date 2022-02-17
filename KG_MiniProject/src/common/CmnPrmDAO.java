package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CmnPrmDAO {
	//TB명 : PRM_TB
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnPrmDAO() {
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
	
	public int IstPrm(CmnPrmDTO DTO) {
		int result = 0;
		sql = "INSERT INTO PRM_TB "+
				"(PRM_Code, PRM_Name)"+
				"VALUES(?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getPRM_Code());
			ps.setString(2, DTO.getPRM_Name());
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
	
	public CmnPrmDTO SltPrmOne(String PRM_Code) {
		CmnPrmDTO tmpdata = null;
		sql = "SELECT * FROM PRM_TB WHERE PRM_Code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRM_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnPrmDTO(rs.getString("PRM_Code"), rs.getString("PRM_Name"));
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
	
	public ArrayList<CmnPrmDTO> SltPrmAll(){
		ArrayList<CmnPrmDTO> Datalist = new ArrayList<>();
		CmnPrmDTO tmpdata = null;
		sql = "SELECT * FROM PRM_TB" ;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmDTO(rs.getString("PRM_Code"), rs.getString("PRM_Name"));
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

	public ObservableList<CmnPrmDTO> SltPrmAllOb(){
		ObservableList<CmnPrmDTO> Datalist = FXCollections.observableArrayList();
		CmnPrmDTO tmpdata = null;
		sql = "SELECT * FROM PRM_TB" ;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPrmDTO(rs.getString("PRM_Code"), rs.getString("PRM_Name"));
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
