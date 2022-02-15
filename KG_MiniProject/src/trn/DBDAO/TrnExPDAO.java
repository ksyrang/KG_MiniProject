package trn.DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trn.DBDTO.TrnExPDTO;

public class TrnExPDAO {
	private Connection con;

	public TrnExPDAO() {
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
	public TrnExPDTO SelectExP(String PRM_CODE) {
		TrnExPDTO tmpDto = null;
		String sql = "SELECT * FROM TRM_TB WHERE PRM_CODE = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PRM_CODE);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpDto = new TrnExPDTO();	
				tmpDto.setPRM_Code(rs.getString("PRM_Code"));
				tmpDto.setPRM_Name(rs.getString("PRM_Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpDto;
	}
	
	
	
}
