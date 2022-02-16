package admin.exProgramMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.helthProgramMgt.HelthProgramMgtDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
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

	
	//listView 모든 정보
	public ObservableList<String> getAllProgram() {	
		String sql = "SELECT PRM_Name FROM PRM_TB";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<String> allProgram = FXCollections.observableArrayList();
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
	
	
	//tableView 모든 정보
	public ObservableList<ExProgramMgtDTO> getAllInfo() {
		String sql = "SELECT * FROM PRMSCHE_TB";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<ExProgramMgtDTO> allList = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				ExProgramMgtDTO exProgramMgtDto = new ExProgramMgtDTO();
//				exProgramMgtDto.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));
//				exProgramMgtDto.setPRMSCHE_Price(rs.getString("PRMSCHE_Price"));
//				exProgramMgtDto.setPRMSCHE_Strdate(rs.getString("PRMSCHE_Strdate"));
//				exProgramMgtDto.setPRMSCHE_Enddate(rs.getString("PRMSCHE_Enddate"));
//				exProgramMgtDto.setPRMSCHE_Time(rs.getString("PRMSCHE_Time"));
//				exProgramMgtDto.setPRMSCHE_LimitP(rs.getString("PRMSCHE_LimitP"));
//				exProgramMgtDto.setPRMSCHE_CurrentP(rs.getString("PRMSCHE_CurrentP"));
//				
//				exProgramMgtDto.setTRAINER_Code(rs.getString("TRAINER_Code"));
//				exProgramMgtDto.setPRM_Code(rs.getString("PRM_Code"));
				
				System.out.println(rs.getString("PRMSCHE_Code"));
//				System.out.println(rs.getString("PRMSCHE_Price"));
				System.out.println(rs.getString("PRMSCHE_Strdate"));
				System.out.println(rs.getString("PRMSCHE_Enddate"));
				System.out.println(rs.getString("PRMSCHE_Time"));
				System.out.println(rs.getString("PRMSCHE_LimitP"));
				System.out.println(rs.getString("PRMSCHE_CurrentP"));
				System.out.println(rs.getString("TRAINER_Code"));
				System.out.println(rs.getString("PRM_Code"));

				
//				CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
//				CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(rs.getString("TRAINER_Code"));
//				exProgramMgtDto.setTRAINER_Name(cmnTrainerDto.getTRAINER_Name());
//				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
//				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(rs.getString("PRM_Code"));
//				exProgramMgtDto.setPRM_Name(cmnPrmDto.getPRM_Name());

//				allList.add(exProgramMgtDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allList;
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
