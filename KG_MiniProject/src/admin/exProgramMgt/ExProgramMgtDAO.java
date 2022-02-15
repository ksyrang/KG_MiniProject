package admin.exProgramMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public ExProgramMgtDTO selectExProgram(String exname) {
		String sql = "SELECT * FROM PRM_TB WHERE PRM_Name=?";
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, exname);
			rs = ps.executeQuery();
			if (rs.next()) {
				ExProgramMgtDTO exProgramDto = new ExProgramMgtDTO();
				exProgramDto.setPRM_Code(rs.getString("PRM_Code"));
				exProgramDto.setPRM_Name(rs.getString("PRM_Name"));
				exProgramDto.setPRM_Price(rs.getInt("PRM_Price"));
				return exProgramDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertExProgram(ExProgramMgtDTO exprogramDto) {
		String sql = "INSERT INTO PRM_TB VALUES(?,?,?)";
		PreparedStatement ps;
		int result = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, exprogramDto.getPRM_Code());
			ps.setString(2, exprogramDto.getPRM_Name());
			ps.setInt(3, exprogramDto.getPRM_Price());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
