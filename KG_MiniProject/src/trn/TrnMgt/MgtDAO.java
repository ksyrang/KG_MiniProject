package trn.TrnMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trn.DTO.TrnDTO;

public class MgtDAO {
	
	private Connection con;

	public MgtDAO() {
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
	
	public TrnDTO SelectTrnInfo(String Code) {
		TrnDTO tmpDto = null;
		String sql = "SELECT * FROM MEM_TB WHERE TRAINER_Code = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Code);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmpDto = new TrnDTO();	
				tmpDto.setTRAINER_Code(rs.getString("TRAINER_Code"));
				tmpDto.setTRAINER_ID(rs.getString("TRAINER_ID"));
				tmpDto.setTRAINER_PW(rs.getString("TRAINER_PW"));
				tmpDto.setTRAINER_Name(rs.getString("TRAINER_Name"));
				tmpDto.setTRAINER_Gender(rs.getString(""));
				tmpDto.setTRAINER_Birth(rs.getInt("TRAINER_Birth"));
				tmpDto.setTRAINER_Mobile(rs.getInt("TRAINER_Mobile"));
				tmpDto.setTRAINER_Career(rs.getInt("TRAINER_Career"));
				tmpDto.setTRAINER_Addr(rs.getString("TRAINER_Addr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpDto;
	}
	
	public int UpdateTrnInfo(TrnDTO trndto) {
		int result = 0;
		PreparedStatement ps;
		String sql = "UPDATE FROM MEM_TB SET "
				+ "TRAINER_PW=?,TRAINER_Name=?, "
				+ "TRAINER_Birth=?, TRAINER_Mobile=?, "
				+ "TRAINER_Career=?, TRAINER_Addr=?"
				+ "WHERE TRAINER_Code=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, trndto.getTRAINER_PW());
			ps.setString(2, trndto.getTRAINER_Name());
			ps.setInt(3, trndto.getTRAINER_Birth());
			ps.setInt(4, trndto.getTRAINER_Mobile());
			ps.setInt(5, trndto.getTRAINER_Career());
			ps.setString(6, trndto.getTRAINER_Addr());
			ps.setString(7, trndto.getTRAINER_Code());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
}
