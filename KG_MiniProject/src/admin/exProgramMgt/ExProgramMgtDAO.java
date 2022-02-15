package admin.exProgramMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExProgramMgtDAO {
	private Connection con;

	public ExProgramMgtDAO() {
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

//	public ArrayList<String> allProgram() {
	public ObservableList<String> allProgram() {	
		String sql = "SELECT PRM_Name FROM PRM_TB";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<String> allProgram = FXCollections.observableArrayList();
//		ArrayList<String> allProgram = new ArrayList<String> ();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				allProgram.add(rs.getString("PRM_Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allProgram;
	}
	
	//ex프로그램 중복체크
	public ExProgramMgtDTO selectExProgram(String addProgram) {
		String sql = "SELECT * FROM PRM_TB WHERE PRM_Name=?";
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, addProgram);
			rs = ps.executeQuery();
			if (rs.next()) {
				ExProgramMgtDTO exProgramDto = new ExProgramMgtDTO();
				exProgramDto.setPRM_Code(rs.getString("PRM_Code"));
				exProgramDto.setPRM_Name(rs.getString("PRM_Name"));
				return exProgramDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//ex프로그램 등록
	public int insertExProgram(ExProgramMgtDTO exprogramDto) {
		String sql = "INSERT INTO PRM_TB VALUES(?,?)";
		PreparedStatement ps;
		int result = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, exprogramDto.getPRM_Code());
			ps.setString(2, exprogramDto.getPRM_Name());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectDelete(String selectData) {
		String sql = "DELETE FROM PRM_TB WHERE PRM_Name=?";
		PreparedStatement ps;
		int result = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, selectData);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}




}
