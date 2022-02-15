package admin.helthProgramMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelthProgramMgtDAO {
	private Connection con;
	public HelthProgramMgtDAO() {
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
	
	// 헬스 회원권 모두 보기
	public ObservableList<HelthProgramMgtDTO> getAllPro() {
		String sql = "SELECT * FROM memship_tb";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<HelthProgramMgtDTO> helthPro = FXCollections.observableArrayList();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				HelthProgramMgtDTO helthProMgtDto = new HelthProgramMgtDTO();
				helthProMgtDto.setMemship_code(rs.getString("memship_code"));
				helthProMgtDto.setMemship_type(rs.getString("memship_type"));
				helthProMgtDto.setMemship_price(rs.getInt("memship_price"));
				helthPro.add(helthProMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return helthPro;
	}
	
	// 회원권 중복 체크
	public HelthProgramMgtDTO selectType(String type) {
		System.out.println("dao 내 selectType");
		String sql = "SELECT * FROM memship_tb WHERE memship_type = ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				HelthProgramMgtDTO helthProgramMgtDto = new HelthProgramMgtDTO();
				
				helthProgramMgtDto.setMemship_code(rs.getString("memship_code"));
				helthProgramMgtDto.setMemship_type(rs.getString("memship_type"));
				helthProgramMgtDto.setMemship_price(rs.getInt("memship_price"));
				return helthProgramMgtDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 회원권 등록
	public int memshipInsert(HelthProgramMgtDTO helthProgramDto) {
		System.out.println("dao 내 memshipInset");
		String sql = "INSERT INTO memship_tb VALUES(?,?,?)";
		PreparedStatement ps;
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, helthProgramDto.getMemship_type() + "memship");
			ps.setString(2, helthProgramDto.getMemship_type());
			ps.setInt(3, helthProgramDto.getMemship_price());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
