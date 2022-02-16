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
	private Controller controller;
	
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
		String sql = "SELECT mem_code, prmsche_code, memshipsche_code , mem_id, mem_name FROM mem_tb WHERE mem_id=?";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MEM_WelcomeDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();				
				memWelcomeDto.setMem_code(rs.getString("mem_code"));
				memWelcomeDto.setPrmsche_code(rs.getString("prmsche_code"));
				memWelcomeDto.setMemshipsche_code(rs.getString("memshipsche_code"));
				memWelcomeDto.setMem_id(rs.getString("mem_id"));
				memWelcomeDto.setMem_name(rs.getString("mem_name"));
				member.add(memWelcomeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
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
				memWelcomeDto.setMem_code(rs.getString("mem_code"));
				memWelcomeDto.setMem_name(rs.getString("mem_name"));
				memWelcomeDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memWelcomeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public ObservableList<MEM_WelcomeDTO> getNotApproveList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb WHERE mem_approve ='false'";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MEM_WelcomeDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MEM_WelcomeDTO memWelcomeDto = new MEM_WelcomeDTO();
				memWelcomeDto.setMem_code(rs.getString("mem_code"));
				memWelcomeDto.setMem_name(rs.getString("mem_name"));
				memWelcomeDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memWelcomeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	

}
