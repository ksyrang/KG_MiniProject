package mem.Welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Main.main.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MEM_WelcomeDAO {
	private Connection con;
	
	public MEM_WelcomeDAO() {
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
	
	public ObservableList<MEM_WelcomeDTO> selectMemAllProgram(String id) {
		String sql1 = "SELECT prmsche_code, memshipsche_code FROM mem_tb WHERE mem_id=?";
		String sql2 = "SELECT * FROM prmsche_tb WHERE prmsche_code=?";
		PreparedStatement ps1, ps2;
		ResultSet rs1, rs2;
		ObservableList<MEM_WelcomeDTO> member = FXCollections.observableArrayList();
		try {
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			rs1 = ps1.executeQuery();
			if(rs1.next()) {
				ps2 = con.prepareStatement(sql2);
				ps2.setString(1, rs1.getString("prmsche_code"));
				rs2 = ps2.executeQuery();
				if(rs2.next()) {
					MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();				
					memWelcomeDto.setPrm_code(rs2.getString("prm_code"));
					memWelcomeDto.setTrainer_code(rs2.getString("trainer_code"));
					memWelcomeDto.setPrmsche_price(rs2.getInt("prmsche_price"));
					memWelcomeDto.setPrmsche_strdate(rs2.getDate("prmsche_strdate"));
					memWelcomeDto.setPrmsche_enddate(rs2.getDate("prmsche_enddate"));
					memWelcomeDto.setPrmsche_code(rs1.getString("prmsche_code"));
					memWelcomeDto.setMemshipsche_code(rs1.getString("memshipsche_code"));
					member.add(memWelcomeDto);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public String getPrmName(String PrmCode) {
		String prm_name = null;
		String sql = "SELECT prm_name FROM prm_tb WHERE prm_code=?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, PrmCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("prm_name");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return prm_name;
	}
	
	public ObservableList<MEM_WelcomeDTO> getAllMemberList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MEM_WelcomeDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();				
//				memWelcomeDto.setMem_code(rs.getString("mem_code"));
//				memWelcomeDto.setMem_name(rs.getString("mem_name"));
//				memWelcomeDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memWelcomeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
}
