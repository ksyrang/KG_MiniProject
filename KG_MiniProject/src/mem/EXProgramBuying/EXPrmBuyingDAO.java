package mem.EXProgramBuying;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.helthProgramMgt.HelthProgramMgtDTO;
import common.CmnPrmDAO;
import common.CmnPrmDTO;
import common.CmnPrmScheDAO;
import common.CmnPrmScheDTO;
import common.CmnTrainerDAO;
import common.CmnTrainerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EXPrmBuyingDAO {
	private Connection con;

	public EXPrmBuyingDAO() {
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
	public ObservableList<ExPrmBuyingDTO> getAllInfo() {
		String sql = "SELECT * FROM PRMSCHE_TB";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<ExPrmBuyingDTO> allList = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				ExPrmBuyingDTO ExPrmBuyingDto = new ExPrmBuyingDTO();
				ExPrmBuyingDto.setPRMSCHE_Code(rs.getString("PRMSCHE_Code"));
				ExPrmBuyingDto.setPRMSCHE_Price(rs.getInt("PRMSCHE_Price"));
				ExPrmBuyingDto.setPRMSCHE_Strdate(rs.getDate("PRMSCHE_Strdate"));
				ExPrmBuyingDto.setPRMSCHE_Enddate(rs.getDate("PRMSCHE_Enddate"));
				ExPrmBuyingDto.setPRMSCHE_Time(rs.getString("PRMSCHE_Time"));
				ExPrmBuyingDto.setPRMSCHE_LimitP(rs.getInt("PRMSCHE_LimitP"));
				ExPrmBuyingDto.setPRMSCHE_CurrentP(rs.getInt("PRMSCHE_CurrentP"));
				ExPrmBuyingDto.setTRAINER_Code(rs.getString("TRAINER_Code"));
				ExPrmBuyingDto.setPRM_Code(rs.getString("PRM_Code"));

				CmnTrainerDAO cmnTrainerDao = new CmnTrainerDAO();
				CmnTrainerDTO cmnTrainerDto = cmnTrainerDao.SltTrnOne(rs.getString("TRAINER_Code"));
				ExPrmBuyingDto.setTRAINER_Name(cmnTrainerDto.getTRAINER_Name());
				CmnPrmDAO cmnPrmDao = new CmnPrmDAO();
				CmnPrmDTO cmnPrmDto = cmnPrmDao.SltPrmOne(rs.getString("PRM_Code"));
				ExPrmBuyingDto.setPRM_Name(cmnPrmDto.getPRM_Name());

				allList.add(ExPrmBuyingDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allList;
	}
		
	
	
	//ex프로그램 중복체크
	public ExPrmBuyingDTO selectExProgram(String addProgram) {
		String sql = "SELECT * FROM PRM_TB WHERE PRM_Name=?";
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, addProgram);
			rs = ps.executeQuery();
			if (rs.next()) {
				ExPrmBuyingDTO ExPrmBuyingDto = new ExPrmBuyingDTO();
				ExPrmBuyingDto.setPRM_Code(rs.getString("PRM_Code"));
				ExPrmBuyingDto.setPRM_Name(rs.getString("PRM_Name"));
				return ExPrmBuyingDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//ex프로그램 PRM_Name 리스트뷰 등록
	public int insertHealthPrmBuying(ExPrmBuyingDTO ExPrmBuyingDto) {
		String sql = "INSERT INTO PRM_TB VALUES(?,?)";
		PreparedStatement ps;
		int result = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ExPrmBuyingDto.getPRM_Code());
			ps.setString(2, ExPrmBuyingDto.getPRM_Name());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	//ex프로그램 PRM_Name 리스트뷰 삭제
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
	
	
	//ex프로그램 세부사항 수정 시 중복체크
	public int selectModifyHealthPrmBuying(ExPrmBuyingDTO ExPrmBuyingDto, CmnPrmScheDTO cmnPrmScheDto) {
		String sql = "SELECT * FROM PRMSCHE_TB WHERE PRMSCHE_CODE=?";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cmnPrmScheDto.getPRMSCHE_Code());
			result = ps.executeUpdate();
			if (ExPrmBuyingDto.getPRMSCHE_Code().equals(cmnPrmScheDto.getPRMSCHE_Code()) &
				(ExPrmBuyingDto.getPRMSCHE_Strdate().equals(cmnPrmScheDto.getPRMSCHE_Strdate())) &
				(ExPrmBuyingDto.getPRMSCHE_Enddate().equals(cmnPrmScheDto.getPRMSCHE_Enddate())) &
				(ExPrmBuyingDto.getPRMSCHE_Time().equals(cmnPrmScheDto.getPRMSCHE_Time())) &
//				(healthPrmBuyingDto.getTRAINER_Code().equals(cmnPrmScheDto.getTRAINER_Code())) &
				(ExPrmBuyingDto.getPRMSCHE_Price() == cmnPrmScheDto.getPRMSCHE_Price())) {
				result = 0;
			}else {
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	//클릭한 ex프로그램 세부사항 수정
	public int setHealthPrmBuyingModify(ExPrmBuyingDTO exProgramMgtDto) {
		String sql = "UPDATE PRMSCHE_TB SET PRMSCHE_STRDATE=?, PRMSCHE_ENDDATE=?, PRMSCHE_TIME=?, PRMSCHE_LIMITP=?, PRMSCHE_PRICE=? WHERE PRMSCHE_CODE=?";
		PreparedStatement ps;
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, exProgramMgtDto.getPRMSCHE_Strdate());
			ps.setDate(2, exProgramMgtDto.getPRMSCHE_Enddate());
			ps.setString(3, exProgramMgtDto.getPRMSCHE_Time());
			ps.setInt(4, exProgramMgtDto.getPRMSCHE_LimitP());
			ps.setInt(5, exProgramMgtDto.getPRMSCHE_Price());
			ps.setString(6, exProgramMgtDto.getPRMSCHE_Code());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		//PRM_Name변경 쿼리문 작성필요

	}

	
	//클릭한 세부항목삭제
	public void ExPrmBuyingDeleteProc(CmnPrmScheDTO cmnPrmScheDto) {
		String sql = "DELETE FROM PRMSCHE_TB WHERE PRMSCHE_CODE = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cmnPrmScheDto.getPRMSCHE_Code());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	





}
