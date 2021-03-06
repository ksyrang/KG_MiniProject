package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CmnMemShipScheDAO {
	private String sql = "";
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public CmnMemShipScheDAO() {
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
	public int IstMemShipSche(CmnMemShipScheDTO DTO) {
		int result = 0;
		sql = "INSERT INTO MEMSHIPSCHE_TB "
				+ "(MEMSHIPSCHE_Code, "
				+ "MEMSHIPSCHECode_Num, "
				+ "MEMSHIPSCHE_Strdate, "
				+ "MEMSHIPSCHE_Enddate, "
				+ "MEMSHIP_Code, "
				+ "MEM_Code) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);//향후 스케쥴 코드 번호 set해줘야함
			ps.setString(1, DTO.getMEMSHIPSCHE_Code());
			ps.setInt(2, DTO.getMEMSHIPSCHECode_Num());
			ps.setDate(3, DTO.getMEMSHIPSCHE_Strdate());
			ps.setDate(4, DTO.getMEMSHIPSCHE_Enddate());
			ps.setString(5, DTO.getMEMSHIP_Code());
			ps.setString(6, DTO.getMEM_Code());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return result;
	}
	// 회원권스케줄 코드번호 최대 값 찾기
		public int MemShipScheMaxCodeNum() {
			int result = 0;
			sql = "SELECT Max(MEMSHIPSCHECode_Num) FROM MEMSHIPSCHE_TB";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt("Max(MEMSHIPSCHECode_Num)");
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
	
	// 회원권스케줄 갯수
	public int CntMemShipSche() {
		int result = 0;
		sql = "SELECT count(*) FROM MEMSHIPSCHE_TB";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public CmnMemShipScheDTO SltMemShipScheCode(String MEM_Code) {
		CmnMemShipScheDTO tmpdata = null;
		sql = "SELECT * FROM MEMSHIPSCHE_TB WHERE MEM_Code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEM_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemShipScheDTO();
				tmpdata.setMEMSHIPSCHE_Code(rs.getString("MEMSHIPSCHE_Code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	public CmnMemShipScheDTO SltMemShipScheOne(String MEMSHIPSCHE_Code) {
		CmnMemShipScheDTO tmpdata = null;
		sql = "SELECT * FROM MEMSHIPSCHE_TB WHERE MEMSHIPSCHE_Code = ?" ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MEMSHIPSCHE_Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpdata = new CmnMemShipScheDTO(
				rs.getString("MEMSHIPSCHE_Code"),
				rs.getInt("MEMSHIPSCHECode_Num"),
				rs.getDate("MEMSHIPSCHE_Strdate"),
				rs.getDate("MEMSHIPSCHE_Enddate"),
				rs.getString("MEMSHIP_Code"),
				rs.getString("MEM_Code")
				);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public CmnMemShipScheDTO SltMemShipOne(String MEMSHIP_Code) {
			CmnMemShipScheDTO tmpdata = null;
			sql = "SELECT * FROM MEMSHIPSCHE_TB WHERE MEMSHIP_Code = ?" ;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, MEMSHIP_Code);
				rs = ps.executeQuery();
				while(rs.next()) {
					tmpdata = new CmnMemShipScheDTO(
					rs.getString("MEMSHIPSCHE_Code"),
					rs.getInt("MEMSHIPSCHECode_Num"),
					rs.getDate("MEMSHIPSCHE_Strdate"),
					rs.getDate("MEMSHIPSCHE_Enddate"),
					rs.getString("MEMSHIP_Code"),
					rs.getString("MEM_Code")
					);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	
	//

}

