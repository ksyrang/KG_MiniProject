package trn.DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trn.DBDTO.TrnExPSCHEDTO;

public class TrnExPSCHEDAO {
	
	private Connection con;

	public TrnExPSCHEDAO() {
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
	
	public int ExPSCHEInsert(TrnExPSCHEDTO ExPSCHE) {
		int result = 0;
		String sql = "INSERT INTO PRMSCHE_TB VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ExPSCHE.getPRMSCHE_CODE());
			ps.setDate(2, ExPSCHE.getPRMSCHE_STRDATE());
			ps.setDate(3, ExPSCHE.getPRMSCHE_ENDDATE());
			ps.setString(4, ExPSCHE.getPRMSCHE_TIME());
			ps.setString(5, ExPSCHE.getPRM_CODE());
			ps.setString(6, ExPSCHE.getTRAINER_CODE());
			ps.setInt(7, ExPSCHE.getPRMSCHE_LIMITP());
			ps.setInt(8, ExPSCHE.getPRMSCHE_CURRENTP());
			ps.setInt(9, ExPSCHE.getPRMSCH_PRICE());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public TrnExPSCHEDTO ExPSCHESelect(String PRMSCHE_CODE) {
		TrnExPSCHEDTO tmpExPSCHE = null;
		String sql = "SELECT * FROM PRMSCHE_TB WHERE PRMSCHE_CODE = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRMSCHE_CODE);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpExPSCHE = new TrnExPSCHEDTO(
						rs.getString("PRMSCHE_CODE")
						, rs.getDate("PRMSCHE_STRDATE")
						, rs.getDate("PRMSCHE_ENDDATE")
						, rs.getString("PRMSCHE_TIME")
						, rs.getString("PRM_CODE")
						, rs.getString("TRAINER_CODE")
						, rs.getInt("PRMSCHE_LIMITP")
						, rs.getInt("PRMSCHE_CURRENTP")
						, rs.getInt("PRMSCH_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return tmpExPSCHE;
	}
	public ArrayList<TrnExPSCHEDTO> ExPSCHESelectALLbyTrn(String TRAINER_CODE) {
//		TrnExPSCHEDTO tmpExPSCHE = null;
		ArrayList<TrnExPSCHEDTO> tmpSCHE = new ArrayList<TrnExPSCHEDTO>();
		String sql = "SELECT * FROM PRMSCHE_TB WHERE TRAINER_CODE = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, TRAINER_CODE);
			rs = ps.executeQuery();
			if(rs.next()) {
				TrnExPSCHEDTO tmpExPSCHE = new TrnExPSCHEDTO(
						rs.getString("PRMSCHE_CODE")
						, rs.getDate("PRMSCHE_STRDATE")
						, rs.getDate("PRMSCHE_ENDDATE")
						, rs.getString("PRMSCHE_TIME")
						, rs.getString("PRM_CODE")
						, rs.getString("TRAINER_CODE")
						, rs.getInt("PRMSCHE_LIMITP")
						, rs.getInt("PRMSCHE_CURRENTP")
						, rs.getInt("PRMSCH_PRICE"));
				tmpSCHE.add(tmpExPSCHE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return tmpSCHE;
	}
	public int ExPSCHEUpdate(TrnExPSCHEDTO ExPSCHE) {
		int result = 0;
		String sql = "UPDATE FROM PRMSCHE_TB SET"
			+"PRMSCHE_STRDATE=? "
			+"PRMSCHE_ENDDATE=? "
			+"PRMSCHE_TIME=? "
			+"PRM_CODE=? "
			+"TRAINER_CODE=? "
			+"PRMSCHE_LIMITP=? "
			+"PRMSCHE_CURRENTP=? "
			+"PRMSCH_PRICE=? "
			+"WHERE PRMSCHE_CODE = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, ExPSCHE.getPRMSCHE_STRDATE());
			ps.setDate(2, ExPSCHE.getPRMSCHE_ENDDATE());
			ps.setString(3, ExPSCHE.getPRMSCHE_TIME());
			ps.setString(4, ExPSCHE.getPRM_CODE());
			ps.setString(5, ExPSCHE.getTRAINER_CODE());
			ps.setInt(6, ExPSCHE.getPRMSCHE_LIMITP());
			ps.setInt(7, ExPSCHE.getPRMSCHE_CURRENTP());
			ps.setInt(8, ExPSCHE.getPRMSCH_PRICE());
			ps.setString(9, ExPSCHE.getPRMSCHE_CODE());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int ExPSCHEDelete(String Code) {
		int result = 0;
		String sql = "DELETE FROM PRMSCHE_TB WHERE PRMSCHE_Code = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Code);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
