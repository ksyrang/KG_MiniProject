package admin.memberMgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mem.Enroll.EnrollDTO;

public class MemberMgtDAO {
	private Connection con;
	
	public MemberMgtDAO() {
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
	
	public MemberMgtDTO selectCode(String mem_code) {
		String sql = "SELECT * FROM mem_tb WHERE mem_code= ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mem_code);
			rs = ps.executeQuery();
			MemberMgtDTO memberMgtDto = new MemberMgtDTO();
			if(rs.next()) {
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_id(rs.getString("mem_id"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_pw(rs.getString("mem_pw"));
				memberMgtDto.setMem_gender(rs.getString("mem_gender"));
				memberMgtDto.setMem_birth(rs.getString("mem_birth"));
				memberMgtDto.setMem_mobile(rs.getString("mem_mobile"));
				memberMgtDto.setMem_addr(rs.getString("mem_addr"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				
				return memberMgtDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ObservableList<MemberMgtDTO> getAllMemberList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MemberMgtDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public ObservableList<MemberMgtDTO> getNotApproveList() {
		String sql = "SELECT mem_code, mem_name, mem_approve FROM mem_tb WHERE mem_approve ='false'";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<MemberMgtDTO> member = FXCollections.observableArrayList();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberMgtDTO memberMgtDto = new MemberMgtDTO();
				memberMgtDto.setMem_code(rs.getString("mem_code"));
				memberMgtDto.setMem_name(rs.getString("mem_name"));
				memberMgtDto.setMem_approve(rs.getString("mem_approve"));
				member.add(memberMgtDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	

}
