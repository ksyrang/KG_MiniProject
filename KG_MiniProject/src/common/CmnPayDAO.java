package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnPayDAO {
	//TB명 : PAY_TB
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnPayDAO() {
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
	
	public int Istpay(CmnPayDTO cmnPayDTO) {
		int result = 0;
		sql = "INSERT INTO PAY_TB "+
				"(PAY_Code, PAYCode_Num, PAY_Type, PAY_Date, "+
				"MEMSHIPSCHE_Code, MEM_Code, PRMSCHE_CODE)"+
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		//PRMSCHE_Code
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cmnPayDTO.getPAY_Code());
			ps.setInt(2, cmnPayDTO.getPAYCode_Num());
			ps.setString(3, cmnPayDTO.getPAY_Type());
			ps.setDate(4, cmnPayDTO.getPAY_Date());
			ps.setString(5, cmnPayDTO.getMEMSHIPSCHE_Code());
			ps.setString(6, cmnPayDTO.getMEM_Code());
			ps.setString(7, cmnPayDTO.getPRMSCHE_Code());
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
	
	//결제 코드 번호 최대 값 찾기
	public int PayMaxCodeNum() {
		int result = 0;
		sql = "SELECT Max(PAYCode_Num) FROM PAY_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("Max(PAYCode_Num)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public CmnPayDTO SltPayOne(String PAY_Code) {
		CmnPayDTO tmpdata = null;
		sql = "SELECT * FROM PAY_TB WHERE PAY_Code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PAY_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnPayDTO(rs.getString("PAY_CODE"), 
						rs.getInt("PAYCode_Num"),
						rs.getString("PAY_TYPE"), 
						rs.getDate("PAY_DATE"), 
						rs.getString("MEMSHIPSCHE_CODE"), 
						rs.getString("MEM_Code"),
						rs.getString("PRMSCHE_CODE"));
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
	
	public ArrayList<CmnPayDTO> SltPayAll(){
		ArrayList<CmnPayDTO> Datalist = new ArrayList<>();
		CmnPayDTO tmpdata = null;
		sql = "SELECT * FROM PAY_TB" ;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				tmpdata = new CmnPayDTO(rs.getString("PAY_Code"), 
						rs.getInt("PAYCode_Num"),
						rs.getString("PAY_Type"), 
						rs.getDate("PAY_Date"), 
						rs.getString("MEMSHIPSCHE_Code"), 
						rs.getString("MEM_Code"),
						rs.getString("PRMSCHE_CODE"));
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
	
	// 프로그램 별 pay_code 카운트
	public ArrayList<CmnPayDTO> getPayCode(String PRMSCHE_CODE) {
		sql = "SELECT PAY_Code, PRMSCHE_Code FROM PAY_TB WHERE PRMSCHE_CODE = ?";
		ArrayList<CmnPayDTO> Datalist = new ArrayList<>();
		CmnPayDTO tmpdata = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRMSCHE_CODE);
			rs = ps.executeQuery();
			while(rs.next()) {
				//result = rs.getInt("count(*)");
				tmpdata = new CmnPayDTO();
				tmpdata.setPAY_Code(rs.getString("PAY_Code"));
				tmpdata.setPRMSCHE_Code(rs.getString("PRMSCHE_CODE"));
				Datalist.add(tmpdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		//return result;
		return Datalist;
	}
	
}
